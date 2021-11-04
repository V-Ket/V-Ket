<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="modal-header">
            <slot name="header">
              default header
            </slot>
          </div>

          <div class="modal-body">
            <slot name="body">
              <div>
                <div v-for="(msg, idx) in msgArr" :key="idx">
                    <div> {{ msg }} </div>
                </div>
                <br/>
                <input type="text" v-model="content" placeholder="보낼 메세지" size="28" />
                <button @click="sendMessage()">SEND</button>
            </div>
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              default footer
              <button class="modal-default-button" v-on:click="$emit('close')">
                OK
              </button>
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import http from '@/http.js';
import store from '../../store/index';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
    name: "ChatModal",
    props:{
      chatRoomId : { type : Number }
    },
    data() {
        return {
            userId: localStorage['userId'],
            userNickname: localStorage['userNickname'],
            msgArr:[],
            stompClient:null,
            content:'',
        }
    },
    created(){
        this.getMessage();

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
        },
        getMessage() {
          http.get('chatRooms/message/' + this.chatRoomId)
          .then((res) => {

            for(let i=0; i<res.data.length; i++) {
              let msg = {
                'userId': res.data[i].userId,
                'content': res.data[i].content,
                'style': res.data[i].userId === this.userId ? 'myMessage' : 'otherMessage' 
              };
              this.msgArr.push(msg);
            }
          });
        }
    }
}
</script>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 300px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>