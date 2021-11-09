<template>
  <div>
    <p>ddd</p>
    <table>
      <tr v-for="(meeting, i) in meetings"
      :key="i">
        <td>{{meeting.buyerId}}</td>
        <td><button>접속하기</button></td>
      </tr>
    </table>
  </div>
</template>
<script>
import http from '@/http.js';

export default {
  name: "MeetingList",
  data() {
    return {
      // interviews: Array,
      meetings: Array,
      // userType: String,
      sellerId: String,
    }
  },
  mounted() {
    // this.userType = localStorage.getItem('login_type')
    // if (this.userType == 'user') {
    //   http.get('/user/myinterview/' + localStorage.getItem('id')) 
    //   .then(res => {
    //     this.interviews =  res.data
    //   })
    // } else if (this.userType == 'company') {
    //   http.get('/company/companyinterviewinfo/' + this.$route.params.id)
    //   .then(res => {
    //     this.interviews =  res.data
    //   })
    // }

    // 여기에서 setInterval로 리스트 계속 가져옴
    this.sellerId = localStorage.getItem('userId')
    setInterval(() => {
      http.get('/session/getlist/' + this.sellerId)
      .then((res)=>{
        this.meetings = res.data
        this.$store.commit('setMeetingListSize', this.meetings.length)
      })
    }, 1000);

  },
  methods: {
    // clickInterview() {
    //   alert('면접 열기')
    // },
    // clickProfile(interview) {
    //   if (this.userType == 'user') {
    //     this.$router.push('/mypage/company/' + interview.companyId)
    //   } else if (this.userType == 'company') {
    //     this.$router.push('/mypage/user/' + interview.userNickname)
    //   }
    // },
    // interviewId(interview) {
    //   if (this.userType == 'user') {
    //     return interview.companyId
    //   } else if (this.userType == 'company') {
    //     return interview.userNickname
    //   }
    // }
  },
}
</script>

<style>
.interview-table-box {
  border: solid #eee 1px;
  width: 800px;
  height: 100%;
}

.interview-table-box.theme--light.v-data-table {
  background-color: #EAF5F1;
}


.interview-table-content {
  background-color: #C0DDD1;
  border-radius: 5px;
  border: black 1px solid;
}

.interview-table-box.theme--light.v-data-table--fixed-header thead tr th {
  background-color: #EAF5F1;
}

.interview-button {
    width: 100px;
    background: #439474;
    padding: 0px;
}

.interview-table-body {
  border-radius: 5px;
  overflow: hidden;
  border: 1px black solid;
}
</style>