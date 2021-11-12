<template>
  <div id="nav" class="container">
    <div class="row">
      <div id="titlebox">V - Ket</div>
    </div>
    <!-- 로그아웃 -->
    <div class="row">
      <div class="col-6" id="nickname">
        {{userId}} 님 
      </div>
      <div class="col-6">
      <button class="btnLogout" @click="logout">로그아웃</button>
      </div>
    </div>
    <!-- 크레딧 -->
    <div class="row" id="credit-box">
      <div class="col-6" id="credit">
        credit :
      </div>
      <div class="col-6" id="mycredit">
        ${{this.credit}}
      </div>
      <!-- <button class="btnPurchase" @click="purchase">충전하기(새창)</button> -->
    </div>

    <!-- 충전하기 -->
    <div class="row">
      <div class="container-fluid mt-3">
        <button class="btnPurchase" @click="purchase2" style="vertical-align:middle">충전하기</button>
      </div>
    </div>

    <!-- 채팅 -->
    <!-- <div class="row">
      <Chat class="chat"/>
    </div> -->

    <div class="row">
      <div class="offcanvas offcanvas-end" id="demo0">
        <div class="offcanvas-header">
          <h1 class="offcanvas-title">채팅 목록</h1>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
        </div>
        <div class="offcanvas-body">
          <div v-for="(chatRoom, idx) in roomList" :key="idx">
            <div id="show-modal" @click="showModal = true, enterRoom(chatRoom.chatRoomId)">
              <span>{{chatRoom.chatRoomId}}번 채팅방</span>
              <span v-if="chatRoom.senderId !== userId">
                {{chatRoom.senderId}}
              </span>
              <span v-else>
                {{chatRoom.receiverId}}
              </span>
            </div>
            <ChatModal :chatRoomId="selectedChatRoomId" v-if="showModal" @close="showModal = false">
              <h3 slot="header">채팅보여줄거임</h3>
            </ChatModal>
          </div>
        </div>
      </div>
      <div class="container-fluid mt-3">
        <img class="reddot" id="reddot" src="images/alert/reddot.png">
        <button @click="getChatList" id="chatList" class="btn-meeting" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo0" style="vertical-align:middle">
          <span>채팅 목록</span>
        </button>
      </div>
    </div>

    <!-- 미팅목록 -->
    <div class="row">
      <div class="offcanvas offcanvas-end" id="demo">
        <div class="offcanvas-header">
          <h1 class="offcanvas-title">미팅 목록</h1>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
        </div>
        <div class="offcanvas-body">
          <table>
            <tr v-for="(meeting, i) in meetings"
            :key="i">
              <td>{{meeting.buyerId}}</td>
              <td></td>
              <td><button @click="$router.push({name:'Meeting', params:{sessionid: meeting.sessionName}})">접속하기</button></td>
            </tr>
          </table>
          <button @click="$router.push({name:'Meeting', params:{sessionid: 'jwjw2test'}})">jt접속하기</button>
        </div>
      </div>
      <div class="container-fluid mt-3">
        <img class="reddot" id="reddot" src="images/alert/reddot.png">
        <button @click="removeDot" class="btn-meeting" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo" style="vertical-align:middle">
          <span>미팅 목록</span>
        </button>
      </div>
    </div>

    <!-- 구매목록 -->
    <div class="row">
      <div class="offcanvas offcanvas-end" id="demo1">
        <div class="offcanvas-header">
          <h1 class="offcanvas-title">나의 구매 목록</h1>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
        </div>
        <div class="offcanvas-body">
          <table>
            <tr v-for="(buy, i) in buyList"
            :key="i">
              <td>{{buy.goodsName}}, </td>
              <td>{{buy.goodsPrice}}, </td>
              <td>{{buy.goodsQuantity}}, </td>
              <td>{{buy.purchaseStatus}}, </td>
              <td>
                <div v-if="buy.purchaseStatus == 10">결제완료</div>
                <div v-else-if="buy.purchaseStatus == 11">배송중</div>
                <div v-else-if="buy.purchaseStatus == 12">구매완료</div>
                <div v-else>구매취소</div>
              </td>
              <td>
                <div v-if="buy.purchaseStatus == 12 || buy.purchaseStatus == 13"></div>
                <div v-else><button @click="moveCredit(buy.dealId)">구매확정</button></div>
              </td>
              <td>
                <div v-if="buy.purchaseStatus == 12 || buy.purchaseStatus == 13"></div>
                <div v-else><button @click="cancelPurchase(buy.dealId)">구매취소</button></div>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div class="container-fluid mt-3">
        <button @click="getBuyList" class="btn-buy" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo1">
          <span>구매 목록</span>
        </button>
      </div>
    </div>
    <!-- 판매목록 -->
    <div class="row">
      <div class="offcanvas offcanvas-end" id="demo2">
        <div class="offcanvas-header">
          <h1 class="offcanvas-title">나의 판매 목록</h1>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
        </div>
        <div class="offcanvas-body">
          <table>
            <tr v-for="(sell, i) in sellList"
            :key="i">
              <td>{{sell.goodsName}}, </td>
              <td>{{sell.goodsPrice}}, </td>
              <td>{{sell.goodsQuantity}}, </td>
              <td>{{sell.purchaseStatus}}, </td>
              <td>
                <button @click="changePurchase(sell.dealId)">
                  <div v-if="sell.purchaseStatus == 10">결제완료</div>
                  <div v-else-if="sell.purchaseStatus == 11">배송중</div>
                  <div v-else-if="sell.purchaseStatus == 12">판매완료</div>
                  <div v-else>구매취소</div>
                </button>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div class="container-fluid mt-3">
        <button @click="getSellList" class="btn-sell" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo2">
          <span>판매 목록</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
// import Chat from '@/components/Chat/Chat.vue'
import http from '@/http.js';
import Offcanvas from 'bootstrap/js/dist/offcanvas'
import { mapGetters } from 'vuex'
import ChatModal from '@/components/Chat/ChatModal.vue'

export default {
  name: "Nav",
  components:{
    // Chat,
    ChatModal,
  },
  data() {
    return {
      meetings: Array,
      meetings2: Array,
      sellerId: String,
      meetingListSize : '',
      buyList: Array,
      sellList: Array,
      dealId: '',
      userId: localStorage.getItem('userId'),
      showModal: false,
      selectedChatRoomId: -1,
      chatRoomIdStr : '-1',
      userNickname: localStorage['userNickname'],
      roomList:[],
      bsOffcanvas: '',
      content:'',
    };
  },
  created(){
    
  },
  computed: {
    ...mapGetters(['credit'])
  },
  watch : {
    meetingListSize (newval, oldval){
      // console.log('new'+newval)
      // console.log('old'+oldval)
      var con = document.getElementById('reddot');
      if(newval > oldval){
        con.style.display = "block"
      }else{

        con.style.display = "none"
      }
    }
  },
  mounted() {
    this.bsOffcanvas = new Offcanvas(this.$refs.offcanvasRight)
    document.addEventListener(
      "click",
      function (event) {
        if (event.target.closest("#menu-container")) return;
        this.showMenu = false;
      }.bind(this)
    );
    this.sellerId = localStorage.getItem('userId')
    setInterval(() => {
      http.get('/session/getlist/' + this.sellerId)
      .then((res)=>{
        // console.log('앞'+this.meetingListSize)
        this.meetings = res.data
        this.$store.commit('setMeetingListSize', this.meetings.length)
        this.meetingListSize = this.$store.getters.getMeetingListSize
        // console.log('뒤'+this.meetingListSize)
      })
    }, 1000);
    
  },
  methods: {
    getChatList(){
      http.get('chatRooms/' + this.userId)
      .then((res) => {
        this.roomList = [];
        for(let i=0; i<res.data.length; i++) {
          let chatRoom = {
            'chatRoomId': res.data[i].chatRoomId,
            'senderId': res.data[i].senderId,
            'receiverId': res.data[i].receiverId
          }
          this.roomList.push(chatRoom);
        }
      });
      this.$store.commit('setChat', true)
    },
    enterRoom(inputChatRoomId) {
      alert(inputChatRoomId + '번 채팅 방 입장!!');
      this.selectedChatRoomId = inputChatRoomId;
    },
    chatOn(){
      this.$store.commit('setChat', true)
    },
    logout(){
      localStorage.removeItem('token')
      localStorage.removeItem('userId')
      localStorage.removeItem('userNickname')
      // this.$router.push({name:'Main'})
      window.location.href="http://localhost:8080/"
    },
    purchase(){
      window.open("http://localhost:8080/purchase", "충전하기", "width=800, height=700")
    },
    purchase2(){
      this.$router.push({name:'Purchase'})
    },
    removeDot(){
      document.getElementById('reddot').style.display="none";
    },
    getBuyList(){
      console.log('b리스트가져와')
      http.get('/deal/buylist/' + localStorage.getItem('userId'))
      .then((res)=>{
        this.buyList = res.data;
      })
    },
    getSellList(){
      console.log('s리스트가져와')
      http.get('/deal/selllist/' + localStorage.getItem('userId'))
      .then((res)=>{
        this.sellList = res.data;
      })
    },
    changePurchase(dealId){
      console.log('가즈아' + dealId)
      http.put('/deal/updateDeal/' + dealId)
      .then(()=>{

      })
    },
    cancelPurchase(dealId){
      http.put('/deal/cancelDeal/' + dealId)
      .then(()=>{

      })
    },
    moveCredit(dealId){
      http.put('/deal/movecredit/' + dealId)
      .then(()=>{

      })
    }
  },
};
</script>

<style scoped>
#credit-box{
  border: 1px solid black;
  /* margin-left: 0px; */
}
#credit{
  text-align: center;
  font-size: 20px;
}
#mycredit{
  font-size: 20px;
  margin-left: 0px;
}
#nickname{
  margin-top: 1.5vh;
  text-align: center;
  font-size: 22px;
  font-weight: bold;
}
#titlebox{
  border: 1px solid black;
  text-align: center;
  font-size: 50px;
}
.reddot{
  display: none;
  position: absolute;
  width: 1.2vw;
  top: 47.8vh;
  left: 6.2vw;
}
.creditDiv{
  margin-top: 10vh;
  text-align: center;
}
.btnPurchase{
  border: 1px solid black;
  border-radius: 10px;
  /* margin: 0.3vw; */
  /* margin-top: 4vh; */
  /* margin-left: 1vw; */
  width: 10vw;
  height: 5vh;
  background-image: linear-gradient(200deg,rgb(243, 243, 122),rgb(238, 241, 59),rgb(194, 194, 59));
  font-size: 20px;
  font-weight: bold;
}
.chat{
  border: 1px solid black;
  border-radius: 10px;
  width: 10vw;
  height: 5vh;
  background-image: linear-gradient(190deg,#eee, gray);
  font-size: 20px;
  font-weight: bold;
}
.btn-meeting{
  border: 1px solid black;
  border-radius: 10px;
  width: 10vw;
  height: 5vh;
  background-image: linear-gradient(190deg,#eee, gray);
  font-size: 20px;
  font-weight: bold;
}
.btn-meeting span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}
.btn-meeting span:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}
.btn-meeting:hover span {
  padding-right: 25px;
}
.btn-meeting:hover span:after {
  opacity: 1;
  right: 0;
}
.btn-buy{
  border: 1px solid black;
  border-radius: 10px;
  width: 10vw;
  height: 5vh;
  background-image: linear-gradient(190deg,#eee, gray);
  font-size: 20px;
  font-weight: bold;
}
.btn-buy span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}
.btn-buy span:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}
.btn-buy:hover span {
  padding-right: 25px;
}
.btn-buy:hover span:after {
  opacity: 1;
  right: 0;
}
.btn-sell{
  border: 1px solid black;
  border-radius: 10px;
  width: 10vw;
  height: 5vh;
  background-image: linear-gradient(190deg,#eee, gray);
  font-size: 20px;
  font-weight: bold;
}
.btn-sell span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}
.btn-sell span:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}
.btn-sell:hover span {
  padding-right: 25px;
}
.btn-sell:hover span:after {
  opacity: 1;
  right: 0;
}
.btnLogout{
  float: right;
  margin-top: 2vh;
  border: 1px solid black;
  width: 5vw;
}
#nav{
  top:0;
  position:absolute;
  top:0;
  width: 11.8vw;
  height: 100vh;
  left: 66.6vw;
  z-index:100;
  border-left: 1px solid black;
  border-bottom: 1px solid var(--color-grey-6);
  background-color: #eee;
  /* background-color: saddlebrown; */
  border: 1px solid black;
}
#nav > .nav-container{
  width : 80%;
  height: 100%;
  margin : auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>