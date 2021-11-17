<template>
    <!-- <v-card class="px-5 py-3">
        <div class="d-flex justify-content-center py-3">
            <v-card-title>
                <span>상점 열기</span>
            </v-card-title>
        </div>
        <v-form>
            <v-container>
                <v-row justify="start">
                    <v-col cols="2" align="right">상점 이름</v-col>
                    <v-col>
                        <v-text-field
                            v-model="storeName"
                            required
                            filled
                            ></v-text-field>
                        </v-col>
                </v-row>
                <v-row justify="start">
                    <v-col cols="2" align="right">상점 URL</v-col>
                    <v-col>
                        <v-text-field
                            v-model="storeURL"
                            required
                            filled
                            ></v-text-field>
                        </v-col>
                </v-row>
                <v-row justify="start">
                    <v-col cols="2" align="right">상점 간단 설명</v-col>
                    <v-col>
                        <v-textarea
                            v-model="storeContent"
                            required
                            filled
                            height="200"
                        ></v-textarea>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </v-card> -->

  <div>
    <div id="box">
        <div class="divbox">
            <b>상점 이름</b>
            <input v-model="storeName" class="input" style="margin-left: 30px"/><br>
            <br>
            <b>상점 URL</b>
            <input v-model="storeURL" class="input" style="margin-left: 30px"/><br>
            <br>
            <b>상점 설명</b>
            <textarea v-model="storeContent" class="input" style="margin-left: 30px; width:200px; height:100px"/><br>
        </div>
        <div class="buttons">
            <button v-if="isUpdate" class="btn" style="margin-right: 40px; margin-left: 40px" @click="updateStore"><b>수정</b></button>
            <button v-else class="btn" style="margin-right: 40px; margin-left: 40px" @click="setStore"><b>등록</b></button>
            <button class="btn" @click="cancle"><b>취소</b></button>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/http.js";
export default {
    props:{
        islandpos:String,
        storepos:String,
        storeid:String,
    },
    data(){
        return{
            storeName: "",
            storeURL: "",
            userId: "",
            storeContent: "",
            islandPos : "",
            storePos : "",
            storeId: "",
            isUpdate: false,
        }
    },
    mounted(){
        if(this.islandpos != null){
            console.log(this.islandpos);
            this.islandPos = this.islandpos
        }
        if(this.storepos != null){
            console.log(this.storepos);
            this.storePos = this.storepos
        }
        if(this.islandpos == null && this.storepos == null){
            console.log("상점 수정으로 들어왔습니다.")
            console.log(this.storeid);
            this.storeId = this.storeid;
            this.isUpdate = true;
            // 상점 정보 받아와서 그래도 뿌려주기
            http.get("/store/select/" + this.storeId)
            .then((res) => {
                console.log(res);
                this.storeName = res.data.storeName;
                this.storeContent = res.data.storeContent;
                this.storeUrl = res.data.storeUrl;
            })
            .catch((e) => {
                console.log("상점 가져오는데 오류 났어요~")
                console.log(e);
            })

        }
        // const body = {
        //     islandId : 1001,
        //     storeIslandNum : 2,
        //     userId : "test",
        //     storeName : "testGetNewStore",
        //     storeContent : "testGetNewStoreContent",
        //     storeUrl : "testURL",
        // }

        // http.post("/store/regist", body)
        // .then((res) => {
        //     console.log(res);
        //     alert("상점 등록 완료")
        // })
        // .catch((e) =>{
        //     console.log(e);
        // })
    },
    methods:{
        setStore(){
            console.log("상점 등록합니다.@@@@@@@@@@@@@@");
            console.log(this.islandPos);
            console.log(this.storePos);
            console.log(this.userId);
            console.log(this.storeName);
            console.log(this.storeContent);
            console.log(this.storeURL);

            const body = {
                islandId : Number(this.islandPos),
                storeIslandNum : Number(this.storePos),
                userId : localStorage.getItem("userId"),
                storeName : this.storeName,
                storeContent : this.storeContent,
                storeUrl : this.storeURL,
            }

            http.post("/store/regist", body)
            .then((res) => {
                console.log(res);
                // alert("상점 등록 완료")
                if(res.status == 201){
                    this.$swal({
                        icon: 'success',
                        title: '상점 등록 완료!',
                        text: '상점에서 물품을 등록해주세요!'
                    })
                }else{
                    this.$swal({
                        icon: 'error',
                        title: '이미 상점이 있습니다!'
                    })
                }
            })
            .catch((e) =>{
                console.log(e);
            })
            
            setTimeout(() => {
                // this.$router.push({name:'Unity'})
                this.$emit('open');
            }, 100);
        },
        updateStore(){
            const body = {
                storeId : this.storeId,
                storeName : this.storeName,
                storeContent : this.storeContent,
                storeUrl : this.URL,
            }

            http.put("/store/storeUpdateAll", body)
            .then((res) => {
                console.log(res)
                this.cancle();
            })
            .catch((e) => {
                console.log("상점 수정 오류")
                console.log(e)
            })
        },
        cancle(){
            this.$emit('closeStoreModal');
        }
    }
}
</script>

<style scoped>
#test{
    background-image: url("/images/Main_Background.png");
    width: 80vw;
    height: 50vw;
}
#box{
  position:absolute;
  border: 2px solid black;
  border-radius: 10px;
  left: 35%;
  top: 15%;
  width: 30vw;
  height: 40vh;
  transform: translate(-50%,10%);
  background-color: #eee;
}
.input{
  position:absolute;
  border: 2px solid black;
  border-radius: 7px;
}
.divbox{
    margin-left: 5vw;
    margin-top: 3vh;
}
.buttons{
  margin-left: 10vw;
  margin-top: 13vh;
}
.btn{
  border: 1px solid black;
  background-color: #eee;
}
</style>