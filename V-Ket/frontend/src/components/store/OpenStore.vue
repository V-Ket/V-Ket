<template>
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
            this.islandPos = this.islandpos
        }
        if(this.storepos != null){
            this.storePos = this.storepos
        }
        if(this.islandpos == null && this.storepos == null){
            this.storeId = this.storeid;
            this.isUpdate = true;
            // 상점 정보 받아와서 그래도 뿌려주기
            http.get("/store/select/" + this.storeId)
            .then((res) => {
                this.storeName = res.data.storeName;
                this.storeContent = res.data.storeContent;
                this.storeUrl = res.data.storeUrl;
            })
            .catch((e) => {
                this.$swal({
                    icon: 'warning',
                    title: '중복확인을 해주세요.',
                    text: e
                })
            })
        }
    },
    methods:{
        setStore(){
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
            .then(() => {
                this.cancle();
            })
            .catch((e) => {
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