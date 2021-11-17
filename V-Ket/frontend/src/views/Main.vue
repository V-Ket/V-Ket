<template>
  <div>
    <v-img id = "background" src="images/background5.gif"></v-img>
    <v-img id="logo" src="images/logo/logo2.png"></v-img>
    <SignUp class="signup" v-if="isModalViewed" @close-modal="isModalViewed=false"/>
    <div class="login">
      <div class="idDiv">
        <b>아이디</b>
        <input v-model="userId" class="idInput" style="margin-left: 35px"/><br>
      </div>
      <div class="pwDiv">
        <b>비밀번호</b>
        <input v-model="userPassword" type="password" class="pwInput" style="margin-left: 20px"/><br>
      </div>
      <div class="buttons">
        <button class="btn" style="margin-right: 40px; margin-left: 40px" @click="login"><b>로그인</b></button>
        <button class="btn" @click="isModalViewed=true"><b>회원가입</b></button>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/http.js';
import SignUp from '@/components/User/SignUp.vue'
export default {
  name: "Main",
  components:{
    SignUp,
  },
  data () {
    return {
      userSeq: '',
      userId: '',
      userNickname: '',
      userPhone: '',
      userToken: '',
      userPassword: '',
      isModalViewed: false,
    }
  },
  computed:{
    isLogin: function(){
      return localStorage.getItem('token');
    }
  },
  methods:{
    login() {
      const body = {
        userId : this.userId,
        userPassword : this.userPassword
      }
      http.post('/user/login' , body)
      .then((res) => {
        localStorage.setItem('token', res.data.accessToken)
        localStorage.setItem('userId', res.data.userId)
        localStorage.setItem('userNickname', res.data.userNickname)
        this.$store.commit('setCredit', res.data.userCredit)
        this.$router.push({name:'Select'})
      })
      .catch((err) => {
        this.$swal({
          icon: 'error',
          title: '아이디와 비밀번호를 확인해주세요.',
          text: err
        })
      });
    },
    unitytest(){
      this.$router.push({name:'Unity'})
    }
  }
};
</script>

<style scoped>
#logo{
  position: absolute;
  bottom: 0vh;
}
#background {
  width: 100vw;
  height: 100vh;
}
.signup{
  position:absolute;
  border: 2px solid black;
  border-radius: 10px;
  left: 50%;
  top: 10%;
  width: 30vw;
  height: 50vh;
  transform: translate(-50%,10%);
  background-color: #eee;
}
.tt{
  position:absolute;
  border-radius: 10px;
  left: 50%;
  top: 10%;
  width: auto;
  height: auto;
  transform: translate(-250%,130%);
}
.login{
  position:absolute;
  border: 2px solid black;
  border-radius: 10px;
  left: 50%;
  top: 52%;
  width: 25vw;
  height: 20vh;
  transform: translate(-50%, 90%);
  background-color: #eee;
}
.idInput{
  position:absolute;
  border: 2px solid black;
  border-radius: 7px;
}
.pwInput{
  position:absolute;
  border: 2px solid black;
  border-radius: 7px;
}
.idDiv{
  margin-left: 5vw;
  margin-top: 3vh;
}
.pwDiv{
  margin-left: 5vw;
  margin-top: 1.5vh;
}
.buttons{
  margin-left: 5vw;
  margin-top: 2vh;
}
.btn{
  border: 1px solid black;
  background-color: #eee;
}
.chat{
  position:absolute;
  border: 2px solid black;
  border-radius: 10px;
  left: 50%;
  top: 10%;
  background-color: #eee;
}
</style>