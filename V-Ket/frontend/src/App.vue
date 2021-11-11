<template>
  <div id="app" data-app>
    <router-view/>
    <UnityGame v-if="showUnity"/>
    <Nav v-if="showNav"/>
  </div>
</template>
<script>
import UnityGame from '@/components/Unity/UnityGame';
import Nav from '@/views/Nav.vue'
export default {
  name: 'App',
  components:{
    UnityGame,
    Nav
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
    },
    showNav : function(){
      if(this.$route.name === "Main" || this.$route.name === "Select" || this.$route.name === "Purchase"){
        return false;
      }
      return true;
    }
  }
}
</script>
<style scoped>

</style>