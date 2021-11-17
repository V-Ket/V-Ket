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
    },
    showNav : function(){
      if(this.$route.name === "Main" || this.$route.name === "Select"){
        return false;
      }
      return true;
    }
  }
}
</script>
<style scoped>

</style>