<template>
  <div>
    <v-img id = "background" src="images/background5.gif"></v-img>
    <div class="text">
      캐릭터를 선택해주세요.
    </div>
    <div>
      <img class="char1" v-bind:style="[select1?{'filter':'drop-shadow(0px 0px 15px rgba(255, 255, 0))'}:{}]" @click="selected1" src="images/character/char1.png" />
      <img class="char2" v-bind:style="[select2?{'filter':'drop-shadow(0px 0px 15px rgba(255, 255, 0))'}:{}]" @click="selected2" src="images/character/char2.png" />
      <img class="char3" v-bind:style="[select3?{'filter':'drop-shadow(0px 0px 15px rgba(255, 255, 0))'}:{}]" @click="selected3" src="images/character/char5.png" />
      <img class="char4" v-bind:style="[select4?{'filter':'drop-shadow(0px 0px 15px rgba(255, 255, 0))'}:{}]" @click="selected4" src="images/character/char3.png" />
      <img class="char5" v-bind:style="[select5?{'filter':'drop-shadow(0px 0px 15px rgba(255, 255, 0))'}:{}]" @click="selected5" src="images/character/char4.png" />
      <img class="char6" v-bind:style="[select6?{'filter':'drop-shadow(0px 0px 15px rgba(255, 255, 0))'}:{}]" @click="selected6" src="images/character/char6_2.png" />
    </div>
    <div class="joinButtonDiv">
      <button class="joinButton" @click="joinUnity"><b>접속하기</b></button>
    </div>
  </div>
</template>
<script>
export default {
  name: "SelectCH",
  data (){
    return{
      isSelect:false,
      characterNum: '',
      select1:false,
      select2:false,
      select3:false,
      select4:false,
      select5:false,
      select6:false,
    }
  },
  methods: {
    joinUnity(){
      if(this.isSelect){
        this.$router.push({name:'Unity'})
      }else{
        this.$swal({
          icon: 'warning',
          title: '캐릭터를 선택해주세요.'
        })
      }
    },
    selected1(){
      this.characterNum = 1
      this.$store.commit('setChracterNum', 0)
      this.selectfalse()
      this.select1 = true
      this.isSelect = true
    },
    selected2(){
      this.characterNum = 2
      this.$store.commit('setChracterNum', 1)
      this.selectfalse()
      this.select2 = true
      this.isSelect = true
    },
    selected3(){
      this.characterNum = 3
      this.$store.commit('setChracterNum', 2)
      this.selectfalse()
      this.select3 = true
      this.isSelect = true
    },
    selected4(){
      this.characterNum = 4
      this.$store.commit('setChracterNum', 4)
      this.selectfalse()
      this.select4 = true
      this.isSelect = true
    },
    selected5(){
      this.characterNum = 5
      this.$store.commit('setChracterNum', 3)
      this.selectfalse()
      this.select5 = true
      this.isSelect = true
    },
    selected6(){
      this.characterNum = 6
      this.$store.commit('setChracterNum', 5)
      this.selectfalse()
      this.select6 = true
      this.isSelect = true
    },
    selectfalse(){
      this.select1 = false
      this.select2 = false
      this.select3 = false
      this.select4 = false
      this.select5 = false
      this.select6 = false
      this.isSelect = false
    }
  },
}
</script>
<style scoped>
.text{
  position:absolute;
  top: 25%;
  left: 35%;
  font-size: 50px;
  color: white;
  font-weight: bold;

}
#background {
  width: 100vw;
  height: 100vh;
}
.joinButtonDiv{
  position:absolute;
  border-radius: 10px;
  left: 50%;
  top: 10%;
  width: 9vw;
  height: 1vh;
  transform: translate(400%,100%);
}
.joinButton{
  padding: 10px;
  border: 1px solid black;
  border-radius: 7px;
  background-color: #eee;
  box-shadow: 5px 3px 10px rgb(1, 8, 32);
}
button:hover{
  filter: drop-shadow(0px 0px 10px rgb(243, 243, 243));
}
img:hover{
  filter: drop-shadow(0px 0px 15px rgba(255, 255, 0));
}
.char1{
  position:absolute;
  border-radius: 10px;
  left: 20%;
  top: 65.5%;
  width: 8vw;
  height: auto;
}
.char2{
  position:absolute;
  border-radius: 10px;
  left: 30%;
  top: 65%;
  width: 8vw;
  height: auto;
}
.char3{
  position:absolute;
  border-radius: 10px;
  left: 40%;
  top: 64.8%;
  width: 8vw;
  height: auto;
}
.char4{
  position:absolute;
  border-radius: 10px;
  left: 50%;
  top: 64%;
  width: 8vw;
  height: auto;
}
.char5{
  position:absolute;
  border-radius: 10px;
  left: 60%;
  top: 66.5%;
  width: 8vw;
  height: auto;
}
.char6{
  position:absolute;
  border-radius: 10px;
  left: 70%;
  top: 65.5%;
  width: 8vw;
  height: auto;
}

</style>