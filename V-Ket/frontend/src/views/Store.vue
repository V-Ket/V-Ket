<template>
  <div id="store">
    <div class="container">

      <div class="row storeHeader">
        <div class="col-4">
          <div id="hostName">
            {{hostId}} 님의 상점
          </div>
        </div>
        <div class="col-4" id="storeName">
          {{storeName}}
        </div>
        <div class="col-4" id="storeOut">
          <button @click="storeOut" id="storeOutBtn">나가기</button>
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
          <!-- <div id="storeUrlnone" v-else>URL 없음</div> -->
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

      <div class="row">
        <div class="col-6" id="storeBtn">
          <div class="container" id="btn1">
            <div class="row mb-4">
              <button id="goodsListBtn" @click="goodsList">상품 상세보기</button>
            </div>
            <div class="row mb-4">
              <button id="sessionBtn" @click="goSession">판매자와 화상연결</button>
            </div>
            <div class="row mb-4">
              <button id="chatBtn" @click="goChat">판매자와 채팅</button>
            </div>
          </div>
        </div>
        <div class="col-6" id="storeUpdate">
          <div class="container" id="btn2">
            <div class="row mb-4">
              <button id="updateStore" @click="updateStore">상점 정보 수정</button>
            </div>
            <div class="row">
              <button id="updateGoods" @click="updateGoods">상품 등록/수정</button>
            </div>
          </div>
        </div>
      </div>

      <v-dialog
        v-model="isOpenaddGoodsModal"
        max-width="500px"
        > <OpenStore class="temp" :islandpos="this.islandPos" :storepos="this.storePos" @open="open" @closeStoreModal="closeStoreModal" style="z-index:1000"  />
      </v-dialog>
    </div>
  </div>
</template>
<script>
import http from '@/http.js';
  export default {
    name: "Store",

    data() {
      return{
        goods: Array,
        storeId: this.$route.params.storeid, //라우터 푸시로 가져와야함
        hostId:'',
        storeName: '',
        storeContent:'',
        storeUrl:'',
        sessionId: '',
        isOpenaddGoodsModal: false,
      }
    },
    mounted(){
      http.get('/store/select/'+ this.storeId)
      .then((res)=>{
        this.storeName = res.data.storeName;
        this.storeContent = res.data.storeContent;
        this.storeUrl = res.data.storeUrl;
        this.hostId = res.data.userId;
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
            this.$router.push({name: 'Meeting', params:{sessionid: this.sessionId}})
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
      },
      updateGoods(){
        this.isOpenaddGoodsModal = true;
        //상품 등록/수정 모달? 모르겠네
      }
    }


  }
</script>
<style scoped>
.ccc{
  width: 25vw;
  height: auto;
}
#store{
  width: 66.6vw;
  height: 100vh;
}
.storeHeader{
  text-align: center;
  margin-top:5vh;
}
#storeName{
  font-size: 20px;
  font-weight: bold;
}
#storeOut{
  margin-top: 3vh;
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
  margin-top: 10vh;
  margin-left: 10vw;
  font-size: 20px;
}
#storeImg{
  margin-left: 10vw;
  margin-top: 8vh;
}
#storeUrl{
  border: 1px solid black;
  margin-top: 8vh;
  margin-left: 3vw;
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
  margin-left: 3vw;
  font-size: 20px;
  text-align: center;
  padding: 5px;
  border-radius: 10px;
  width: 25vw;
}
#contentBox{
  margin-top: 1vh;
  margin-left: 3vw;
  width: 25vw;
  border: 1px solid black;
  height: 30vh;
}
#contentBox2{
  margin-top: 8vh;
  margin-left: 3vw;
  width: 25vw;
  border: 1px solid black;
  height: 35vh;
}
#storeContent{
  margin-top: 1vh;
  text-align: center;
  font-size: 20px;
}
#btn1{
  margin-top: 6vh;
  margin-left: 5vw;
}
#btn2{
  margin-top: 6vh;
}
#goodsListBtn{
  border: 1px solid black;
  border-radius: 10px;
  width: 10vw;
  padding: 5px;
  margin-left: 11vw;
}
#sessionBtn{
  border: 1px solid black;
  border-radius: 10px;
  width: 10vw;
  padding: 5px;
  margin-left: 11vw;
}
#chatBtn{
  border: 1px solid black;
  border-radius: 10px;
  width: 10vw;
  padding: 5px;
  margin-left: 11vw;
}
#updateStore{
  border: 1px solid black;
  border-radius: 10px;
  width: 10vw;
  padding: 5px;
  margin-left: 11vw;
}
#updateGoods{
  border: 1px solid black;
  border-radius: 10px;
  width: 10vw;
  padding: 5px;
  margin-left: 11vw;
}
</style>