<template>
    <div>
        <div>
            채팅보여줄거임
            <div v-for="(msg, idx) in msgArr" :key="idx">
                <div> {{ msg }} </div>
            </div>
            <br/>
            <input type="text" v-model="content" placeholder="보낼 메세지" size="70" />
            <button @click="sendMessage()">SEND</button>

            <br><br>
        </div>
    </div>
</template>
<script>
import store from '../../store/index';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
    name: "ChatRoom",
    data() {
        return {
            chatRoomId:-1,
            userId: localStorage['userId'],
            userNickname: localStorage['userNickname'],
            msgArr:[],
            stompClient:null,
            content:'',
        }
    },
    created(){
        this.chatRoomId = this.$route.params.chatRoomId;
        let socket = new SockJS(store.getters.baseURL + 'ws');
        this.stompClient = Stomp.over(socket);
        console.log(this.chatRoomId,'번 방 연결');

        this.stompClient.connect({}, frame => {
            console.log('>>>> success ', this.chatRoomId, '번 방 연결 성공', frame);
            this.stompClient.subscribe('/sub/' + this.chatRoomId, res => { // 메시지 받기
                let jsonBody = JSON.parse(res.body);
                let msg = {
                    'userId': jsonBody.userId,
                    'content': jsonBody.content,
                    'style': jsonBody.userId === this.userId ? 'myMessage' : 'otherMessage' 
                };

                this.msgArr.push(msg);
            });
        });
    },
    methods: {
        sendMessage(){
           let msg = {
                'chatRoomId': this.chatRoomId,
                'content': this.content,
                'userId': this.userId
           };
           this.stompClient.send('/pub/', JSON.stringify(msg));
           this.content='';
        }
    }
}
</script>
