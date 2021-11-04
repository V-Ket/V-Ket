<template>
  <div>
      <UnityGame>
      </UnityGame>
      <p>hi</p>
    <button @click="logout">로그아웃</button>
  </div>
</template>

<script>
import UnityGame from '@/components/Unity/UnityGame.vue';

export default {

    components:{
        UnityGame
    },
    methods: {
        
    logout(){
      localStorage.removeItem('token')
      localStorage.removeItem('userId')
      localStorage.removeItem('userNickname')
      this.$router.push({name:'Main'})
    },
    },

}
</script>

<style>

</style>