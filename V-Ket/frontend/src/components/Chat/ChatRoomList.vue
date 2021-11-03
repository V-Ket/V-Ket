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

    <!-- 우측 NavBar -->
    <button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">Toggle right offcanvas</button>
    <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
      <div class="offcanvas-header">
        <h5 id="offcanvasRightLabel">Offcanvas right</h5>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        ...
      </div>
    </div>
    
    <div ref="mySidenav" id="mySidenav" class="sidenav">
      <a @click="closeNav">&times;</a>
      <a href="#">About</a>
      <a href="#">Service</a>
      <a href="#">Clients</a>
    </div>

    <span @click="openNav">open</span>

    <div ref="main" id="main">
      ...
    </div>
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
      openNav() {
        alert("open");
        var vm = this;
        vm.$refs.mySidenav.style.width="250px";
        vm.$refs.mySidenav.style.marginLeft="250px";
      },
      closeNav(){
        alert("close");
        var vm = this;
        vm.$refs.mySidenav.style.width="0";
        vm.$refs.mySidenav.style.marginLeft="0";
      },

      enterRoom(chatRoomId){
        alert(chatRoomId + '번 채팅 방 입장!!');
        this.selectedChatRoomId = chatRoomId;
    },
  }
};
</script>

<style>

.sideNav{
  height: 100%;
  width: 0;
  position: fixed; /* Stay in Place */
  z-index: 1; /* Stay on Top */
  top: 0;
  left: 0;
  background-color: #111;
  overflow-x: hidden; /* Disable horizontal scroll */

}


</style>