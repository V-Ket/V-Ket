<template>
    <div id="goodslist">
        <div class="container">
            <div class="row">
                <div class="col-9">

                </div>
                <div class="col-3" id="back">
                    <button id="back-btn" @click="goBack">뒤로가기</button>
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
        http.get('/goods/store/'+this.storeId)
        .then((res)=>{
            this.goodsList = res.data;
        })
        .catch((e) => {
            console.log(e);
        })
    },
    methods:{
        Refresh(){
            http.get('/goods/store/'+this.storeId)
            .then((res)=>{
                this.goodsList = res.data;
            })
            .catch((e) => {
                console.log(e);
            })
        },
        goBack(){
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
#back{
  margin-left: 58.5vw;
}
#back-btn{
  border: 1px solid black;
  padding: 1vh;
  border-radius: 10px;
  font-weight: bold;
  color: white;
  background-color: brown;
}

</style>