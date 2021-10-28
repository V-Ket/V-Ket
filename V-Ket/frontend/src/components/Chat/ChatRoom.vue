<template>
    <div>
      ChatRoomList의 하위 컴포넌트 ChatRoom입니다.
      <br/>
      채팅 메시지 보기
      <div v-for="(msg, idx) in messages" :key="idx">
        <div>
          {{ msg }}
        </div>
      </div>
      <br/>
      <input type="text" v-model="content" placeholder="보낼 메세지" size="70" />
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
        messages:[{
              'userId': '아이디',
              'content': '내용',
              'style': 'myMessage' 
            }],
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
          console.log('>>>>>>>>>>>>>', '/sub/' + this.chatRoomId);
          this.stompClient.subscribe('/sub/' + this.chatRoomId, res => { // 메시지 받기
            console.log('받기', res);
            let jsonBody = JSON.parse(res.body);
            let message = {
              'userId': jsonBody.userId,
              'content': jsonBody.content,
              'style': jsonBody.userId === this.userId ? 'myMessage' : 'otherMessage' 
            };
            this.messages.push(message);
          });
        });
    },
    methods: {
      sendMessage(){
        // 메시지 전송\\
        console.log('send ', this.content, ' chatRoomId', this.chatRoomId);
        if(this.chatRoomId!=-1 && this.content.trim() !='' && this.stompClient!=null) {
          // console.log('send ', this.content, ' chatRoomId', this.chatRoomId);
          let chatMessage = {
            'chatRoomId': this.chatRoomId,
            'content': this.content,
            'userId': this.userId
          };
          //console.log(chatMessage);
          this.stompClient.send('/pub/', JSON.stringify(chatMessage));
          this.content='';
        }
      }
    }
};
</script>
