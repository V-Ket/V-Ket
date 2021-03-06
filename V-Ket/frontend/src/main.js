import Vue from "vue";
import './plugins/bootstrap-vue'
import App from "./App.vue";
import router from "./router/index.js";
// import store from './store/index';
import vuetify from './plugins/vuetify'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueSweetalert2 from 'vue-sweetalert2';
import store from './store/index';
// If you don't need the styles, do not connect
import 'sweetalert2/dist/sweetalert2.min.css';

Vue.use(VueSweetalert2);
Vue.config.productionTip = false;

export const eventBus = new Vue()

new Vue({
    router,
    vuetify,
    store,
    render: (h) => h(App),
}).$mount("#app");