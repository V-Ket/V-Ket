<template>
  <div>
      <!-- 사진
      상품명
      상품 가격
      상품 수량
      상품 설명
      상품 담당 상점 아이디 -->
      
    <v-form v-model="valid">
    <v-container class="m-t-50" style="max-width: 800px; background: #EAF5F1;">
      <v-row class="justify-center">
        <!-- <v-col cols="3" align="right"><div id="thumb">썸네일</div> </v-col> -->
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
            <v-btn block text x-large class="primary-color text-white rounded-0" @click="regist">
              등록
            </v-btn>
          </v-col>
          <v-col cols="3">
            <v-btn block text x-large class="secondary-color text-white rounded-0" @click="cancel">
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
    goodsId: String,
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
    // 상품 수정이면 상품을 가져와 인풋에 넣는다.
    if(this.isUpdate){
      http.get("/goods/" + this.goodsId)
      .then((res) => {
        console.log(res);
        this.goodsName = res.data.goodname;
        this.goodsPrice = res.data.goodsPrice;
        this.goodsQuantity = res.data.goodsQuantity;
        this.goodsContent = res.data.goodsContent;
        // 이미지는 어떻게 하지...? ㅋㅋㅋㅋㅋ
      })
      .catch((e) => {
        console.log("상품 정보를 가져오는데 오류났어요~~!")
        console.log(e);
      })

    }

  },
  methods:{
    regist(){
      if(!this.vaild){
        alert("정보를 모두 입력해 주세요")
      }else{
        let goods = new FormData();
        goods.append('goodsName', this.goodsName);
        goods.append('goodsPrice', this.goodsPrice);
        goods.append('goodsImg', this.goodsImg);
        goods.append('goodsQuantity', this.goodsQuantity);
        goods.append('goodsContent', this.goodsContent);
        goods.append('storeId', this.storeid);

        http.post('/goods', goods, {
          headers: { 'Content-Type': 'multipart/form-data' },
        })
        .then((res) => {
          console.log(res);
          alert("상품등록 완료!");
          this.cancel();
        })
        .catch((e) => {
          console.log("상품 등록 에러~!");
          console.log(e);
        })
      }
    },
    cancel(){
      this.$emit('closeGoodsModal');
    }


  }

}
</script>

<style>

</style>