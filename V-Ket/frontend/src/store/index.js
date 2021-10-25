import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 개발모드면 true 배포모드면 false
// const DEVELOPMODE = true;
const DEVELOPMODE = false;
export default new Vuex.Store({
  getters: {
    fileURL: function () {
      return DEVELOPMODE ? 'http://localhost:8080/' : 'https://j5a105.p.ssafy.io/';
    },
    DEVELOPMODE: function() {
      return DEVELOPMODE;
    }
  }
})
