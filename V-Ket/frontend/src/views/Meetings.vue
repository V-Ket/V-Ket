<template>
	<div id="session">
		<div class="container">
			<div class="row mt-1">
				<div class="col-1"></div>
				<div class="col-9" id="title">
					{{other}} 님과의 화상 통화
				</div>
				<div class="col-2">
					<Dialog
						:buttonText="'나가기'"
						:dialogTitle="'알림'"
						:dialogContent="'미팅을 종료하시겠습니까?'"
						:buttonO="'네'"
						:buttonX="'아니오'"
						@clickO="exitInterview"
					/>
				</div>
			</div>
			<div class="row">
				<div class="col-12" style="padding:0px" id="seller-video">
					<user-video	v-for="pub in publishers"	:key="pub.stream.connection.connectionId + '2'" :stream-manager="pub"/>
				</div>
			</div>
			<div class="row">
				<div class="col-9"></div>
				<div class="col-3" style="padding:0px" id="my-video">
					<user-video-2 :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
				</div>
			</div>
			<div class="row">
				<div class="col-12" id="buttons">
					<button id="btn-camoff" @click="updateStream(0)">
						<div v-if="!setting.publishVideo"><v-icon id="unpublish-video" style="color:red; margin-right:1vw;">fas fa-video-slash</v-icon> 비디오 시작 </div>
						<div v-else><v-icon id="publish-video" style="color:green; margin-right:1vw;">fas fa-video</v-icon> 비디오 끄기 </div>
					</button>
					<button id="btn-audiooff" @click="updateStream(1)">
						<div v-if="!setting.publishAudio"><v-icon id="unpublish-audio" style="color:red; margin-right:1vw;">fas fa-microphone-slash</v-icon> 음소거 해제</div>
						<div v-else><v-icon id="publish-audio" style="color:green; margin-right:1vw;">fas fa-microphone</v-icon> 음소거 </div>
					</button>
				</div>
			</div>
		</div>
	</div>
</template>
<style scoped>
#title{
	font-weight: bold;
	font-size: 20px;
}
#seller-video{
	margin-top: 1vh;
	position:absolute;
	margin-left: 4vw;
}
#session{
	width: 66.6vw;
}
#my-video{
	position:absolute;
	margin-top: 66vh;
	margin-left: 48vw;
}
#buttons{
	position: absolute;
	margin-top: 75vh;
}
#btn-camoff{
	width: 15vw;
	border: 1px solid black;
	border-radius: 10px;
	padding-top: 2vh;
	padding-bottom: 2vh;
	padding-left: 3vw;
	padding-right: 3vw;
	margin-left: 5vw;
	font-weight: bold;
	font-size: 20px;
}
#btn-audiooff{
	position: absolute;
	width: 15vw;
	border: 1px solid black;
	border-radius: 10px;
	padding-top: 2vh;
	padding-bottom: 2vh;
	padding-left: 3vw;
	padding-right: 3vw;
	margin-left: 5vw;
	font-weight: bold;
	font-size: 20px;
}
</style>
<script>
import http from '@/http.js';
import axios from 'axios';
// import { mapGetters } from 'vuex';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/live/UserVideo';
import UserVideo2 from '@/components/live/UserVideo2';
import Dialog from '@/components/Dialog'
axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://k5a404.p.ssafy.io:8011";
const OPENVIDU_SERVER_SECRET = "1234";
export default {
	name: 'Meetings',
	components: {
		UserVideo,
		UserVideo2,
		Dialog
	},
	data () {
		return {
			timeout: 3000,
			isQuestion: false,
			OV: undefined,
			session: undefined,
      sessionId: this.$route.params.sessionid,
			other : this.$route.params.other,
			storeId: this.$route.params.storeid,
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
    this.mySessionId = this.sessionId
		// this.setting.audioSource = this.$store.getters.getAudio;
		// this.setting.videoSource = false;
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
      this.$router.push({name:'Store', params:{storeId : this.storeId}});
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
								publishAudio: false,  	// Whether you want to start publishing with your audio unmuted or not
								publishVideo: false,  	// Whether you want to start publishing with your video enabled or not
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