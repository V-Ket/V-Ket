<template>
  <div>
    아이디: <input v-model="userId"/><br>
    비밀번호: <input v-model="userPassword"/><br>
    닉네임: <input v-model="userNickname"/><br>
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
      userPassword: ''
    };
  },
  methods: {
    sighUp() {
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
      location.reload();
    },
  },
};
</script>
