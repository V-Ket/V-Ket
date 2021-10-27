<template>
    <div>
        하이하이
        <h3></h3>
    </div>
</template>
<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
    name:'ChatRoomList',
    setup(){

    },
    data: () => {
      return {
        userId: localStorage['userId'],
        userNickname: localStorage['userNickname'],
        mesg:[],
        content:'',
        stompClient:null
      }
    },
    created() {
        let socket = new SockJS('http://localhost:8877/ws');
        this.stompClient = Stomp.over(socket);
        console.log('stompClient ', this.stompClient);
    }
};
</script>
