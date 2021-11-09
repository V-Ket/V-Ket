<template>
<div id="unity-game" :class="{'small-map':!showMap}" @click="goUnity">
        <div id="game-container">
            <div id="mini-map-alt" class="map-alt" v-if="!showMap">
                <div class="mini-alt">
                    <p id="mini-alt-text">학교로 <br> 돌아가기</p>
                </div>
            </div>
            <div id="keydown-map-alt" class="map-alt" v-if="keydownAlt">
                <v-icon id="keydown-alt-icon">fas fa-exclamation-circle</v-icon>
                <p id="keydown-alt-text">캐릭터를 움직이기 위해선 게임 화면을 클릭해 주세요.</p>
            </div>
            <unity id="vket-unity" 
            src="./unity/Build/unity.json" 
            width = "1080"
            height = "720"
            unityLoader="./unity/Build/UnityLoader.js" 
            :hideFooter="true"
            ref="hookInstance">
            </unity>
            <button @click="unityHook">시~작~</button>
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
            height : '720',
            width : '1080',
            interval : '',
            mapHeight : 0,
            mapWidth : 0,
            unityFocus : false,
            keydownAlt : false,
            enterMap : false,
            userId : '',
            userNickname : '',
            userChar : '',
        }
    },
    created(){
      // if(this.$store.state.user===null){
      //   this.$router.push('/login');
      // }
      // this.schoolName=this.$store.state.schoolName;
      // this.user=this.$store.getters.getUser;
      // 유저 아이디, 닉네임, 캐릭터 가져와서 data에 저장
      
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
                    if(this.$refs.hookInstance !== undefined) this.$refs.hookInstance.message('GameManager','focusing',"true");
                    this.unityFocus = true;
                    this.keydownAlt = false;
                }else if(this.$store.state.chat){
                    if(this.$refs.hookInstance !== undefined)  this.$refs.hookInstance.message('GameManager','focusing',"false");
                    this.unityFocus = false;
                }else{ // 윈도우 인풋 가능
                    if(this.$refs.hookInstance !== undefined)  this.$refs.hookInstance.message('GameManager','focusing',"false");
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
        $route(to, from){
            console.log(to.path);
            console.log(from.path);
            if(from.path == '/select'){
                console.log("이게 나올거에요 제발요 안나오면 안되여");
                this.userChar = this.$store.getters.getCharacterNum;
                this.userId = localStorage.getItem('userId');
                this.userNickname = localStorage.getItem('userNickname');
                this.startGame();
            }
        },
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
        },

    },
    methods : {
        startGame(){
            console.log("캐릭터 번호 확인 : " + this.userChar);
            this.$refs.hookInstance.message('PlayerManager', 'SetUserId', this.userId);
            this.$refs.hookInstance.message('PlayerManager', 'SetUserNickname', this.userNickname);
            this.$refs.hookInstance.message('PlayerManager', 'SetUserChar', this.userChar);
            this.$refs.hookInstance.message('PlayerManager', 'StartGame');
        },
        goUnity(){
            if(!this.showMap){
              console.log('두번실행')
                this.$router.push({name : "Unity"});
            }
        },
        unityHook(){
            this.objectName = "";
            this.interval = setInterval(()=>{
                if(document.getElementById('unity-object-name').innerHTML != this.objectName){
                    this.objectName = document.getElementById('unity-object-name').innerHTML;
                    switch (this.objectName) {
                        case "Whale_R": // 정보공유/코드공유 - blackboard
                            this.$router.push({name : 'Purchase'});
                            break;
                        default:
                            break;
                    }
                    document.getElementById('unity-object-name').innerHTML = "";
                }
            })
        }
    }
  }
</script>
<style scoped src="../../css/UnityGame.css">
</style>