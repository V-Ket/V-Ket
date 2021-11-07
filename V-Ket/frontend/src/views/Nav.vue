<template>
  <div id="nav">
    <div>
      <div class="creditDiv">
        보유 credit : 10,000
      </div>
      <button class="btnPurchase" @click="purchase">충전하기</button>
    </div>
    <div>
      <Chat class="chat"/>
    </div>
    <div>
      <button class="btnLogout" @click="logout">로그아웃</button>
    </div>
  </div>
</template>

<script>
import Chat from '@/components/Chat/Chat.vue'
export default {
  name: "Nav",
  components:{
    Chat
  },
  data() {
    return {

    };
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
    logout(){
      localStorage.removeItem('token')
      localStorage.removeItem('userId')
      localStorage.removeItem('userNickname')
      // this.$router.push({name:'Main'})
      window.location.href="http://localhost:8080/"
    },
    purchase(){
      window.open("http://localhost:8080/purchase", "충전하기", "width=800, height=700")
    },
  },
};
</script>

<style scoped>
.creditDiv{
  margin-top: 10vh;
  text-align: center;
}
.btnPurchase{
  border: 1px solid black;
  margin-left: 1vw;
}
.chat{
  margin-top: 10vh;
  margin-left: 1vw;
}
.btnLogout{
  margin-top: 10vh;
  border: 1px solid black;
  margin-left: 1vw;
}
#nav{
  position:absolute;
  width: 15vw;
  height: 100vh;
  left: 70.3vw;
  z-index:100;
  border-left: 1px solid black;
  border-bottom: 1px solid var(--color-grey-6);
  background-color: #eee;
  /* background-color: saddlebrown; */
  border: 1px solid black;
}
#nav > .nav-container{
  width : 80%;
  height: 100%;
  margin : auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>