<template>
	<div id="session-background" class="d-flex justify-content-center align-items-center">
		<div class="elevation-10 session-whole" v-if="session">
			<div id="session-header">
				<div>
					<!-- <img class="header-logo-letter" src="/images/icons/logo_letter.png" alt="IMG-LOGO"> -->
					<p>이미지</p>
				</div>
				<Dialog
				:buttonText="'면접장 퇴장'"
				:dialogTitle="'알림'"
				:dialogContent="'면접장을 퇴장하시겠습니까?'"
				:buttonO="'네'"
				:buttonX="'아니오'"
				@clickO="exitInterview"/>
			</div>
			<div id="session-body">

				<div id="session-video" class="d-inline-flex row">
					<user-video	:stream-manager="publisher" class="col-6"	@click.native="updateMainVideoStreamManager(publisher)" />
					<user-video	v-for="pub in publishers"	:key="pub.stream.connection.connectionId + '2'" :stream-manager="pub" class="col-6" />
				</div>
				<button id="btn-camoff" @click="updateStream(0)" >
					<div v-if="!setting.publishVideo"><v-icon id="unpublish-video">fas fa-video-slash</v-icon></div>
          <div v-else><v-icon id="publish-video">fas fa-video</v-icon></div>
					</button>
					<button id="btn-audiooff" @click="updateStream(1)">
          <div v-if="!setting.publishAudio"><v-icon id="unpublish-audio">fas fa-microphone-slash</v-icon></div>
          <div v-else><v-icon id="publish-audio">fas fa-microphone</v-icon></div>
      </button>
				<div id="session-message">
					<div id="session-message-header" class="elevation-2">
						{{this.sessionId}} 세션아이디
					</div>
        </div>
			</div>
		</div>
		
	</div>
</template>
<style scoped>	
#btn-camoff{
	position: absolute;
	top:90%;
}
#btn-audiooff{
	position: absolute;
	top:90%;
	left: 30%;
}
.header-logo-letter {
    height: 40px;
    width: 300px; 
    object-fit: cover;
    background: none;
}

#header-company-name {
	font-size: 2rem;
}

.icon-box {
	display: flex;
	flex-direction: column;
	justify-content: center;
	text-align: center;
	color: rgb(49, 49, 49);
	height: 30px;
	width: 30px;
	margin-top: 0px;
	margin-right: 10px;
	background: white;
	border: outset;
	border-radius: 5px;
}

.icon-box:hover {
	cursor: pointer;
}

.mute {
	color: rgb(255, 91, 123);
}

#question-header {
	display: flex;
	justify-content: space-between;
	border-bottom: rgb(82, 82, 82) solid 1px;
	padding-left: 10px;
	padding-right: 0px;
	padding-bottom: 10px;
}

#question-nickname {
	font-size: 20px;
	padding-top: 8px;
}

#question-content {
	padding-top: 20px;
	padding-left: 10px;
	padding-right: 10px;
}

.chat-box {
	padding: 5px;
	padding-left: 15px;
	padding-right: 15px;
  background-color: #eee;
	border: 1px solid rgb(189, 189, 189);
  border-radius: 5px;
}

.my-chat-box {
  padding: 5px;
	padding-left: 15px;
	padding-right: 15px;
  background-color: #439474;
	color: white;
	border: 1px solid rgb(189, 189, 189);
  border-radius: 5px;
	text-align: end;
}

.userInfo {
  height: 35px;
  display: flex;
  align-items: center;
}

.participant-name {
  height: 25px;
  line-height: 25px;
}

.chat-image-box {
    height: 25px;
    width: 25px;
    border-radius: 70%;
    overflow: hidden;
}

.chat-image {
    width: 100%;
    height: 100%;   
    object-fit: cover;
}

#session-background {
  background-color: rgb(199, 199, 199);
  height: 100vh;
  width: 100vw;
}

.session-whole {
  width: 95%;
  height: 95%;
  background-color: white;
  border-radius: 10px;
  overflow: hidden;
	border: 1px solid rgb(151, 151, 151);
}

#session-header {
	display: flex;
  justify-content: space-between;
  height: 15%;
  padding-left: 30px;
  padding-right: 50px;
  padding-top: 30px;
  padding-bottom: 30px;
  border-bottom: solid rgb(151, 151, 151) 2px;
	background: linear-gradient( to right, rgb(197, 204, 206), #C0DDD1 );
}

#session-body {
  height: 85%;
  display: flex;
  position: relative;
	background: rgb(155, 155, 155);
}

#session-video {
  width: 70vw;
  height: 70vh;
  position: absolute;
  margin-left: 1vw;
  margin-bottom: 0px;
  margin-right: 2vw;
  margin-top: 0px;
}

#session-message {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  position: absolute;
  right: 0px;
  height: 100%;
  width: 24%;
  background-color: #C0DDD1;
  border-left: solid rgb(151, 151, 151) 1px;
}

#session-message-header {
	text-align: center;
	padding: 10px;
	font-size: 18px;
	border-bottom: 1px rgb(151, 151, 151) solid;
	background: rgb(223, 223, 223);
	border-radius: 5px;
	margin: 15px;
	margin-bottom: 5px;
}

#session-message-box {
	scroll-behavior: auto;
	overflow: auto;
	padding: 10px;
}


#session-message-send {
	border-top: solid rgb(151, 151, 151) 1px;
}

#session-message-input {
  width: 100%;
}
video {
  width: 100%;
  height: auto;
}

</style>
<script>
import http from '@/http.js';
import axios from 'axios';
// import { mapGetters } from 'vuex';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/live/UserVideo';
import Dialog from '@/components/Dialog'
axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://k5a404.p.ssafy.io:8011";
const OPENVIDU_SERVER_SECRET = "1234";
export default {
	name: 'Meetings',
	components: {
		UserVideo,
		Dialog
	},
	data () {
		return {
			timeout: 3000,
			isQuestion: false,
			OV: undefined,
			session: undefined,
      sessionId: this.$route.params.sessionid,
			// interviewee: this.$route.params.interviewee,
      publisher: undefined,
			subscribers: [],
      publishers: [],
			chats: [],
			question: Object,
			sendMsg: '',
			mySessionId: '',
			myUserName: '',
			isHost: false,
			muteList: [],
			setting:{
				audioSource: undefined,
				videoSource: undefined,
				publishAudio: false,
				publishVideo: false,
			}
		}
	},
	computed: {
    changedPublishers: function () {
      return this.publishers
    },
		// ...mapGetters([
    //   'fileURL',
    // ]),
  },
	created () {
		this.setting.audioSource = this.$store.getters.getAudio;
		this.setting.videoSource = this.$store.getters.getVideo;
	},
	mounted() {
		this.joinSession()
	},
  beforeDestroy () {
    this.leaveSession()
  },
	methods: {
		updateStream(type){
        if (type == 1) {
          this.setting.publishAudio = !this.setting.publishAudio;
          this.publisher.publishAudio(this.setting.publishAudio);
        } else {
          this.setting.publishVideo = !this.setting.publishVideo;
          this.publisher.publishVideo(this.setting.publishVideo);
        }
      },
		removeSession () {
			axios.delete(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${this.sessionId}`, {
			auth: {
				username: 'OPENVIDUAPP',
				password: OPENVIDU_SERVER_SECRET,
			},
			})
			.then((res) => {
				console.log(res)
			})
			.catch((err) => {
				console.log(err)
			})
		},
    exitInterview () {
      this.leaveSession()
			http.delete('/session/delete/' + this.sessionId)
			.then(() => {
				this.removeSession()
			})
			.catch((err) => {
				console.log(err)
			})
      this.$router.go(-1)
    },
		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();
			// --- Init a session ---
			this.session = this.OV.initSession();
			// --- Specify the actions when events take place in the session ---
			// On every new Stream received...
      this.session.on('streamCreated', ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.publishers.push(subscriber)
			});
			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.publishers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.publishers.splice(index, 1);
				}
			});
			this.session.on('signal:my-chat', event => {
        this.chats.push(JSON.parse(event.data));
        setTimeout(this.chat_on_scroll, 10);
      });
			this.session.on('signal:question', event => {
        this.question = JSON.parse(event.data);
				this.isQuestion = true
      });
			this.session.on('signal:mute', event => {
				this.muteList.push(JSON.parse(event.data).nickname)
      });
			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});
			// --- Connect to the session with a valid user token ---
			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session
          .connect(token, { clientData: this.myUserName })
					.then(() => {
						let publisher = this.OV.initPublisher(undefined, {
								audioSource: undefined, // The source of audio. If undefined default microphone
								videoSource: undefined, // The source of video. If undefined default webcam
								publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
								publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
								resolution: '640x480',  // The resolution of your video
								frameRate: 30,			// The frame rate of your video
								insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
								mirror: false,       	// Whether to mirror your local video or not
              });
							this.publisher = publisher;
							// --- Publish your stream ---
							this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});
			window.addEventListener('beforeunload', this.leaveSession)
		},
		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect();
			this.session = undefined;
			this.mainStreamManager = undefined;
			this.subscribers = [];
			this.OV = undefined;
			window.removeEventListener('beforeunload', this.leaveSession);
		},
		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},
		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},
		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},
    updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},
	}
}
</script>