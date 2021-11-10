<template>
  <div class="pp">
    <div class="card-body bg-white mt-0 shadow">
      <p style="font-weight: bold">카카오페이 현재 사용가능</p>
      <label class="box-radio-input"><input type="radio" name="cp_item" value="5000" v-model="picked"><span>5,000원</span></label>
      <label class="box-radio-input"><input type="radio" name="cp_item" value="10000" v-model="picked"><span>10,000원</span></label>
      <label class="box-radio-input"><input type="radio" name="cp_item" value="15000" v-model="picked"><span>15,000원</span></label>
      <label class="box-radio-input"><input type="radio" name="cp_item" value="20000" v-model="picked"><span>20,000원</span></label>
      <label class="box-radio-input"><input type="radio" name="cp_item" value="25000" v-model="picked"><span>25,000원</span></label>
      <label class="box-radio-input"><input type="radio" name="cp_item" value="30000" v-model="picked"><span>30,000원</span></label>
      <label class="box-radio-input"><input type="radio" name="cp_item" value="35000" v-model="picked"><span>35,000원</span></label>
      <label class="box-radio-input"><input type="radio" name="cp_item" value="40000" v-model="picked"><span>40,000원</span></label>
      <label class="box-radio-input"><input type="radio" name="cp_item" value="50000" v-model="picked"><span>50,000원</span></label>
      <p  style="color: #ac2925; margin-top: 30px">카카오페이의 최소 충전금액은 5,000원이며 <br/>최대 충전금액은 50,000원 입니다.</p>
      <span>충전할 금액 : {{picked}}</span>
      <button type="button" class="btn btn-lg btn-block  btn-custom" id="charge_kakao" @click="requestPay()">충 전 하 기</button>
    </div>
    <div>
      <input type="text">
      <button @click="goUni">유니티로 돌아가기</button>
    </div>
  </div>
</template>
<script>
// import axios from 'axios'
import http from '@/http.js'

export default {
  name: 'Purchase',
  watch:{
    picked(){
      console.log('watch : ', this.picked)
    }
  },
  data() {
    return{
      money : '',
      picked:'',
      impCode : '',
      order: {
        name : '',
        amount : null,
        buyer_tel : '',
      }
    }
  },
  methods: {
    goUni(){
      this.$router.push({name:'Unity'})
    },
    requestPay(){
      var IMP = window.IMP;
      IMP.init('imp83028829');
      var money = this.picked
      console.log(money);
      IMP.request_pay({
          pg: 'kakao',
          merchant_uid: 'merchant_' + new Date().getTime(),
          name: '주문명 : 주문명 설정',
          amount: money,
          buyer_email: 'iamport@siot.do',
          buyer_name: localStorage.getItem('userId'),
          buyer_tel: '010-1234-5678',
          buyer_addr: '인천광역시 부평구',
          buyer_postcode: '123-456'
      }, function (rsp) {
          console.log(rsp);
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
              http.post('/user/credit', body)
              .then((res)=>{
                if(res.status == 201){
                  alert('충전이 완료되었습니다.')
                }else{
                  alert('충전 오류')
                }
              })
          } else {
              msg = '결제에 실패하였습니다.';
              msg += '에러내용 : ' + rsp.error_msg;
          }
          alert(msg);
          setTimeout(function(){
            location.reload()
          },100)
      });
    }
  }
}
</script>
<style scoped>
.pp{
  width: 66.6vw;
}
</style>