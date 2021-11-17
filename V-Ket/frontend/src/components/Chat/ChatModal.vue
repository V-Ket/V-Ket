<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="modal-header">
            <slot name="header">
            </slot>
            <button class="modal-default-button" v-on:click="$emit('close')">
                X
            </button>
          </div>

          <div class="modal-body" ref="messages">
            <slot name="body" >
              <div id="bodyIn">
                <div v-for="(msg, idx) in msgArr" :key="idx">
                    <div class="chatDiv" :class="msg.style"> 
                      <span>{{ msg.userId }} : </span> 
                      <span class="chatContent"> {{ msg.content }}</span>
                    </div>
                </div>
              </div>
            </slot>
          </div>
          <div id="chat-send">
            <input id="send-msg" type="text" v-model="content" @keyup.enter="sendMessage" placeholder="보낼 메세지" size="28" />
            <button id="send-btn" @click="sendMessage()" >SEND</button>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import http from '@/http.js';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
    name: "ChatModal",
    props:{
      chatRoomId : { type : Number },
      receiver : String,
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
        // let socket = new SockJS("https://k5a404.p.ssafy.io:8877/ws");
        let socket = new SockJS("http://localhost:8877/ws");
        this.stompClient = Stomp.over(socket);
        this.stompClient.connect({}, () => {
            this.stompClient.subscribe('/sub/' + this.chatRoomId, res => { // 메시지 받기
                let jsonBody = JSON.parse(res.body);
                let msg = {
                    'userId': jsonBody.userId,
                    'content': jsonBody.content,
                    'style': jsonBody.userId === this.userId ? 'myMsg' : 'otherMsg' 
                };
                this.msgArr.push(msg);
            });
        });
    },
    watch:{
      msgArr() {
        this.$nextTick(() => {
          let messages = this.$refs.messages;
          messages.scrollTo({top: messages.scrollHeight, behavior: 'smooth'})
        })
        this.$emit("msgAlert");
      }
    },
    computed:{
      isMyMsg : function(){
        if(this.receiver != this.userId){
          return true;
        }else{
          return false;
        }
      }
    },
    methods: {
        sendMessage(){
          if(this.content != '') {
            let msg = {
                  'chatRoomId': this.chatRoomId,
                  'content': this.content,
                  'userId': this.userId
            };
            this.stompClient.send('/pub/', JSON.stringify(msg));
            this.content='';
          }
        },
        getMessage() {
          http.get('chatRooms/message/' + this.chatRoomId)
          .then((res) => {
            for(let i=0; i<res.data.length; i++) {
              let msg = {
                'userId': res.data[i].userId,
                'content': res.data[i].content,
                'style': res.data[i].userId === this.userId ? 'myMsg' : 'otherMsg' 
              };
              this.msgArr.push(msg);
            }
          });
        }
    }
}
</script>

<style scoped>
.modal-footer{
  margin-left: 0px;
  padding: 0px;
}
.chatDiv{
  white-space: pre-line;
  background-color: white;
  margin-bottom: 8px;
  display: flex;
  padding: 10px 10px 0 10px;
  border-radius: 0 6px 6px 0;
  max-width: 60%;
  width: auto;
  box-shadow: 0 0 2px rgba(0, 0, 0, 0.12), 0 2px 4px rgba(0, 0, 0, 0.24);
  flex: 1 0 auto;
  flex-direction: column;
  width: calc(100% - 50px);
}
.myMsg {
  float: right;
  border-radius: 6px 0 0 6px;
  color: gray;
}

.chatContent {
  word-break: break-all;
  text-align: start;
  font-size: 11pt;
  line-height: 13pt;
  margin: 0 0 10px;
}
.myMsg {
  float: right;
  border-radius: 6px 0 0 6px;
  color: gray;
  text-align: right;
}
#chat-send{
  border-top: 1px solid gray;
}
#send-msg{
  border-left: 1px solid gray;
  border-bottom: 1px solid gray;
  border-right: 1px solid gray;
  border-radius: 10px;
  background-color: white;
  width: 15vw;
  height: 8vh;
}
#send-btn{
  background-color: white;
  margin-top: 1vh;
  float: right;
  padding:5px;
  border: 1px solid black;
}
/* Modal 창에 대한 style */
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  /* background-color: rgba(0, 0, 0, .5); */
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 22vw;
  height: 60vh;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #eee;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: Helvetica, Arial, sans-serif;
  /*overflow: scroll;*/   /* 스크롤 추가 */
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
  height: 75vh;
}

.modal-body {
  overflow: auto;
  margin: 20px 0;
  height: 36vh;
  padding-bottom: 0px;
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