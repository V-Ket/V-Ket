<template>
  <div>
    아이디: <input v-model="userId"/>
    <button @click="idValid()">중복확인</button><br>
    비밀번호: <input v-model="userPassword"/><br>
    닉네임: <input v-model="userNickname"/>
    <button @click="nicknameValid()">중복확인</button><br>
    전화번호: <input v-model="userPhone"/><br>
    <button @click="sighUp()"> 회원가입 </button>
  </div>
</template>
<script>
import http from '@/http.js';

export default {
  name: 'SignUp',

  data() {
    return {
      userSeq: '',
      userId: '',
      userNickname: '',
      userPhone: '',
      userToken: '',
      userPassword: '',
      valid_id : false,
      valid_nickname: false,
    };
  },
  methods: {
    sighUp() {
      if(this.valid_id && this.valid_nickname){
        const body = {
        userId : this.userId,
        userPassword : this.userPassword,
        userNickname : this.userNickname,
        userPhone : this.userPhone
        }
        http.post('/user/signUp', body)
        .then((res)=>{
          alert(res+"가입성공!")
        })
      }else{
        alert('중복확인을 해주세요')
      }
    },
    idValid(){
      http.get('/user/userId/' + this.userId)
      .then((res)=>{
        console.log(res.status)
        if(res.status==201){
          this.valid_id = true;
          alert('사용가능한 아이디입니다.')
        }else{
          this.valid_id = false;
          alert('아이디가 중복입니다.')
        }
      })
    },
    nicknameValid(){
      http.get('/user/userNickname/'+this.userNickname)
      .then((res)=>{
        if(res.status==201){
          this.valid_nickname = true;
          alert('사용가능한 닉네임입니다.')
        }else{
          this.valid_nickname = false;
          alert('닉네임이 중복입니다.')
        }
      })
    }
  },
};
</script>
