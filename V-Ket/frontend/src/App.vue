<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png" />
    <HelloWorld msg="Welcome to Your Vue.js App" />
    <div v-if="isLogin">
      <button @click="logout"> 로그아웃 </button>
    </div>
    <div v-else>
      <Login/>
      <p> 회원가입 </p>
      <SignUp/>
    </div>
    <ChatRoomList/>
    <ChatRoom/>
  </div>
</template>

<script>
import HelloWorld from "./components/HelloWorld.vue";
import Login from './components/User/Login.vue'
import SignUp from './components/User/SighUp.vue'
import ChatRoomList from './components/Chat/ChatRoomList.vue'
import ChatRoom from './components/Chat/ChatRoom.vue'

export default {
  name: "App",
  components: {
    HelloWorld,
    Login,
    SignUp,
    ChatRoomList,
    ChatRoom
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
