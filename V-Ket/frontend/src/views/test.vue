<template>
  <div id="unity">
    <Nav/>
    <div>

    <SignUp class="signup" v-if="isModalViewed" @close-modal="isModalViewed=false"/>
    </div>
    <div id="unity-container">
      <UnityGame v-if="showUnity"/>
    </div>
      <p>hi</p>
      <div class="btnClass">

      <button class="btn" @click="isModalViewed=true"><b>회원가입</b></button>
      </div>
    <button @click="logout">로그아웃</button>
  </div>
</template>

<script>
import UnityGame from '@/components/Unity/UnityGame.vue';
import Nav from '@/views/Nav.vue'
import SignUp from '@/components/User/SignUp.vue'
export default {

    components:{
        UnityGame,
        Nav,
        SignUp
    },
    data () {
      return {
        isModalViewed: false,
      }
    },
    methods: {
        
    logout(){
      localStorage.removeItem('token')
      localStorage.removeItem('userId')
      localStorage.removeItem('userNickname')
      this.$router.push({name:'Main'})
    },
    },
    computed : {
    showUnity : function(){
      if(this.$route.name === "MyPage" || this.$route.name === "CamSetting" || this.$route.name === "About"){
        return false;
      }
      return true;
    }
  }

}
</script>

<style scoped>
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
.btn{
  border: 1px solid black;
  background-color: #eee;
}
.btnClass{
  margin-left: 65vw;
}
</style>