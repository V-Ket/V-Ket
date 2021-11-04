<template>
  <div id="main">
    <img alt="Vue logo" src="../assets/logo.png" />
    <!-- <HelloWorld msg="Welcome to Your Vue.js App" /> -->
    <div v-if="isLogin">
      <button @click="logout"> 로그아웃 </button>
    </div>
    <div v-else>
      <Login/>
      <p> 회원가입 </p>
      <SignUp/>
    </div>

    <Chat/>
    <button @click="unity">unity</button>
    
  </div>
</template>

<script>
// import HelloWorld from "../components/HelloWorld.vue";
import Login from '../components/User/Login.vue'
import SignUp from '../components/User/SighUp.vue'
import Chat from '../components/Chat/Chat.vue'

export default {
  name: "Main",
  components: {
    // HelloWorld,
    Login,
    SignUp,
    Chat
  },
  computed:{
    isLogin: function(){
      return localStorage.getItem('token');
    }
  },
  methods:{
    logout(){
      localStorage.removeItem('token')
      localStorage.removeItem('userId')
      localStorage.removeItem('userNickname')
      location.reload()
    },
    unity(){
      
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

</style>