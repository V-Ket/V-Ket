<template>
  <div>
    <v-img id = "background" src="images/background5.gif"></v-img>
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
      this.$router.push({name:'Unity'})
    },
    selected1(){
      this.characterNum = 1
      this.$store.commit('setChracterNum', 1)
      this.selectfalse()
      this.select1 = true
      // alert(this.characterNum)
      alert(this.$store.getters.getCharacterNum)
    },
    selected2(){
      this.characterNum = 2
      this.$store.commit('setChracterNum', 2)
      this.selectfalse()
      this.select2 = true
      // alert(this.characterNum)
      alert(this.$store.getters.getCharacterNum)
    },
    selected3(){
      
      alert(this.$store.getters.getCharacterNum)
      this.characterNum = 3
      this.selectfalse()
      this.select3 = true
      // alert(this.characterNum)
    },
    selected4(){
      this.characterNum = 4
      this.selectfalse()
      this.select4 = true
      // alert(this.characterNum)
    },
    selected5(){
      this.characterNum = 5
      this.selectfalse()
      this.select5 = true
      // alert(this.characterNum)
    },
    selected6(){
      this.characterNum = 6
      this.selectfalse()
      this.select6 = true
      // alert(this.characterNum)
    },
    selectfalse(){
      this.select1 = false
      this.select2 = false
      this.select3 = false
      this.select4 = false
      this.select5 = false
      this.select6 = false
    }
  },
}
</script>
<style scoped>
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
  /* box-shadow: 0 0 15px yellow; */
  filter: drop-shadow(0px 0px 10px rgb(243, 243, 243));
}
img:hover{
  /* box-shadow: 0 0 15px yellow; */
  filter: drop-shadow(0px 0px 15px rgba(255, 255, 0));
}
.char1{
  position:absolute;
  border-radius: 10px;
  left: 50%;
  top: 10%;
  width: 10vw;
  height: auto;
  transform: translate(-400%,220%);
}
.char2{
  position:absolute;
  border-radius: 10px;
  left: 50%;
  top: 10%;
  width: 10vw;
  height: auto;
  transform: translate(-270%,210%);
}
.char3{
  position:absolute;
  border-radius: 10px;
  left: 50%;
  top: 10%;
  width: 10vw;
  height: auto;
  transform: translate(-120%,205%);
}
.char4{
  position:absolute;
  border-radius: 10px;
  left: 50%;
  top: 10%;
  width: 10vw;
  height: auto;
  transform: translate(10%,190%);
}
.char5{
  position:absolute;
  border-radius: 10px;
  left: 50%;
  top: 10%;
  width: 10vw;
  height: auto;
  transform: translate(140%,228%);
}
.char6{
  position:absolute;
  border-radius: 10px;
  left: 50%;
  top: 10%;
  width: 10vw;
  height: auto;
  transform: translate(270%,208%);
}

</style>