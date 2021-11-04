<template>
  <div id="chat">
     하이 Chat.vue
 
    <div id="navBar">  
      <button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">채팅</button>
      <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
        <div class="offcanvas-header">
          <h5 id="offcanvasRightLabel">{{userId}}님의 채팅방</h5>
          <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>

        <div class="offcanvas-body">
            <div v-if="chatRoomId == -1">
                채팅방목록보여줄거임 
                <div v-for="(chatRoom, idx) in roomList" :key="idx">
                    <div @click="enterRoom(chatRoom.chatRoomId)">
                        {{chatRoom}}
                     </div> 
                </div>
                
            </div>
            <div v-else>
                채팅보여줄거임
                <div v-for="(msg, idx) in chatMsgArr[chatRoomIdStr]" :key="idx">
                    <div> {{ msg }} </div>
                </div>
                <br/>
                <input type="text" v-model="content" placeholder="보낼 메세지" size="70" />
                <button @click="sendMessage()">SEND</button>

                <br><br>
                <button type="button" @click="backToChatRoomList">채팅방 목록</button>
            </div>
        </div>

        </div>
      </div>
  </div>
</template>

<script>
import http from '@/http.js';
import store from '../../store/index';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import Offcanvas from 'bootstrap/js/dist/offcanvas'

export default {
    name: 'Chat',
    data() {
        return {
            // 채팅방 
            chatRoomId:-1,
            chatRoomIdStr : '-1',
            userId: localStorage['userId'],
            userNickname: localStorage['userNickname'],
            roomList:[],
            chatMsgArr:{},
            bsOffcanvas: '',
            // 채팅 내역과 채팅 보내기
            stompClient:null,
            content:'',
        }
    },
    created(){
        // socket, stomp 연결
        let socket = new SockJS(store.getters.baseURL + 'ws');
        this.stompClient = Stomp.over(socket);

        // 채팅방 목록 불러오기
        http.get('chatRooms/' + this.userId)
        .then((res) => {
            this.roomList = [];
            for(let i=0; i<res.data.length; i++) {
                let chatRoom = {
                    'chatRoomId': res.data[i].chatRoomId,
                    'senderId': res.data[i].senderId,
                    'receiverId': res.data[i].receiverId
                }
                this.roomList.push(chatRoom);
            }

            // for(let i=0; i<res.data.length; i++) {
            //     this.connectStompClient(res.data[i].chatRoomId);
            // }
        });
    },
    mounted () {
      this.bsOffcanvas = new Offcanvas(this.$refs.offcanvasRight)
    },
    methods:{
        // connectStompClient: async function(inputChatRoomId) {
        connectStompClient(inputChatRoomId) {
           
            console.log(inputChatRoomId,'번 방 연결');

            this.stompClient.connect({}, frame => {
                console.log('>>>> success ', inputChatRoomId, '번 방 연결 성공', frame);
                this.stompClient.subscribe('/sub/' + inputChatRoomId, res => { // 메시지 받기
                    let jsonBody = JSON.parse(res.body);
                    let msg = {
                        'userId': jsonBody.userId,
                        'content': jsonBody.content,
                        'style': jsonBody.userId === this.userId ? 'myMessage' : 'otherMessage' 
                    };

                    console.log('메시지 받기', msg);
                    let str = String(inputChatRoomId);
                    if(str in this.chatMsgArr) {
                        this.chatMsgArr[str].push(msg);
                    } else {
                        this.chatMsgArr[str] = new Array({});
                        this.chatMsgArr[str].push(msg);
                    }
                });
            });
        },

        enterRoom(inputChatRoomId) {
            alert(inputChatRoomId + '번 채팅 방 입장!!');
            this.chatRoomId = inputChatRoomId;
            this.chatRoomIdStr = String(inputChatRoomId);
            //this.connectStompClient(inputChatRoomId);
            // 라우터 이동
            this.$router.push({name:"ChatRoom", params:{chatRoomId : this.chatRoomId}});
        },

        sendMessage(){
           let msg = {
                'chatRoomId': this.chatRoomId,
                'content': this.content,
                'userId': this.userId
           };
           this.stompClient.send('/pub/', JSON.stringify(msg));
           this.content='';
           
        },
        backToChatRoomList() {
            this.chatRoomId = -1;
            this.chatRoomIdStr = '-1';
        }
    }
}

</script>

<style>

</style>