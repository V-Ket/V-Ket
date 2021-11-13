<template>
    <v-col class="col-6 mt-3" align="center" id="up">
      <div
        elevation="10"
        shaped
        outlined
        class="m-2 goods-card"
      >
        <div class="container" id="goods">
          <div class="row">
            <div class="col-5">
            <!-- 이미지 넣을 자리 -->
              <div>
                <img :src='goods.goodsImg' id="goodsImg">
              </div>
            </div>
            <div class="col-7">
              <div class="container">
              <!-- 상품 이름 들어갈 자리 -->
                <div class="row">
                  <div class="col-12" style="padding:0;">
                    <div id="goodsName">{{goods.goodsName}}</div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-12" style="padding:0;">

                  <div id="goodsContent">{{goods.goodsContent}}</div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-6" style="padding:0;">
                    <div id="goodsQuntity">재고 : {{goods.goodsQuantity}}</div>
                  </div>
                  <div class="col-6" style="padding:0;">
                    <div id="goodsPrice">가격 : {{goods.goodsPrice}}</div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-7" style="padding:0;"></div>
                  <div class="col-5" style="padding:0;">
                    <div v-if="isHost">
                      <button id="btn" @click="update">수정하기</button>
                    </div>
                    <div v-else>
                      <button id="btn">구매하기</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <v-dialog
        v-model="isOpenGoodsModal"
        max-width="500px"
        > <GoodsModal class="temp" :goods="this.goods" :storeid="''+this.goods.storeId" :isUpdate="isUpdate" @closeGoodsModal="closeGoodsModal" style="z-index:1000"  />
      </v-dialog>

    </v-col>
</template>

<script>
import GoodsModal from '@/components/store/GoodsModal.vue';
export default {
  name: 'Goods',
  props: ['goods','hostId'],
  data(){
    return{
      isUpdate: true,
      isOpenGoodsModal: false,
    }
  },
  components:{
    GoodsModal,
  },
  computed:{
    isHost: function(){
      if(this.$props.hostId == localStorage.getItem('userId')){
        return true;
      }else{
        return false;
      }
    }
  },
  methods:{
    // S3 서버에서 
    getImgPath: function() {
      //
      // return this.fileURL + '' + this.goods;
    },
    update(){
      this.isOpenGoodsModal = true;
    },
    closeGoodsModal(){
      this.isOpenGoodsModal = false;
      this.$emit("Refresh");
      // this.$router.push({name: "GoodsList", params:{storeId : this.goods.storeId, hostId : this.hostId}});
    }
  }
}
</script>

<style scoped>
#up{
  /* background-color: red; */
  border-radius: 100px;
}
#goods{
  width: 100%;
  background-color: #eee;
  border-radius: 10px;
}
#goodsImg{
  background-color: white;
  width: 12vw;
  height: 23vh;
  border: 1.5px solid black;
  border-style: dotted;
  border-radius: 10px;
}
#goodsName{
  padding: 1px;
  /* width: 17.5vw; */
  border: 1.5px solid black;
  border-style: dotted;
  border-radius: 10px;
  background-color: #f8f8f8;
  font-weight: bold;
}
#goodsContent{
  padding: 12px 20px;
  margin-top: 1vh;
  height: 13vh;
  border: 1.5px solid black;
  border-style: dotted;
  border-radius: 10px;
  background-color: #f8f8f8;
  overflow: auto;
}
#goodsQuntity{
  margin-top: 1vh;
  border: 1.5px solid black;
  border-style: dotted;
  border-radius: 10px;
  background-color: #f8f8f8;
  color: red;
}
#goodsPrice{
  margin-top: 1vh;
  border: 1.5px solid black;
  border-style: dotted;
  border-radius: 10px;
  background-color: #f8f8f8;
  font-weight: bold;
}
#btn{
  padding: 5px;
  margin-top: 1vh;
  /* border: 1px solid black; */
  border-radius: 10px;
  background-color: rgb(238, 144, 57);
  color: white;
  font-weight: 500;
  box-shadow: 0 0 5px red;
}
</style>