<template>
  <div>
      <!-- 사진
      상품명
      상품 가격
      상품 수량
      상품 설명
      상품 담당 상점 아이디 -->
      
    <v-form v-model="valid" id="editModal">
    <v-container class="m-t-50" style="max-width: 800px;">
      <v-row class="justify-center">
        <v-col cols="12">
          <v-row class="justify-center">
            <v-col cols="6">
              <v-file-input
                v-model="goodsImg"
                required
                label="상품 이미지"
                show-size
              ></v-file-input>
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12">
          <v-row>
            <v-col >
              <v-text-field
                v-model="goodsName"
                required
                label="상품 이름"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12">
          <v-row>
            <v-col >
              <v-text-field
                v-model="goodsPrice"
                required
                type="number"
                label="상품 가격"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12">
          <v-row>
            <v-col >
              <v-text-field
                v-model="goodsQuantity"
                required
                type="number"
                label="상품 수량"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12">
          <v-row>
            <v-col>
              <v-textarea
                v-model="goodsContent"
                required
                label="상품 설명"
              ></v-textarea>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <v-container style="max-width: 800px; height: 200px;">
      <v-col>
        <v-row class="d-flex justify-content-end">
          <v-col cols="3">
            <v-btn v-if="this.isUpdate == false" block text x-large class="btn-add text-white" @click="regist">
              등록
            </v-btn>
            <v-btn v-else block text x-large class="btn-add text-white" @click="update">
              수정
            </v-btn>
          </v-col>
          <v-col cols="3">
            <v-btn block text x-large class="btn-cancle text-white" @click="cancel">
              취소
            </v-btn>
          </v-col>
        </v-row>
      </v-col>
    </v-container>
  </v-form>

  </div>
</template>

<script>

import http from '@/http.js';

export default {
  name: "GoodsModal",
  props:{
    storeid: String,
    goods: Object,
    isUpdate: Boolean,
  },
  data(){
    return{
      goodsName: '',
      goodsPrice: '',
      goodsImg: null,
      goodsQuantity: '',
      goodsContent: '',
      vaild: true,
    }
  },
  mounted(){

    console.log(this.goods)
    console.log(this.isUpdate)

    // 상품 수정이면 상품을 가져와 인풋에 넣는다.
    if(this.isUpdate == true){
      console.log("업데이트로 들어왔습니다.");
      this.goodsName = this.goods.goodsName;
      this.goodsPrice = this.goods.goodsPrice;
      this.goodsImg = null;
      this.goodsQuantity = this.goods.goodsQuantity;
      this.goodsContent = this.goods.goodsContent;
    }

  },
  methods:{
    regist(){
      if(!this.vaild){
        this.$swal({
          icon: 'warning',
          title: '정보를 모두 입력해 주세요',
        })
      }else{
        let goodsInfo = new FormData();
        goodsInfo.append('goodsName', this.goodsName);
        goodsInfo.append('goodsPrice', this.goodsPrice);
        goodsInfo.append('goodsImg', this.goodsImg);
        goodsInfo.append('goodsQuantity', this.goodsQuantity);
        goodsInfo.append('goodsContent', this.goodsContent);
        goodsInfo.append('storeId', this.storeid);
          http.post('/goods', goodsInfo, {
            headers: { 'Content-Type': 'multipart/form-data' },
          })
          .then(() => {
            this.$swal({
              icon: 'success',
              title: '상품등록 완료!',
            })
            this.cancel();
          })
          .catch(() => {
            this.$swal({
              icon: 'error',
              title: '상품 등록 에러~!',
            })
          })
        
      }
    },
    update(){
      let goodsInfo = new FormData();
        goodsInfo.append('goodsName', this.goodsName);
        goodsInfo.append('goodsPrice', this.goodsPrice);
        if(this.goodsImg != null){
          goodsInfo.append('goodsImg', this.goodsImg);
        }
        goodsInfo.append('goodsQuantity', this.goodsQuantity);
        goodsInfo.append('goodsContent', this.goodsContent);
        goodsInfo.append('storeId', this.storeid);
        goodsInfo.append('goodsId', this.goods.goodsId);
        http.put('/goods', goodsInfo, {
          headers: { 'Content-Type': 'multipart/form-data' },
        })
        .then(() => {
          this.$swal({
              icon: 'success',
              title: '상품등록 완료!',
            })
          this.cancel();
        })
        .catch(() => {
          this.$swal({
              icon: 'error',
              title: '상품 등록 에러~!',
            })
        })
    },
    cancel(){
      this.$emit('closeGoodsModal');
    }


  }

}
</script>

<style>
#editModal{
  border: 2px solid black;
  border-radius: 10px;
  background-color: #eee;
}

.btn-add{
  background-color: green;
  border-radius: 10px;
  width: 5vw;
  height: 4vh;
  color: white;
  font-weight: bold;
}

.btn-cancle{
  background-color: brown;
  border-radius: 10px;
  width: 5vw;
  height: 4vh;
  color: white;
  font-weight: bold;
}

</style>