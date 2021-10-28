<template>
  <div>
    <h2>채팅방 목록 보기</h2>
    <h3> Id : {{userId}}</h3>
    <!-- <div v-else-if="roomList.length>0"> -->
    <div v-for="(chatRoom,idx) in roomList" :key="idx">
      <div @click="enterRoom(chatRoom.chatRoomId)">
        {{chatRoom}}
      </div> 
    </div>
    <ChatRoom :chatRoomId="selectedChatRoomId" />
  </div>
</template>
<script>
import http from '@/http.js';
import ChatRoom from './ChatRoom.vue' // 하위 컴포넌트

export default {
    name:'ChatRoomList',
    setup(){

    },
    data: () => {
      return {
        userId: localStorage['userId'],
        userNickname: localStorage['userNickname'],
        roomList:[],
        selectedChatRoomId:-1
      }
    },
    components: {
      ChatRoom
    },
    created(){
          http.get('chatRooms/' + this.userId)
          .then((res) => {
            //console.log('조회 ', res.data);
            this.roomList = [];
            for(let i=0; i<res.data.length; i++) {
              let chatRoom = {
                'chatRoomId': res.data[i].chatRoomId,
                'senderId': res.data[i].senderId,
                'receiverId': res.data[i].receiverId
              }
              this.roomList.push(chatRoom);
            }
          });
    },
    methods:{
      enterRoom(chatRoomId){
        this.selectedChatRoomId = chatRoomId;
    },
  }
};
</script>
