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
                      <button id="btn" @click="buy(goods)">구매하기</button>
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
import http from '@/http.js';

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
    update(){
      this.isOpenGoodsModal = true;
    },
    closeGoodsModal(){
      this.isOpenGoodsModal = false;
      this.$emit("Refresh");
    },
    buy(goods){
      if(this.$props.goods.goodsQuantity==0 || this.$props.goods.goodsPrice>this.$store.getters.credit){
        this.$swal({
          icon: 'error',
          title: '구매할 수 없습니다'
        })
      }else{
        this.$swal({
          icon: 'warning',
          title: '정말 구매하시겠습니까?',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: '네',
          cancelButtonText:' 아니오'
        })
        .then((result)=>{
          if(result.isConfirmed){
            const body = {
              buyerId : localStorage.getItem('userId'),
              sellerId : this.$props.hostId,
              goodsId: goods.goodsId,
              goodsName : goods.goodsName,
              eachPrice : goods.goodsPrice,
              quantity : 1,
              goodsImg : goods.goodsImg
            }
            http.post('/deal/regist',body)
            .then(()=>{
              this.$props.goods.goodsQuantity--;
              this.$store.commit('setCredit', this.$store.getters.credit - this.$props.goods.goodsPrice)
            })
            this.$swal({
              icon: 'success',
              title: '구매가 완료되었습니다.',
            })
          }
        })
      }
    },
  }
}
</script>

<style scoped>
#up{
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
  border-radius: 10px;
  background-color: rgb(238, 144, 57);
  color: white;
  font-weight: 500;
  box-shadow: 0 0 5px red;
}
</style>