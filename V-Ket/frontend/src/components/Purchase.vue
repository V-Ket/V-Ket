<template>
  <div class="purchase">
    <div id="out">
      <button id="out-btn" @click="goUni">나가기</button>
    </div>
    <div class="container-fluid bg-gradient p-5">
      <div style="margin-bottom: 30px; font-weight: bold; font-size: 20px; text-align: center">
        <div style="color: #ac2925"></div>
      </div>
      <div class="row m-auto text-center w-75">
        <div class="col-4 princing-item red">
          <div class="pricing-divider ">
            <h3 class="text-light">P A Y C O</h3>
            <svg class='pricing-divider-img' enable-background='new 0 0 300 100' height='100px' id='Layer_1' preserveAspectRatio='none' version='1.1' viewBox='0 0 300 100' width='300px' x='0px' xml:space='preserve' xmlns:xlink='http://www.w3.org/1999/xlink' xmlns='http://www.w3.org/2000/svg' y='0px'>
              <path class='deco-layer deco-layer--1' d='M30.913,43.944c0,0,42.911-34.464,87.51-14.191c77.31,35.14,113.304-1.952,146.638-4.729c48.654-4.056,69.94,16.218,69.94,16.218v54.396H30.913V43.944z' fill='#FFFFFF' opacity='0.6'></path>
              <path class='deco-layer deco-layer--2' d='M-35.667,44.628c0,0,42.91-34.463,87.51-14.191c77.31,35.141,113.304-1.952,146.639-4.729c48.653-4.055,69.939,16.218,69.939,16.218v54.396H-35.667V44.628z' fill='#FFFFFF' opacity='0.6'></path>
              <path class='deco-layer deco-layer--3' d='M43.415,98.342c0,0,48.283-68.927,109.133-68.927c65.886,0,97.983,67.914,97.983,67.914v3.716H42.401L43.415,98.342z' fill='#FFFFFF' opacity='0.7'></path>
              <path class='deco-layer deco-layer--4' d='M-34.667,62.998c0,0,56-45.667,120.316-27.839C167.484,57.842,197,41.332,232.286,30.428c53.07-16.399,104.047,36.903,104.047,36.903l1.333,36.667l-372-2.954L-34.667,62.998z' fill='#FFFFFF'></path>
            </svg>
          </div>
          <div class="card-body bg-white mt-0 shadow">
            <p style="font-weight: bold">서비스 준비 중입니다.</p>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="5000"><span>5,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="10000"><span>10,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="15000"><span>15,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="20000"><span>20,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="25000"><span>25,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="30000"><span>30,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="35000"><span>35,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="40000"><span>40,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="50000"><span>50,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="100000"><span>100,000원</span></label>
            <p></p>
            <!-- <p  style="color: #ac2925; margin-top: 30px">페이코의 최소 충전금액은 5,000원이며 <br/>최대 충전금액은 50,000원 입니다.</p> -->
            <button type="button" class="btn btn-lg btn-block  btn-custom ">충 전 하 기</button>
          </div>
        </div>
        <div class="col-4 princing-item blue">
          <div class="pricing-divider ">
            <h3 class="text-light">카카오페이</h3>
            <svg class='pricing-divider-img' enable-background='new 0 0 300 100' height='100px' id='Layer_1' preserveAspectRatio='none' version='1.1' viewBox='0 0 300 100' width='300px' x='0px' xml:space='preserve' xmlns:xlink='http://www.w3.org/1999/xlink' xmlns='http://www.w3.org/2000/svg' y='0px'>
              <path class='deco-layer deco-layer--1' d='M30.913,43.944c0,0,42.911-34.464,87.51-14.191c77.31,35.14,113.304-1.952,146.638-4.729c48.654-4.056,69.94,16.218,69.94,16.218v54.396H30.913V43.944z' fill='#FFFFFF' opacity='0.6'></path>
              <path class='deco-layer deco-layer--2' d='M-35.667,44.628c0,0,42.91-34.463,87.51-14.191c77.31,35.141,113.304-1.952,146.639-4.729c48.653-4.055,69.939,16.218,69.939,16.218v54.396H-35.667V44.628z' fill='#FFFFFF' opacity='0.6'></path>
              <path class='deco-layer deco-layer--3' d='M43.415,98.342c0,0,48.283-68.927,109.133-68.927c65.886,0,97.983,67.914,97.983,67.914v3.716H42.401L43.415,98.342z' fill='#FFFFFF' opacity='0.7'></path>
              <path class='deco-layer deco-layer--4' d='M-34.667,62.998c0,0,56-45.667,120.316-27.839C167.484,57.842,197,41.332,232.286,30.428c53.07-16.399,104.047,36.903,104.047,36.903l1.333,36.667l-372-2.954L-34.667,62.998z' fill='#FFFFFF'></path>
            </svg>
          </div>
          <div class="card-body bg-white mt-0 shadow">
            <p style="font-weight: bold">카카오페이 현재 사용가능</p>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="5,000" v-model="picked"><span>5,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="10,000" v-model="picked"><span>10,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="15,000" v-model="picked"><span>15,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="20,000" v-model="picked"><span>20,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="25,000" v-model="picked"><span>25,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="30,000" v-model="picked"><span>30,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="35,000" v-model="picked"><span>35,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="40,000" v-model="picked"><span>40,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="50,000" v-model="picked"><span>50,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="100,000" v-model="picked"><span>100,000원</span></label>
            <p></p>
            <span v-if="picked != null">충전할 금액 : {{picked}} 원</span>
            <p></p>
            <!-- <p  style="color: #ac2925; margin-top: 30px">카카오페이의 최소 충전금액은 5,000원이며 <br/>최대 충전금액은 50,000원 입니다.</p> -->
            <button type="button" class="btn btn-lg btn-block  btn-custom" id="charge_kakao" @click="requestPay()">충 전 하 기</button>
          </div>
        </div>
        <div class="col-4 princing-item green">
          <div class="pricing-divider ">
            <h3 class="text-light">네이버페이</h3>
            <svg class='pricing-divider-img' enable-background='new 0 0 300 100' height='100px' id='Layer_1' preserveAspectRatio='none' version='1.1' viewBox='0 0 300 100' width='300px' x='0px' xml:space='preserve' xmlns:xlink='http://www.w3.org/1999/xlink' xmlns='http://www.w3.org/2000/svg' y='0px'>
              <path class='deco-layer deco-layer--1' d='M30.913,43.944c0,0,42.911-34.464,87.51-14.191c77.31,35.14,113.304-1.952,146.638-4.729c48.654-4.056,69.94,16.218,69.94,16.218v54.396H30.913V43.944z' fill='#FFFFFF' opacity='0.6'></path>
              <path class='deco-layer deco-layer--2' d='M-35.667,44.628c0,0,42.91-34.463,87.51-14.191c77.31,35.141,113.304-1.952,146.639-4.729c48.653-4.055,69.939,16.218,69.939,16.218v54.396H-35.667V44.628z' fill='#FFFFFF' opacity='0.6'></path>
              <path class='deco-layer deco-layer--3' d='M43.415,98.342c0,0,48.283-68.927,109.133-68.927c65.886,0,97.983,67.914,97.983,67.914v3.716H42.401L43.415,98.342z' fill='#FFFFFF' opacity='0.7'></path>
              <path class='deco-layer deco-layer--4' d='M-34.667,62.998c0,0,56-45.667,120.316-27.839C167.484,57.842,197,41.332,232.286,30.428c53.07-16.399,104.047,36.903,104.047,36.903l1.333,36.667l-372-2.954L-34.667,62.998z' fill='#FFFFFF'></path>
            </svg>
          </div>
          <div class="card-body bg-white mt-0 shadow">
            <p style="font-weight: bold">서비스 준비 중입니다.</p>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="5000"><span>5,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="10000"><span>10,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="15000"><span>15,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="20000"><span>20,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="25000"><span>25,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="30000"><span>30,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="35000"><span>35,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="40000"><span>40,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="50000"><span>50,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="50000"><span>100,000원</span></label>
            <p></p>
            <button type="button" class="btn btn-lg btn-block  btn-custom" id="charge_naver">충 전 하 기 </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import http from '@/http.js'

export default {
  name: 'Purchase',
  watch:{
    picked(){
      console.log('watch')
    }
  },
  data() {
    return{
      money : '',
      picked: null,
      impCode : '',
      order: {
        name : '',
        amount : null,
        buyer_tel : '',
      },
      changedCredit:'',
    }
  },
  methods: {
    goUni(){
      this.$router.push({name:'Unity'})
    },
    requestPay(){
      var IMP = window.IMP;
      IMP.init('imp83028829');
      var money = this.picked.replace(',','')
      IMP.request_pay({
        pg: 'kakao',
        merchant_uid: 'merchant_' + new Date().getTime(),
        name: '주문명 : v-ket credit',
        amount: money,
        buyer_email: 'iamport@siot.do',
        buyer_name: localStorage.getItem('userId'),
        buyer_tel: '010-1234-5678',
        buyer_addr: '서울특별시 강남구',
        buyer_postcode: '123-456'
      }, async function (rsp) {
          var msg = '';
          if (rsp.success) {
              msg = '결제가 완료되었습니다.';
              msg += '고유ID : ' + rsp.imp_uid;
              msg += '상점 거래ID : ' + rsp.merchant_uid;
              msg += '결제 금액 : ' + rsp.paid_amount;
              msg += '카드 승인번호 : ' + rsp.apply_num;
              const body = {
                userId : localStorage.getItem('userId'),
                credit : money
              }
              await http.post('/user/credit', body)
              .then((res)=>{
                this.changedCredit = res.data
              })
          } else {
              msg = '결제에 실패하였습니다.';
              msg += '에러내용 : ' + rsp.error_msg;
              this.changedCredit = this.$store.getters.credit
          }
          console.log(msg)
        this.$store.commit('setCredit', this.changedCredit)
      }.bind(this));
    }
  }
}
</script>
<style scoped>
.purchase{
  width: 66.6vw;
}
#out{
  position : absolute;
  margin-top: 2vh;
  margin-left: 60vw;
}
#out-btn{
  border: 1px solid black;
  padding: 1vh;
  border-radius: 10px;
  font-weight: bold;
  color: white;
  background-color: brown;
}
.bg-gradient {
  background: #C9D6FF;
  background: -webkit-linear-gradient(to right, #E2E2E2, #C9D6FF);
  background: linear-gradient(to right, #E2E2E2, #C9D6FF);
}
ul li {
  margin-bottom:1.4rem;
}
.pricing-divider {
  border-radius: 20px;
  background: #C64545;
  padding: 1em 0 4em;
  position: relative;
}
.blue .pricing-divider{
  background: #e7da20;
}
.green .pricing-divider {
  background: #1AA85C;
}
.red b {
  color:#C64545
}
.blue b {
  color:#2D5772
}
.green b {
  color:#1AA85C
}
.pricing-divider-img {
  position: absolute;
  bottom: -2px;
  left: 0;
  width: 100%;
  height: 80px;
}
.deco-layer {
  -webkit-transition: -webkit-transform 0.5s;
  transition: transform 0.5s;
}
.btn-custom  {
  background:#C64545; color:#fff; border-radius:20px
}

.img-float {
  width:50px; position:absolute;top:-3.5rem;right:1rem
}

.princing-item {
  transition: all 150ms ease-out;
}
.princing-item:hover {
  transform: scale(1.05);
}
.princing-item:hover .deco-layer--1 {
  -webkit-transform: translate3d(15px, 0, 0);
  transform: translate3d(15px, 0, 0);
}
.princing-item:hover .deco-layer--2 {
  -webkit-transform: translate3d(-15px, 0, 0);
  transform: translate3d(-15px, 0, 0);
}
.box-radio-input input[type="radio"]{
  display:none;
}

.box-radio-input input[type="radio"] + span{
  display:inline-block;
  background:none;
  border:1px solid #dfdfdf;
  padding:0px 10px;
  text-align:center;
  height:35px;
  line-height:33px;
  font-weight:500;
  cursor:pointer;
  width: 10vw;
}

.box-radio-input input[type="radio"]:checked + span{
  border:1px solid #23a3a7;
  background:#23a3a7;
  color:#fff;
}
</style>