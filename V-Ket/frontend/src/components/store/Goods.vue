<template>
  <div>
      <v-col cols="6" align="center">
  <v-card
    elevation="1"
    outlined
    class="m-2 goods-card"
  >
    <div class="goods d-flex flex-column px-3 pt-4">
      <div class="d-flex">
        <div class="goodsLogo-box d-flex align-center justify-center">
          <!-- 이미지 넣을 자리 -->
          <!-- <img class="rounded goodsLogo" :src="getImgPath()" :alt="goods.goodsName" /> -->
        </div>
        <div class="goodsContent ms-5">
          <div class="d-flex align-center justify-space-between">
            <!-- 상품 이름 들어갈 자리 -->
            <!-- <h4>{{ goods.goodsName }}</h4> -->
            <!-- <v-btn
              text rounded class="third-color text-white"
              v-if="goods.sessionId"
              @click="enterPresentation(goods.sessionId, goods.goodsName)">live-on</v-btn> -->
          </div>
          <br />
          <!-- 상품 정보등 2개 -->
          <!-- <h6 class="mb-2" style="text-align:left">{{ goods.career }}</h6> -->
          <!-- <h6 style="text-align:left">[{{ goods.job }}] {{ goods.jobDetail }}</h6> -->
        </div>
      </div>
      <div class="d-flex justify-content-between align-end mt-5">
        <!-- 구매하기 또는 수정하기 v-if로 -->
        <!-- <v-chip label v-if="goods.presentationDate">
          {{ goods.presentationDate | moment('MM월 DD일 HH:mm') }} 설명회
        </v-chip> -->
      </div>
    </div>
  </v-card>
  </v-col>
  </div>
</template>

<script>
export default {
  name: 'Goods',
  props: ['goods'],
  methods:{
    // S3 서버에서 
    getImgPath: function() {
      //
      // return this.fileURL + '' + this.goods;
    },
  }
}
</script>

<style>
h5 {
  margin-top: 5px;
  margin-bottom: 5px;
}

img {
  background: white;
  width: 150px;
  height: 150px;
  object-fit: cover;
}

.goods {
  width: 100%;
}

.goods-card {
  width: 530px;
  height: 180px;
  position: relative;
}

.goodsLogo-box {
  width: 100px;
  height: 100px;
}

.goodsLogo {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.goodsContent {
  width: 400px;
}

.primary-color {
  color: #439474;
}

.secondary-color {
  color: #7d7d7d;
}

.third-color {
  background: #ff5e5e;
}
</style>