<template>
  <div>

    <!-- 우측 NavBar -->
    <div>  
      <button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">채팅</button>
      <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
        <div class="offcanvas-header">
          <h5 id="offcanvasRightLabel">나의 채팅방</h5>
          <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
    
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

        </div>
      </div>
    </div>
</template>

<script>
import http from '@/http.js';
import Offcanvas from 'bootstrap/js/dist/offcanvas'
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
        selectedChatRoomId:-1,
        bsOffcanvas: '',
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
        alert(chatRoomId + '번 채팅 방 입장!!');
        this.selectedChatRoomId = chatRoomId;
    },
    mounted () {
    this.bsOffcanvas = new Offcanvas(this.$refs.offcanvasRight)
    }
  }
};
</script>

