<template>
  <div id="app" data-app>
    <router-view/>
    <UnityGame v-if="showUnity"/>
  </div>
</template>
<script>
import UnityGame from '@/components/Unity/UnityGame';
export default {
  name: 'App',
  components:{
    UnityGame
  },
  computed : {
    showUnity : function(){
      if(this.$route.name === "Main"){
        return false;
      }
      return true;
      // if(this.$route.name == "Unity"){
      //   return true;
      // }
      // return false;
    }
  }
}
</script>
<style scoped>

</style>