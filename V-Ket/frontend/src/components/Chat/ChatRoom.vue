<template>
    <div>
      ChatRoomList의 하위 컴포넌트 ChatRoom입니다.
      <br/>
      <input type="text" v-model="content" placeholder="보낼 메세지" size="100" />
      <button @click="sendMessage()">SEND</button>
    </div>
</template>
<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
    name:'ChatRoom',
    props:{
      chatRoomId : { type : Number }
    },
    data: () => {
      return {
        userId: localStorage['userId'],
        userNickname: localStorage['userNickname'],
        meg:[],
        content:'',
        //chatRoomId:-1,
        stompClient:null
      }
    },
    created() {
        console.log('chatRoomId ' , this.chatRoomId);
        let socket = new SockJS('http://localhost:8877/ws');
        this.stompClient = Stomp.over(socket);
        this.stompClient.connect({}, frame => {
          console.log('success ', frame);
        });
    },
    methods: {
      sendMessage(){
        console.log('send ', this.content, ' chatRoomId', this.chatRoomId);
      }
    }
};
</script>
