<template>
    <div id="unity-game" :class="{'small-map':!showMap}" @click="goUnity">
        <div id="game-container">
            <unity id="vket-unity" 
            src="./unity/Build/unity.json" 
            :width = width
            :height = height
            unityLoader="./unity/Build/UnityLoader.js" 
            :hideFooter="true"
            ref="hookInstance">
            </unity>
            <v-dialog
                v-model="isOpenStoreModal"
                max-width="500px"
                > <OpenStore class="temp" :islandpos="this.islandPos" :storepos="this.storePos" @open="open" @closeStoreModal="closeStoreModal" />
            </v-dialog>
        </div>
        <!-- 상점 번호 -->
        <div id="unity-store-id" hidden></div>
        <!-- 상점 위치 섬번호 + 상점 위치번호 -->
        <div id="unity-store-pos" hidden></div>
    </div>
</template>

<script>
  import Unity from 'vue-unity-webgl';
  import OpenStore from '@/components/store/OpenStore.vue';
  
  export default {
		name : "UnityGame",
		components : {
            Unity,
            OpenStore,
        },
        data() {
        return {
            user: {},
            objectName : '',
            schoolName : '',
            linked : false,
            height : '970',
            width : '1280',
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
            isOpenStoreModal : false,
        }
    },
    mounted() {
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
                const target = document.querySelector('#unity-game-container')
                const targetRect = target.getBoundingClientRect();
                this.width = targetRect.width;
                this.height = window.innerHeight;
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
            if(from.path == '/select'){
                this.userChar = this.$store.getters.getCharacterNum;
                this.userId = localStorage.getItem('userId');
                this.userNickname = localStorage.getItem('userNickname');
                this.startGame();
            }
            else if(to.path.includes('/meeting')){
                this.$refs.hookInstance.message('SoundManager', 'stopBGM')
            }else if(from.path.includes('/meeting')){
                this.$refs.hookInstance.message('SoundManager', 'playBGM')
            }
        },
        showMap : function(newVal){
            if(newVal){
                const target = document.querySelector('#unity-game-container')
                const targetRect = target.getBoundingClientRect();
                this.width = targetRect.width;
                this.height = window.innerHeight;
            }else{
                this.width = '150';
                this.height = '100';
            }
        },

    },
    methods : {
        setStore(){
            this.$refs.hookInstance.message('Stores', 'ChangeStore');
        },
        startGame(){
            this.$refs.hookInstance.message('PlayerManager', 'SetUserId', this.userId);
            this.$refs.hookInstance.message('PlayerManager', 'SetUserNickname', this.userNickname);
            this.$refs.hookInstance.message('PlayerManager', 'SetUserChar', this.userChar);
            this.$refs.hookInstance.message('PlayerManager', 'StartGame');
            this.unityHook();
        },
        goUnity(){
            if(!this.showMap){
                this.$router.push({name : "Unity"});
            }
        },
        //유니티에서 값 보내는거 확인용
        unityHook(){
            this.storeId = "";
            this.islandPos = "";
            this.storePos = "";
            // 요기 주석풀고 하면됨.
            this.interval = setInterval(() => {
                // storeid가 비어있지 않다면
                if(document.getElementById('unity-store-id').innerHTML != this.storeId){
                    this.storeId = document.getElementById('unity-store-id').innerHTML;
                    document.getElementById('unity-store-id').innerHTML = "";
                    document.getElementById('unity-store-pos').innerHTML = "";
                    this.$router.push({name: 'Store' , params:{storeId : this.storeId}});
                    
                }
                // storePos가 비어있지 않다면
                else if(document.getElementById('unity-store-pos').innerHTML != this.storePos){
                    this.islandPos = document.getElementById('unity-store-pos').innerHTML.substring(0,4);
                    this.storePos = document.getElementById('unity-store-pos').innerHTML.substring(4,5);
                    this.isOpenStoreModal = true;
                    document.getElementById('unity-store-id').innerHTML = "";
                    document.getElementById('unity-store-pos').innerHTML = "";
                    this.unityFocus = false;
                    this.$refs.hookInstance.message('GameManager','focusing',"false");
                }  
            }, 1000);
            
        },
        closeStoreModal(){
            this.isOpenStoreModal = false;
            this.$refs.hookInstance.message('GameManager','focusing',"true");
            this.unityFocus = true;
            
        },
        open(){
            this.isOpenStoreModal = false;
            this.setStore();
            this.$refs.hookInstance.message('GameManager','focusing',"true");
            this.unityFocus = true;
        }
    }
  }
</script>
<style scoped src="../../css/UnityGame.css">
</style>