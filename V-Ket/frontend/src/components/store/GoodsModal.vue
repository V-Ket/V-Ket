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
                <!-- 상품 이미지
              <v-file-input
                v-model="files"
                required
                :rules="[(v) => !!v || '필수 항목입니다']"
                label="영상 업로드"
                show-size
              ></v-file-input> -->
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12">
          <v-row>
            <v-col >
                <!-- 상품 이름
              <v-text-field
                v-model="title"
                required
                label="제목"
                :rules="[(v) => !!v || '필수 항목입니다']"
              ></v-text-field> -->
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12">
          <v-row>
            <v-col >
                <!-- 상품 가격
              <v-text-field
                v-model="title"
                required
                label="제목"
                :rules="[(v) => !!v || '필수 항목입니다']"
              ></v-text-field> -->
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12">
          <v-row>
            <v-col >
                <!-- 상품 수량
              <v-text-field
                v-model="title"
                required
                label="제목"
                :rules="[(v) => !!v || '필수 항목입니다']"
              ></v-text-field> -->
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12">
<!--             
            상품 설명
          <v-row>
            <v-col>
              <v-textarea
                v-model="content"
                required
                label="내용"
                :rules="[(v) => !!v || '필수 항목입니다']"
              ></v-textarea>
            </v-col>
          </v-row> -->
        </v-col>
      </v-row>
    </v-container>
    <v-container style="max-width: 800px; height: 200px;">
      <v-col>
        <v-row class="d-flex justify-content-end">
<!--           등록 or 취소 버튼
          <v-col cols="3">
            <v-btn block text x-large class="primary-color text-white rounded-0" @click="regist">
              등록
            </v-btn>
          </v-col>
          <v-col cols="3">
            <v-btn block text x-large class="secondary-color text-white rounded-0" @click="cancel">
              취소
            </v-btn>
          </v-col> -->
        </v-row>
      </v-col>
    </v-container>
  </v-form>

  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>