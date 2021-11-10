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
            width = "1280"
            height = "970"
            unityLoader="./unity/Build/UnityLoader.js" 
            :hideFooter="true"
            ref="hookInstance">
            </unity>
            <!-- <button @click="unityHook">시~작~</button> -->
        </div>
        <div id="unity-school-name" hidden></div>
        <div id="unity-object-name" hidden></div>
        <!-- 상점 번호 -->
        <div id="unity-store-id" hidden></div>
        <!-- 상점 위치 섬번호 + 상점 위치번호 -->
        <div id="unity-store-pos" hidden></div>
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
            storeId : '',
            islandPos : '',
            storePos : '',
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

        this.unityHook();
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
        //유니티에서 값 보내는거 확인용
        unityHook(){
            this.storeId = "";
            this.islandPos = "";
            this.storePos = "";
            // 요기 주석풀고 하면됨.
            // this.interval = setInterval(() => {
            //     // storeid가 비어있지 않다면
            //     if(document.getElementById('unity-store-id').innerHTML != this.storeId){
            //         this.storeId = document.getElementById('unity-store-id').innerHTML;
            //         // this.$router.push();
            //     }
            //     // storePos가 비어있지 않다면
            //     else if(document.getElementById('unity-store-pos').innerHTML != this.storePos){
            //         this.islandPos = document.getElementById('unity-store-id').innerHTML.substring(0,4);
            //         this.storePos = document.getElementById('unity-store-id').innerHTML.substring(4,5);
            //         // this.$router.push();
            //     }

            //     document.getElementById('unity-store-id').innerHTML = "";
            //     document.getElementById('unity-store-pos').innerHTML = "";
            // });

        }
    }
  }
</script>
<style scoped src="../../css/UnityGame.css">
</style>