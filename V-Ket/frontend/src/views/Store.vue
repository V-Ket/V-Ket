<template>
  <div id="store">
    <img src="images/store/storeContent.png" class="contents">
    <!-- <img src="images/store/storeImg.png" class="storeImg"> -->
    <img src="images/store/storeBack.png" class="storeBack">
    <img src="images/store/storeName.png" class="storeName">
    <img src="images/store/storeHost.png" class="storeHost">
    <div class="container">

      <div class="row storeHeader">
        <div class="col-4">
          <div id="hostName">
            {{hostId}} 님의 상점
          </div>
        </div>
        <div class="col-4" id="storeName">
          <div id="storeName-box">
            {{storeName}}
          </div>
        </div>
        <div class="col-1">

        </div>
        <div class="col-3" id="storeOut">
          <img src="images/store/storeOut.png" @click="storeOut" style="width:4vw">
          <!-- <button @click="storeOut" id="storeOutBtn">나가기</button> -->
        </div>
      </div>

      <div class="row">
        <div class="col-6">
          <div id="storeImg">
            <v-carousel hide-delimiters height="35vh">
              <v-carousel-item
                v-for="(good,i) in goods"
                :key="i"
                :src="good.goodsImg"
                active-class="ccc"
              ></v-carousel-item>
            </v-carousel>
          </div>
        </div>
        <div class="col-6">
          <div id="storeUrl" v-if="storeUrl != ''">
            상점 URL : <a :href="storeUrl">{{storeUrl}}</a>
          </div>
          <div id="contentBox" v-if="storeUrl != ''">
            
            <div id="storeContent">
              {{storeContent}}
            </div>
          </div>
          <div id="contentBox2" v-else>
            <div id="storeContent">
              {{storeContent}}
            </div>
          </div>
        </div>
      </div>

      <div class="row" id="storeBtn">
        <div class="col-3">
        </div>
        <div class="col-2" id="btn1">
          <button id="goodsListBtn" @click="goodsList">상품 상세보기</button>
        </div>
        <div class="col-2" id="btn2">
          <button id="updateStore" v-if="isOwner" @click="updateStore">상점 정보 수정</button>
          <button id="sessionBtn" v-else @click="goSession">화상연결</button>
        </div>
        <div class="col-2" id="btn3">
          <button id="updateGoods" v-if="isOwner" @click="updateGoods">상품 등록</button>
          <button id="chatBtn" v-else @click="goChat">채팅하기</button>
        </div>
        <div class="col-3">
        </div>
      </div>
      <!-- 상품 등록 -->
      <v-dialog
        v-model="isOpenaddGoodsModal"
        max-width="500px"
      > 
        <GoodsModal class="temp" goodsId="0" :storeid="this.storeId" :isUpdate="isUpdate" @closeGoodsModal="closeGoodsModal" style="z-index:1000"  />
      </v-dialog>
      <!-- 상점 정보 수정 -->
      <v-dialog
        v-model="isOpenUpdateModal"
        max-width="500px"
      > 
        <OpenStore class="temp" :storeid="this.storeId" @closeStoreModal="closeStoreModal" style="z-index:1000" />
      </v-dialog>
    </div>
  </div>
</template>

<script>
import http from '@/http.js';
import OpenStore from '@/components/store/OpenStore.vue';
import GoodsModal from '@/components/store/GoodsModal.vue';
  export default {
    name: "Store",
    components:{
      GoodsModal,
      OpenStore,
    },
    data() {
      return{
        goods: Array,
        storeId: this.$route.params.storeId, //라우터 푸시로 가져와야함
        hostId:'',
        storeName: '',
        storeContent:'',
        storeUrl:'',
        sessionId: '',
        isOpenaddGoodsModal: false,
        isOwner: false,
        isUpdate: false,
        isOpenUpdateModal: false,
        islandpos: null,
        storepos: null,
      }
    },
    mounted(){
      http.get('/store/select/'+ this.storeId)
      .then((res)=>{
        this.storeName = res.data.storeName;
        this.storeContent = res.data.storeContent;
        this.storeUrl = res.data.storeUrl;
        this.hostId = res.data.userId;
        if(localStorage.getItem("userId") == this.hostId){
          this.isOwner = true;
        }
        console.log('상점정보가져오기'+res);
        // this.storeName = '테스트상점';
        // this.storeContent = '나는 이런걸 팔고 있는 상점이에요 많이 사주세요';
        // this.storeUrl = 'http://localhost:8080/store';
        // this.hostId = '주인장임';
      })
      //상품 정보 가져와서 goods에 담음
      http.get('/goods/store/'+this.storeId)
      .then((res)=>{
        this.goods = res.data;
      })
    },
    methods: {
      setStore(){
        http.get('/store/select/'+ this.storeId)
        .then((res)=>{
          this.storeName = res.data.storeName;
          this.storeContent = res.data.storeContent;
          this.storeUrl = res.data.storeUrl;
          this.hostId = res.data.userId;
          if(localStorage.getItem("userId") == this.hostId){
            this.isOwner = true;
          }
          console.log('상점정보가져오기'+res);
        })
      },
      storeOut(){
        this.$router.push({name:"Unity"});
      },
      goodsList(){
        //상품 상세보기로 라우터 푸시
        this.$router.push({name: "GoodsList", params:{storeId : this.storeId, hostId : this.hostId}});
        //this.$router.push({name: ""})
      },
      goSession(){
        //판매자와 화상연결
        const body = {
          buyerId : localStorage.getItem('userId'),
          sellerId : this.hostId
        }
        http.post('/session/regist', body)
        .then((res)=>{
          console.log(res)
          if(res.status == 201){
            this.sessionId = localStorage.getItem('userId') + this.hostId;
            this.$router.push({name: 'Meeting', params:{sessionid: this.sessionId, other: this.hostId}})
            console.log('성공')
          }else{
            console.log('실패')
          }
        })
      },
      goChat(){
        //판매자랑 채팅방 개설
        const body = {
          senderId : localStorage.getItem('userId'),
          receiverId : this.hostId
        }
        http.post('/chatRooms', body)
        .then((res)=>{
          console.log('채팅방개설'+res.data.status)
        })
      },
      updateStore(){
        //상점 정보 수정 모달 오픈
        this.isOpenUpdateModal = true;
      },
      updateGoods(){
        this.isOpenaddGoodsModal = true;
        //상품 등록/수정 모달? 모르겠네
      },
      closeGoodsModal(){
        this.isOpenaddGoodsModal = false;
        console.log("다시 정보 가져오기")

        http.get('/store/select/'+ this.storeId)
        .then((res)=>{
          this.storeName = res.data.storeName;
          this.storeContent = res.data.storeContent;
          this.storeUrl = res.data.storeUrl;
          this.hostId = res.data.userId;
          if(localStorage.getItem("userId") == this.hostId){
            this.isOwner = true;
          }
          console.log('상점정보가져오기'+res);
        // this.storeName = '테스트상점';
        // this.storeContent = '나는 이런걸 팔고 있는 상점이에요 많이 사주세요';
        // this.storeUrl = 'http://localhost:8080/store';
        // this.hostId = '주인장임';
        })
      //상품 정보 가져와서 goods에 담음
        http.get('/goods/store/'+this.storeId)
        .then((res)=>{
          this.goods = res.data;
        })
      },
      closeStoreModal(){
        this.isOpenUpdateModal = false;
        // 상점 다시 가져오기
        this.setStore();
      }
    },


  }
</script>
<style scoped>
.storeBack{
  position: absolute;
  top: 77vh;
  left: 8vw;
  width: 55vw;
  height: 15vh;
  z-index: -1;
}
.storeName{
  position: absolute;
  left: 22.2vw;
  top: 3vh;
  width: 22vw;
  z-index: -1;
}
.storeHost{
  position: absolute;
  left: 8vw;
  top: 17vh;
  width: 12vw;
  z-index: -1;
}
.contents{
  position: absolute;
  left: 31.5vw;
  top: 22vh;
  width: 30vw;
  height: 52vh;
  z-index: -1;
}
.ccc{
  width: 25vw;
  height: auto;
}
#store{
  width: 66.6vw;
  height: 100vh;
}
.storeHeader{
  /* border: 1px solid red; */
  text-align: center;
  margin-top:3vh;
  /* background-color: rgb(114, 64, 35); */
}
#storeName{
  /* border: 1px solid red; */
  font-size: 40px;
  font-weight: bold;
}
#storeName-box{
  /* border: 2px solid black; */
  padding: 5px;
  border-radius: 10px;
  /* background-color: rgb(165, 87, 42); */
  color: white;
  /* border-color: black;
  border-style: outset; */
}
#storeOut{
  /* border: 1px solid red; */
  /* margin-top: 3vh; */
}
#storeOutBtn{
  background-color: brown;
  border-radius: 10px;
  width: 5vw;
  height: 4vh;
  color: white;
  font-weight: bold;
}
#hostName{
  /* border-style: outset; */
  color: white;
  /* background-color: rgb(165, 87, 42); */
  /* border: 1px solid red; */
  margin-top: 13vh;
  margin-left: 6vw;
  font-size: 23px;
}
#storeImg{
  margin-left: 5vw;
  margin-top: 8vh;
}
#storeUrl{
  border: 1px solid black;
  margin-top: 8vh;
  /* margin-left: 2vw; */
  font-size: 20px;
  text-align: center;
  padding: 5px;
  border-radius: 10px;
  width: 25vw;
}
#storeUrlnone{
  visibility: hidden;
  border: 1px solid black;
  margin-top: 8vh;
  /* margin-left: 2vw; */
  font-size: 20px;
  text-align: center;
  padding: 5px;
  border-radius: 10px;
  width: 25vw;
}
#contentBox{
  margin-top: 1vh;
  /* margin-left: 2vw; */
  width: 25vw;
  border: 1px solid black;
  height: 30vh;
}
#contentBox2{
  margin-top: 8vh;
  /* margin-left: 2vw; */
  width: 25vw;
  border: 1px solid black;
  height: 35vh;
}
#storeContent{
  margin-top: 1vh;
  text-align: center;
  font-size: 20px;
}
#storeBtn{
  margin-top: 10.5vh;
}
#btn1{
}
#btn2{
  margin-left: 2vw;
}
#btn3{
  margin-left: 2vw;
}
#goodsListBtn{
  /* border: 1px solid black; */
  border-radius: 10px;
  /* width: 10vw; */
  padding: 20px;
  /* margin-left: 10vw; */
  font-weight: bold;
  font-size: 22px;
  color: white;
  /* #983C34 #812F38 */
  /* background-color: #812F38; */
  border-style: outset;
  border-color: #812F38;
  background-image: linear-gradient(290deg,#812F38,#983C34);
}
#sessionBtn{
  border-style: outset;
  border-color: #812F38;
  background-image: linear-gradient(290deg,#812F38,#983C34);
  border-radius: 10px;
  width: 10vw;
  padding: 20px;
  /* margin-left: 14vw; */
  font-weight: bold;
  font-size: 22px;
  color: white;
}
#chatBtn{
  border-style: outset;
  border-color: #812F38;
  background-image: linear-gradient(290deg,#812F38,#983C34);
  border-radius: 10px;
  width: 10vw;
  padding: 20px;
  /* margin-left: 18vw; */
  font-weight: bold;
  font-size: 22px;
  color: white;
}
#updateStore{
  border-style: outset;
  border-color: #812F38;
  background-image: linear-gradient(290deg,#812F38,#983C34);
  border-radius: 10px;
  width: 10vw;
  padding: 20px;
  /* margin-left: 14vw; */
  font-weight: bold;
  font-size: 22px;
  color: white;
}
#updateGoods{
  border-style: outset;
  border-color: #812F38;
  background-image: linear-gradient(290deg,#812F38,#983C34);
  border-radius: 10px;
  width: 10vw;
  padding: 20px;
  /* margin-left: 18vw; */
  font-weight: bold;
  font-size: 22px;
  color: white;
}
</style>