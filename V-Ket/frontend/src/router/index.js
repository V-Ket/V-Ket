import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import Home from '../views/Home.vue'
import Unity from '../views/Unity.vue'
// import Unity from '../components/Unity/UnityGame.vue'
import SelectCH from '../views/SelectCH.vue'
import Purchase from '../components/Purchase.vue'
import NavTmp from '../views/NavTmp.vue'
import Meetings from '../views/Meetings.vue'
import MeetingList from '../components/MeetingList/MeetingList.vue';
import Store from '../views/Store.vue'

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'Main',
        component: Main
    },
    {
        path: '/home',
        name: 'Home',
        component: Home
    },
    {
        path: '/unity',
        name: 'Unity',
        component: Unity
    },
    {
        path: '/select',
        name: 'Select',
        component: SelectCH
    },
    {
        path: '/purchase',
        name: 'Purchase',
        component: Purchase
    },
    {
        path: '/navtmp',
        name: 'Navtmp',
        component: NavTmp
    },
    {
        path: '/meeting/:sessionid',
        name: 'Meeting',
        component: Meetings
    }
    ,
    {
        path: '/meetlist',
        name: 'MeetingList',
        component: MeetingList
    },
    {
        path: '/store',
        name: 'Store',
        component: Store
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: routes
})

export default router