import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 개발모드면 true 배포모드면 false
const DEVELOPMODE = false;
let baseURL = DEVELOPMODE ? 'http://localhost:8877/' : 'https://k5a404.p.ssafy.io/';
// const chatRoomNumber = -1;

export default new Vuex.Store({
    state: {
        chat: false,
        characterNum: null,
        meetingListSize: null,
        credit: null,
    },
    getters: {
        fileURL: function() {
            return DEVELOPMODE ? 'http://localhost:8877/' : 'https://k5a404.p.ssafy.io/';
        },
        DEVELOPMODE: function() {
            return DEVELOPMODE;
        },
        baseURL: function() {
            return baseURL;
        },
        // chatRoomNumber: function() {
        //     return chatRoomNumber;
        // }
        getChat(state) {
            return state.chat;
        },
        getCharacterNum(state) {
            if (state.characterNum == null) {
                return null;
            }
            return state.characterNum;
        },
        getMeetingListSize(state) {
            if (state.meetingListSize == null) {
                return null;
            }
            return state.meetingListSize;
        },
        credit: state => state.credit
    },
    mutations: {
        setChat(state, status) {
            state.chat = status;
        },
        setChracterNum(state, characterNum) {
            state.characterNum = characterNum;
        },
        setMeetingListSize(state, meetingListSize) {
            state.meetingListSize = meetingListSize;
        },
        setCredit(state, credit) {
            state.credit=credit
        }
    }
})