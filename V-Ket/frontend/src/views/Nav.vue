<template>
  <div id="nav" class="container">
    <!-- 타이틀 -->
    <div class="row" id="titlebox">
        <img src="images/logo/navLogo.png">
    </div>
    <!-- 유저ID, 로그아웃 -->
    <div class="row">
      <div class="col-6">
      </div>
      <div class="col-6">
      <button class="btnLogout" @click="logout">로그아웃</button>
      </div>
    </div>
    <div class="row">
      <div class="col-12" id="nickname">
        {{userId}} 님 
      </div>
    </div>
    <!-- 크레딧 -->
    <div class="row" id="credit-box">
      <div class="col-6" id="credit">
        credit :
      </div>
      <div class="col-6" id="mycredit">
        <b>$</b>{{this.credit}}
      </div>
    </div>
    <!-- 충전하기 -->
    <div class="row">
      <div class="container-fluid" style="margin-top:3vh;">
        <button class="btnPurchase" @click="purchase2" style="vertical-align:middle">충전하기</button>
      </div>
    </div>
    <!-- 채팅 -->
    <div class="row">
      <div class="offcanvas offcanvas-end" id="demo0">
        <div class="offcanvas-header" id="chat-header">
          <h1 class="offcanvas-title" style="font-weight:bold;">채팅</h1>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
        </div>
        <div class="offcanvas-body" style="padding:0px;">
          <div v-for="(chatRoom, idx) in roomList" :key="idx">
            <div id="show-modal" @click="showModal = true, enterRoom(chatRoom.chatRoomId, chatRoom.senderId, chatRoom.receiverId)">
              <button v-if="chatRoom.senderId !== userId">
                {{chatRoom.senderId}} 님과의 채팅방
              </button>
              <button v-else>
                {{chatRoom.receiverId}} 님과의 채팅방
              </button>
            </div>
            <ChatModal :chatRoomId="selectedChatRoomId" :receiver="selectedReceiverId" v-if="showModal" @close="showModal = false" @reddotChat="reddotChat">
              <h3 slot="header">{{selectedReceiverId}} 님과의 채팅방</h3>
            </ChatModal>
          </div>
        </div>
      </div>
      <div class="container-fluid" style="margin-top:4vh;">
        <button ref="chatList" @click="getChatList" id="chatList" class="chat" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo0">
          <span>채팅 목록</span>
          <span class="badge" id="reddot-chat">&nbsp;</span>
        </button>
      </div>
    </div>
    <!-- 미팅목록 -->
    <div class="row">
      <div class="offcanvas offcanvas-end" id="demo">
        <div class="offcanvas-header" id="meet-header">
          <h1 class="offcanvas-title" style="font-weight:bold;">미팅 목록</h1>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
        </div>
        <div class="offcanvas-body container" id="meet-body">
          <div class="row" id="meet-row" v-for="(meeting, i) in meetings"
            :key="i">
            <div id="meet-buyer-id" class="col-6">
              {{meeting.buyerId}}
            </div>
            <div class="col-6">

              <button id="meet-btn" @click="$router.push({name:'Meeting', params:{sessionid: meeting.sessionName, other: meeting.buyerId, storeid: meeting.storeId, isseller: true}})">접속하기</button>
            </div>
          </div>
        </div>
      </div>
      <div class="container-fluid" style="margin-top:3vh;">
        <button @click="removeDot" class="btn-meeting" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo">
          <span class="text">미팅 목록</span>
          <span class="badge" id="reddot">&nbsp;</span>
        </button>
      </div>
    </div>
    <!-- 구매목록 -->
    <div class="row">
      <div class="offcanvas offcanvas-end" id="demo1">
        <div class="offcanvas-header" id="buylist-header">
          <h1 class="offcanvas-title" style="font-weight:bold;">구매 목록</h1>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
        </div>
        <div class="offcanvas-body container" style="padding:0px;">
          <div class="container" style="border-bottom:1px solid black">
            <div class="row">
              <div class="col-3">
                상품 이름
              </div>
              <div class="col-3">
                상품 가격
              </div>
              <div class="col-3">
                수량
              </div>
              <div class="col-3">
                상태
              </div>
            </div>
          </div>
          <div class="row" v-for="(buy, i) in buyList"
            :key="i">
            <div class="container" style="border-bottom:1px solid gray">
              <div class="row">
                <div class="col-3" style="padding:20px;">
                  {{buy.goodsName}}
                </div>
                <div class="col-3" style="padding:20px;">
                  {{buy.goodsPrice}}
                </div>
                <div class="col-3" style="padding:20px;">
                  {{buy.goodsQuantity}}
                </div>
                <div class="col-3" style="padding:20px 20px 20px 0;">
                  <div v-if="buy.purchaseStatus == 10">결제완료</div>
                  <div v-else-if="buy.purchaseStatus == 11">배송중</div>
                  <div v-else-if="buy.purchaseStatus == 12">구매완료</div>
                  <div v-else>구매취소</div>
                </div>
              </div>
              <div class="row">
                <div class="col-6">
                </div>
                <div class="col-3">
                  <div v-if="buy.purchaseStatus == 12 || buy.purchaseStatus == 13"></div>
                  <div v-else><button style="border:1px solid black; padding:2px; border-radius:10px;" @click="moveCredit(buy.dealId)">구매확정</button></div>
                </div>
                <div class="col-3">
                  <div v-if="buy.purchaseStatus == 12 || buy.purchaseStatus == 13"></div>
                  <div v-else><button style="border:1px solid black; padding:2px; border-radius:10px;" @click="cancelPurchase(buy.dealId)">구매취소</button></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="container-fluid" style="margin-top:3vh;">
        <button @click="getBuyList" class="btn-buy" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo1">
          <span>구매 목록</span>
        </button>
      </div>
    </div>
    <!-- 판매목록 -->
    <div class="row">
      <div class="offcanvas offcanvas-end" id="demo2">
        <div class="offcanvas-header" id="selllist-header">
          <h1 class="offcanvas-title" style="font-weight:bold;">판매 목록</h1>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
        </div>
        <div class="offcanvas-body container">
          <div class="row" style="border-bottom:1px solid black">
            <div class="col-3">
              상품 이름
            </div>
            <div class="col-3">
              상품 가격
            </div>
            <div class="col-3">
              수량
            </div>
            <div class="col-3">
              상태
            </div>
          </div>
          <div class="row" v-for="(sell, i) in sellList"
            :key="i" style="border-bottom:1px solid gray">
            <div class="col-3">
              {{sell.goodsName}}
            </div>
            <div class="col-3">
              {{sell.goodsPrice}}
            </div>
            <div class="col-3">
              {{sell.goodsQuantity}}
            </div>
            <div class="col-3">
              <button @click="changePurchase(sell.dealId)">
                <div v-if="sell.purchaseStatus == 10">결제완료</div>
                <div v-else-if="sell.purchaseStatus == 11">배송중</div>
                <div v-else-if="sell.purchaseStatus == 12">판매완료</div>
                <div v-else>구매취소</div>
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="container-fluid" style="margin-top:3vh;">
        <button @click="getSellList" class="btn-sell" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo2">
          <span>판매 목록</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/http.js';
import Offcanvas from 'bootstrap/js/dist/offcanvas'
import { mapGetters } from 'vuex'
import ChatModal from '@/components/Chat/ChatModal.vue'
import {eventBus} from '@/main.js';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
  name: "Nav",
  components:{
    ChatModal,
  },
  data() {
    return {
      meetings: Array,
      meetings2: Array,
      sellerId: String,
      meetingListSize : '',
      chatListSize : '',
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
      selectedReceiverId:'',
      mapHeight : 0,
      mapWidth : 0,
      height : '970',
      width : '1280',
    };
  },
  created(){
    eventBus.$on('openChat', () => {
      // 함수실행
      this.openChat();
    })
  },
  computed: {
    ...mapGetters(['credit'])
  },
  watch : {
    meetingListSize (newval, oldval){
      var con = document.getElementById('reddot');
      if(newval > oldval){
        con.style.display = "block"
      }else{
        con.style.display = "none"
      }
    },
  },
  mounted() {
    this.sellerId = localStorage.getItem('userId')
    setInterval(() => {
      http.get('/session/getlist/' + this.sellerId)
      .then((res)=>{
        this.meetings = res.data
        this.$store.commit('setMeetingListSize', this.meetings.length)
        this.meetingListSize = this.$store.getters.getMeetingListSize
      })
      this.getChatList2();
    }, 1000);
    
    // let socket = new SockJS("https://k5a404.p.ssafy.io:8877/ws");
    let socket = new SockJS("http://localhost:8877/ws");
    this.stompClient = Stomp.over(socket);
      this.stompClient.connect({}, () => {
        setInterval(() => {
          for(let i=0; i<this.roomList.length; i++){
            this.stompClient.subscribe('/sub/' + this.roomList[i].chatRoomId, (res) => { // 메시지 받기
            let jsonBody = JSON.parse(res.body);
            if(jsonBody.userId != this.userId && jsonBody.userId != this.selectedReceiverId){
              document.getElementById('reddot-chat').style.display="block";
            }
            });
          }
          }, 1000);
    });
    this.bsOffcanvas = new Offcanvas(this.$refs.offcanvasRight)
  },
  methods: {
    reddotChat(){
      document.getElementById('reddot-chat').style.display="block";
    },
    openChat(){
      // 클릭이벤트 줘서 오프캠버스 열기
      this.$refs.chatList.click();
    },
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
      document.getElementById('reddot-chat').style.display="none";
    },
    getChatList2(){
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
    enterRoom(inputChatRoomId, inputSenderId, inputReceiverId) {
      this.selectedChatRoomId = inputChatRoomId;
      if(inputSenderId == this.userId){
        this.selectedReceiverId = inputReceiverId;
      }else{
        this.selectedReceiverId = inputSenderId;
      }
    },
    chatOn(){
      this.$store.commit('setChat', true)
    },
    logout(){
      localStorage.removeItem('token')
      localStorage.removeItem('userId')
      localStorage.removeItem('userNickname')
      window.location.href="http://localhost:8080/"
      // window.location.href="https://k5a404.p.ssafy.io/"
    },
    purchase2(){
      this.$router.push({name:'Purchase'})
    },
    removeDot(){
      document.getElementById('reddot').style.display="none";
    },
    getBuyList(){
      http.get('/deal/buylist/' + localStorage.getItem('userId'))
      .then((res)=>{
        this.buyList = res.data;
      })
    },
    getSellList(){
      http.get('/deal/selllist/' + localStorage.getItem('userId'))
      .then((res)=>{
        this.sellList = res.data;
      })
    },
    changePurchase(dealId){
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
#show-modal .badge{
  padding: 5px 10px;
  border-radius: 50%;
  background: red;
  color: white;
}
.chat .badge{
  display: none;
  position: absolute;
  top: 41.5vh;
  left: 9.5vw;
  padding: 5px 10px;
  border-radius: 50%;
  background: red;
  color: white;
}
.btn-meeting .badge {
  display: none;
  position: absolute;
  top: 49.5vh;
  left: 9.5vw;
  padding: 5px 10px;
  border-radius: 50%;
  background: red;
  color: white;
}
#demo2{
  background-color: #eee;
  padding:0px;
}
#selllist-header{
  padding-top: 4.5vh;
  border-bottom: 1px solid black;
}
#demo1{
  background-color: #eee;
  padding:0px;
}
#buylist-header{
  padding-top: 4.5vh;
  border-bottom: 1px solid black;
}
#meet-row{
  border-bottom: 1px solid gray;
}
#meet-body{
  font-weight: bold;
  font-size: 20px;
}
#meet-buyer-id{
  margin-top: 0.5vh;
  text-align: center;
}
#meet-btn{
  border: 1px solid black;
  border-radius: 10px;
  padding: 5px;
}
#meet-btn:hover{
  background-color: rgb(200, 200, 200);
}
#demo{
  background-color: #eee;
  padding:0px;
}
#meet-header{
  padding-top: 4.5vh;
  border-bottom: 1px solid black;
}
#meet-table{
  border-collapse: collapse;
}
#demo0{
  background-color: #eee;
  padding:0px;
}
#show-modal{
  padding-left: 1.5vw;
  padding-bottom: 2vh;
  padding-top: 2vh;
  font-size: 20px;
  border-bottom: 1px solid gray;
  margin-left: 0px;
}
#show-modal:hover{
  background-color: rgb(211, 207, 207);
}
#chat-header{
  padding-top: 4.5vh;
  border-bottom: 1px solid black;
}
#credit-box{
  border-bottom: 1px solid black;
}
#credit{
  text-align: center;
  font-size: 20px;
}
#mycredit{
  padding-left: 0px;
  font-size: 20px;
}
#nickname{
  text-align: center;
  font-size: 22px;
  font-weight: bold;
}
#titlebox{
  border-bottom: 2px solid black;
  border-bottom-style: double;
  text-align: center;
  font-size: 50px;
}
.creditDiv{
  margin-top: 10vh;
  text-align: center;
}
.btnPurchase{
  border: 1px solid black;
  border-radius: 10px;
  width: 10vw;
  height: 5vh;
  background-image: linear-gradient(200deg,rgb(243, 243, 122),rgb(238, 241, 59),rgb(194, 194, 59));
  font-size: 20px;
  font-weight: bold;
}
.chat{
  display: inline-block;
  border: 1px solid black;
  border-radius: 10px;
  width: 10vw;
  height: 5vh;
  background-image: linear-gradient(190deg,#eee, gray);
  font-size: 20px;
  font-weight: bold;
}
.btn-meeting{
  display: inline-block;
  border: 1px solid black;
  border-radius: 10px;
  width: 10vw;
  height: 5vh;
  background-image: linear-gradient(190deg,#eee, gray);
  font-size: 20px;
  font-weight: bold;
}
.btn-meeting .text {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}
.btn-meeting .text:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}
.btn-meeting:hover .text {
  padding-right: 25px;
}
.btn-meeting:hover .text:after {
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
  border: 1px solid black;
  width: 5vw;
  border-radius: 10px;
  background-image: linear-gradient(200deg,rgb(224, 160, 160),rgb(240, 81, 81),rgb(246, 79, 79),rgb(248, 21, 21));
  opacity: 0.6;
  transition: 0.3s;
  color: white;
}
.btnLogout:hover {opacity: 1}
.btn-mystore{
  border: 1px solid black;
  border-radius: 10px;
  width: 10vw;
  height: 5vh;
  background-image: linear-gradient(190deg,#eee, gray);
  font-size: 20px;
  font-weight: bold;
}
#nav{
  top:0;
  position:absolute;
  width: 11.8vw;
  height: 100vh;
  left: 66.6vw;
  z-index:100;
  border-left: 1px solid black;
  border-bottom: 1px solid var(--color-grey-6);
  background-color: #eee;
  border: 2px solid black;
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