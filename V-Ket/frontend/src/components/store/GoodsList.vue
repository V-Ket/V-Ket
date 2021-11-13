<template>
    <div id="goodslist">
        <div class="container">
            <div class="row">
                <div class="col-9">

                </div>
                <div class="col-3">
                    <button @click="goBack">뒤로가기</button>
                </div>
            </div>
            <div class="row">
                <v-row class="row">
                <Goods v-for="(goods, idx) in goodsList"
                :key="idx+'goods'"
                :goods="goods"
                :hostId="hostId"
                @Refresh="Refresh"
                 />
                </v-row>
            </div>
        </div>
    </div>
</template>

<script>
import http from '@/http.js';
import Goods from '@/components/store/Goods.vue';

export default {
    name: "GoodsList",
    props:{

    },
    data(){
        return{
            goodsList: Array,
            storeId : this.$route.params.storeId,
            hostId : this.$route.params.hostId,
        }
    },
    components:{
        Goods,
    },
    mounted(){
        console.log("상품 상세보기");
        console.log(this.storeId);
        console.log(this.hostId);
        // http.get('/goods/store/'+1)
        http.get('/goods/store/'+this.storeId)
        .then((res)=>{
            this.goodsList = res.data;
            console.log('dddd'+this.goodsList)
        })
        .catch((e) => {
            console.log("오류")
            console.log(e);
        })
    },
    methods:{
        Refresh(){
            console.log("새로고침");
            http.get('/goods/store/'+this.storeId)
            .then((res)=>{
                this.goodsList = res.data;
                console.log('dddd'+this.goodsList)
            })
            .catch((e) => {
                console.log("오류")
                console.log(e);
            })
        },
        goBack(){
            console.log(this.storeId);
            this.$router.push({name:'Store', params:{storeId : this.storeId}});
        }
    }

}
</script>

<style scoped>
#goodslist{
    width: 66.6vw;
    height: 100vh;
    background-image: linear-gradient(45deg, rgb(136, 92, 27),rgb(231, 208, 174));
    overflow: auto;
}
</style>