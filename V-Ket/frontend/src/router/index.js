import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import Unity from '../views/Unity.vue'
import SelectCH from '../views/SelectCH.vue'
import Purchase from '../components/Purchase.vue'
import Meetings from '../views/Meetings.vue'
import Store from '../views/Store.vue'
import GoodsList from '../components/store/GoodsList.vue';

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'Main',
        component: Main
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
        path: '/meeting/:sessionid',
        name: 'Meeting',
        component: Meetings
    },
    {
        path: '/store',
        name: 'Store',
        component: Store,
    },
    {
        path: '/goodslist',
        name: 'GoodsList',
        component: GoodsList,
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: routes
})

export default router