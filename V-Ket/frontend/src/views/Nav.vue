<template>
  <div id="nav">
    <div>
      <div class="creditDiv">
        보유 credit : 10,000
      </div>
      <button class="btnPurchase" @click="purchase">충전하기(새창)</button>
    </div>
    <div>
      <Chat class="chat"/>
    </div>
    <div>
      <button class="btnLogout" @click="logout">로그아웃</button>
    </div>
    <div>
      <button class="btnPurchase2" @click="purchase2">충전하기(라우터)</button>
    </div>
    <div>
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
        <button @click="removeDot" class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo">
          미팅 목록
        </button>
      </div>
    </div>
    <div>
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
        <button @click="getBuyList" class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo1">
          구매 목록
        </button>
      </div>
    </div>
    <div>
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
        <button @click="getSellList" class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo2">
          판매 목록
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import Chat from '@/components/Chat/Chat.vue'
import http from '@/http.js';

export default {
  name: "Nav",
  components:{
    Chat
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
    };
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
  margin-left: 1vw;
}
.chat{
  margin-top: 10vh;
  margin-left: 1vw;
}
.btnLogout{
  margin-top: 10vh;
  border: 1px solid black;
  margin-left: 1vw;
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