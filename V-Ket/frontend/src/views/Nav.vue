<template>
  <div id="nav">
    <SignUp class="signup" v-if="isModalViewed" @close-modal="isModalViewed=false"/>
    
    <div class="nav-container">
      <div id="user-container">
        <div id="user-name"><p>{{isUser ? user + '님' : '로그인이 필요합니다.'}}</p></div>
        <p>dddddd</p>
        <button class="btn" @click="isModalViewed=true"><b>회원가입</b></button>
        <div id="menu-container">
          <button id="menu-dropdown" @click="showMenu = !showMenu">보여라
          </button>
          <ul
            id="menus"
            v-if="this.user === null"
          >
            <li><button @click="handleAbout">About</button></li>
            <li><button @click="handleLogin">로그인</button></li>
            <li><button @click="handleJoin">회원가입</button></li>
          </ul>
          <ul
            id="menus"
            v-else
          >
            <li><button @click="handleAbout">About</button></li>
            <li><button @click="handleUnity">학교가기</button></li>
            <li><button @click="handleMypage">마이페이지</button></li>
            <li><button @click="handleLogout">로그아웃</button></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SignUp from '@/components/User/SignUp.vue'
export default {
  name: "Nav",
  components:{
    SignUp
  },
  data() {
    return {
      showMenu: false,
      // user:{},
      user : '나다',
      isUser: true,
        isModalViewed: false,
    };
  },
  created(){
    // this.user=this.$store.getters.getUser;
    // if(this.user===null){
    //   window.location.href="https://k4b107.p.ssafy.io:8000/login"
    // }
  },
  mounted() {
    document.addEventListener(
      "click",
      function (event) {
        if (event.target.closest("#menu-container")) return;
        this.showMenu = false;
      }.bind(this)
    );
  },
  methods: {
    showMenus() {
      this.showMenu = !this.showMenu;
    },
    handleMypage(){
      // window.location.href="https://k4b107.p.ssafy.io:8000/mypage"
    },
    handleLogout(){
      this.$router.push('/');
    },
    handleAbout(){
      window.location.href="http://localhost:8080/purchase"
    },
    handleLogin(){
      this.$router.push('/');
    },
    // handleJoin(){
    //   this.$router.push('/join');
    // },
    handleUnity(){
      this.$router.push({name : 'Unity'});
    }
  },
};
</script>

<style scoped src="../css/Nav.css">
</style>
<style>
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
</style>