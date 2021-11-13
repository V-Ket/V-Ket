<template>
  <v-row class="row d-flex" style="max-width:80vw; margin:auto;">
      <Goods v-for="(goods, idx) in goodsList"
      :key="idx+'goods'"
      :goods="goods" />
  </v-row>
</template>

<script>
import http from '@/http.js';
import Goods from '@/components/store/Goods.vue';
export default {
    name: "GoodsList",
    props:{
        storeId : String,
        hostId : String,
    },
    data(){
        return{
            goodsList: Array,
        }
    },
    components:{
        Goods,
    },
    mounted(){
        console.log("상품 상세보기");
        console.log(this.storeId);
        console.log(this.hostId);
        http.get('/goods/store/'+this.storeId)
        .then((res)=>{
            this.goodsList = res.data;
        })
        .catch((e) => {
            console.log("오류")
            console.log(e);
        })
    }

}
</script>

<style>

</style>