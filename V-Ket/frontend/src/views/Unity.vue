<template>
  <div id="unity">
    <div>
      <SignUp class="signup" v-if="isModalViewed" @close-modal="isModalViewed=false"/>
    </div>
    <div id="unity-container">
      <div id="unity-game-container">
        <div id="game-container"></div>
      </div>
    </div>
    <!-- <p>hi</p>
    <div class="btnClass">
      <button class="btn" @click="isModalViewed=true"><b>회원가입</b></button>
    </div>
    <button @click="logout">로그아웃</button> -->
  </div>
</template>

<script>
// import UnityGame from '@/components/Unity/UnityGame.vue';
import SignUp from '@/components/User/SignUp.vue'
export default {

    components:{
        // UnityGame,
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
#unity{
  display: flex;
  /* height : 100vh; */
  overflow: hidden;
  flex-direction: column;
  -ms-user-select: none; 
  -moz-user-select: -moz-none;
  -khtml-user-select: none;
  -webkit-user-select: none;
  user-select: none;
}
#unity-container{
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: stretch;
  width : 66.6vw;
  height : 100%;
  margin : 0 auto;
  text-align: center;
  flex : 1;
}
#unity-game-container{
  width : 100%;
}
#unity-main{
  width: 100%;
  flex-grow: 9;
  flex-basis: 0;
}
</style>