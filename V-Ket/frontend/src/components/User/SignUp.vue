<template>
  <div>
    <div class="id">
    <b>아이디: </b> <input class="idInput" v-model="userId"/>
    <button class="btn" style="margin-left:1.5vw" @click="idValid()"><b>중복확인</b></button><br>
    </div>
    <div class="pw">
    <b>비밀번호: </b><input class="pwInput" v-model="userPassword"/><br>
    </div>
    <div class="ni">
    <b>닉네임: </b><input class="niInput" v-model="userNickname"/>
    <button class="btn" style="margin-left:1.5vw" @click="nicknameValid()"><b>중복확인</b></button><br>
    </div>
    <div class="ph">
    <b>전화번호: </b><input class="phInput" v-model="userPhone"/><br>
    </div>
    <div class="buttons" style="margin-top:6vh">
    <button class="btn" style="margin-left:3vw; margin-right:3vw" @click="signUp()"> <b>회원가입</b> </button>
    <button class="btn" @click="$emit('close-modal')"> <b>닫기</b> </button>
    </div>
  </div>
</template>
<script>
import http from '@/http.js';

export default {
  name: 'SignUp',

  data() {
    return {
      userSeq: '',
      userId: '',
      userNickname: '',
      userPhone: '',
      userToken: '',
      userPassword: '',
      valid_id : false,
      valid_nickname: false,
    };
  },
  methods: {
    signUp() {
      if(this.valid_id && this.valid_nickname){
        const body = {
        userId : this.userId,
        userPassword : this.userPassword,
        userNickname : this.userNickname,
        userPhone : this.userPhone
        }
        http.post('/user/signUp', body)
        .then(()=>{
          this.$swal({
            icon: 'success',
            title: '회원가입 성공!',
            text: '환영합니다! 로그인해 주세요 :)'
          })
          this.$emit('close-modal')
          // alert(res+"가입성공!")
        })
      }else{
        this.$swal({
            icon: 'warning',
            title: '중복확인을 해주세요.'
          })
      }
    },
    idValid(){
      http.get('/user/userId/' + this.userId)
      .then((res)=>{
        console.log(res.status)
        if(res.status==201){
          this.valid_id = true;
          this.$swal({
            icon: 'success',
            title: '사용가능한 아이디입니다.'
          })
        }else{
          this.valid_id = false;
          this.$swal({
            icon: 'error',
            title: '사용중인 아이디입니다.',
            text: '다른 아이디를 입력해주세요.'
          })
        }
      })
    },
    nicknameValid(){
      http.get('/user/userNickname/'+this.userNickname)
      .then((res)=>{
        if(res.status==201){
          this.valid_nickname = true;
          this.$swal({
            icon: 'success',
            title: '사용가능한 닉네임입니다.'
          })
          // alert('사용가능한 닉네임입니다.')
        }else{
          this.valid_nickname = false;
          this.$swal({
            icon: 'error',
            title: '사용중인 닉네임입니다.',
            text: '다른 닉네임을 입력해주세요.'
          })
          // alert('닉네임이 중복입니다.')
        }
      })
    }
  },
};
</script>

<style scoped>
.id{
  margin-left: 3vw;
  margin-top: 6vh;
}
.pw{
  margin-left: 3vw;
  margin-top: 3vh;
}
.ni{
  margin-left: 3vw;
  margin-top: 3vh;
}
.ph{
  margin-left: 3vw;
  margin-top: 3vh;
}
.idInput{
  margin-left: 1.5vw;
  border: 1px solid black;
  border-radius: 7px;
}
.pwInput{
  margin-left: 0.5vw;
  border: 1px solid black;
  border-radius: 7px;
}
.niInput{
  margin-left: 1.5vw;
  border: 1px solid black;
  border-radius: 7px;
}
.phInput{
  margin-left: 0.5vw;
  border: 1px solid black;
  border-radius: 7px;
}
.buttons{
  margin-left: 5vw;
  margin-top: 2vh;
}
.btn{
  border: 1px solid black;
  background-color: #eee;
}
</style>