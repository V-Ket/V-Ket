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
            <div v-for="(chatRoom, idx) in roomList" :key="idx">
                {{chatRoom}}
                <button id="show-modal" @click="showModal = true, enterRoom(chatRoom.chatRoomId)">
                    채팅방 입장하기</button>
                <ChatModal :chatRoomId="selectedChatRoomId" v-if="showModal" @close="showModal = false">
                    <h3 slot="header">채팅보여줄거임</h3>
                </ChatModal>
            </div>
        </div>

        </div>
      </div>
  </div>
</template>

<script>
import http from '@/http.js';
import Offcanvas from 'bootstrap/js/dist/offcanvas'
import ChatModal from './ChatModal.vue'

export default {
    name: 'Chat',
    components: {
        ChatModal,
    },
    data() {
        return {
            showModal: false,
            selectedChatRoomId: -1,
            chatRoomIdStr : '-1',
            userId: localStorage['userId'],
            userNickname: localStorage['userNickname'],
            roomList:[],
            bsOffcanvas: '',
            content:'',
        }
    },
    created(){
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
        });
    },
    mounted () {
      this.bsOffcanvas = new Offcanvas(this.$refs.offcanvasRight)
    },
    methods:{
        enterRoom(inputChatRoomId) {
            alert(inputChatRoomId + '번 채팅 방 입장!!');
            this.selectedChatRoomId = inputChatRoomId;
        },
    }
}
</script>