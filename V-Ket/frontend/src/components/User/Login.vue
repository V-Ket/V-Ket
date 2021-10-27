<template>
  <div>
    <input v-model="userId" />
    <input v-model="userPassword" type="password" />
    <button @click="login()">로그인</button>
  </div>
</template>
<script>
import http from '@/http.js';
// import axios from 'axios';

export default {
  name: 'Login',

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
    login() {
      const body = {
        userId : this.userId,
        userPassword : this.userPassword
      }
      http.post('/user/login' , body)
      // axios.post('http://localhost:8877/user/login'+body)
      .then((res) => {
        console.log('로그인됨')
        localStorage.setItem('token', res.data.accessToken)
        localStorage.setItem('userId', res.data.userId)
        localStorage.setItem('userNickname', res.data.userNickname)
      });
      setTimeout(function(){
        location.reload()
      },100)
    },
  },
};
</script>
