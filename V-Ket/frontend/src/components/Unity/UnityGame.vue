<template>
<div id="unity-game" :class="{'small-map':!showMap}" @click="goUnity">
        <div id="game-container">
            <div id="mini-map-alt" class="map-alt" v-if="!showMap">
                <div class="mini-alt">
                    <v-icon id="mini-alt-icon">fas fa-sign-in-alt</v-icon>
                    <p id="mini-alt-text">학교로 <br> 돌아가기</p>
                </div>
            </div>
            <div id="keydown-map-alt" class="map-alt" v-if="keydownAlt">
                <v-icon id="keydown-alt-icon">fas fa-exclamation-circle</v-icon>
                <p id="keydown-alt-text">캐릭터를 움직이기 위해선 게임 화면을 클릭해 주세요.</p>
            </div>
            <unity id="bts-unity" 
            src="./unity/Build/unity.json" 
            :width = width
            :height = height
            unityLoader="./unity/Build/UnityLoader.js" 
            :hideFooter="true"
            ref="hookInstance"></unity>
        </div>
        <div id="unity-school-name" hidden></div>
        <div id="unity-object-name" hidden></div>
    </div>
</template>

<script>
  import Unity from 'vue-unity-webgl';
  
  export default {
		name : "UnityGame",
		components : {Unity},
    data() {
        return {
            user: {},
            objectName : '',
            schoolName : '',
            linked : false,
            height : '700',
            width : '950',
            interval : '',
            mapHeight : 0,
            mapWidth : 0,
            unityFocus : false,
            keydownAlt : false,
            enterMap : false,
        }
    },
    created(){
      // if(this.$store.state.user===null){
      //   this.$router.push('/login');
      // }
      // this.schoolName=this.$store.state.schoolName;
      // this.user=this.$store.getters.getUser;
    },
    mounted() {
        this.enterMap = false;
        this.schoolName = "";
        // this.$store.commit('setIsSubmit',true);
        // setTimeout(()=>{
        //   this.$store.commit('setIsSubmit',false);
        // },5000)
        this.mapHeight = document.querySelector('#unity-game-container').getBoundingClientRect().height;
        this.mapWidth = document.querySelector('#unity-game-container').getBoundingClientRect().width;
        
        // const top = document.querySelector('#nav').getBoundingClientRect().height + 1;
        const target = document.querySelector('#unity-game-container')
        const targetRect = target.getBoundingClientRect();
        this.width = targetRect.width;
        // this.height = document.querySelector('#unity').getBoundingClientRect().height-top;
        this.height = window.innerHeight-102;
        const unity = document.querySelector('#unity-game');
        unity.style.transform = `translate(${targetRect.left}px,102px)`;
        document.addEventListener(
            "click",
            function (event) {
                if (event.target.closest("#game-container")){ // 유니티 가능
                    if(this.$refs.hookInstance !== undefined) this.$refs.hookInstance.message('Game Manager','focusing',"true");
                    this.unityFocus = true;
                    this.keydownAlt = false;
                }else{ // 윈도우 인풋 가능
                    if(this.$refs.hookInstance !== undefined)  this.$refs.hookInstance.message('Game Manager','focusing',"false");
                    this.unityFocus = false;
                }
            }.bind(this)
        );
        document.addEventListener(
            "keydown",
            function () {
                if(this.$route.name === "Unity" && !this.unityFocus && this.enterMap){
                    this.keydownAlt = true;
                }
            }.bind(this)
        );
    },
    updated(){
        window.addEventListener('resize', () => {
            if(this.showMap && (this.mapHeight !== document.querySelector('#unity-game-container').getBoundingClientRect().height || this.mapWidth !== document.querySelector('#unity-game-container').getBoundingClientRect().width)){
                // const top = document.querySelector('#nav').getBoundingClientRect().height + 1;
                const target = document.querySelector('#unity-game-container')
                const targetRect = target.getBoundingClientRect();
                this.width = targetRect.width;
                this.height = window.innerHeight-102;
                const unity = document.querySelector('#unity-game');
                unity.style.transform = `translate(${targetRect.left}px,102px)`;
                this.mapHeight = document.querySelector('#unity-game-container').getBoundingClientRect().height;
                this.mapWidth = document.querySelector('#unity-game-container').getBoundingClientRect().width;
            }
        })
    },
    destroyed(){
        clearInterval(this.interval);
    },
    computed : {
        showMap : function(){
            if(this.$route.name === 'Unity'){
                return true;
            }else{
                return false;
            }
        }
    },
    watch : {
        showMap : function(newVal){
            if(newVal){
                // const top = document.querySelector('#nav').getBoundingClientRect().height + 1;
                const target = document.querySelector('#unity-game-container')
                const targetRect = target.getBoundingClientRect();
                this.width = targetRect.width;
                this.height = window.innerHeight-102;
                const unity = document.querySelector('#unity-game');
                unity.style.transform = `translate(${targetRect.left}px,102px)`;
            }else{
                this.width = '150';
                this.height = '100';
            }
        }
    },
    methods : {
        goUnity(){
            if(!this.showMap){
              console.log('두번실행')
                this.$router.push({name : "Unity"});
            }
        },
    }
}
</script>
<style scoped src="../../css/UnityGame.css">
</style>