import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import Home from '../views/Home.vue'
import Test from '../views/test.vue'
// import Unity from '../components/Unity/UnityGame.vue'
import SelectCH from '../views/SelectCH.vue'
import Purchase from '../components/Purchase.vue'

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
        component: Test
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
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: routes
})

export default router