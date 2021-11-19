![navLogo](https://user-images.githubusercontent.com/56018285/142551393-1e736b76-7f4d-4f8c-aaff-17fb78aec22d.png)

<h1 align=center><b>메타버스 플리마켓 V-Ket</b></h1>

# 📌 [V-Ket 접속하기](https://k5a404.p.ssafy.io)  

### 📢 SSAFY 5기 TEAM  404NotFound

|TEAM 404NotFound||
|:------|:------|
|![동엽](https://user-images.githubusercontent.com/56018285/130910983-fed56471-7119-429f-ae19-aadf9e15f9e1.jpg)   |![재우](https://user-images.githubusercontent.com/56018285/130911017-369a7a43-425a-4a95-b78f-fca1b1e7a8b5.jpg) |
|민동엽|심재우|
|Unity, Front-end, Back-end|Unity, Front-end, Back-end|
|[@dymin01](https://github.com/dymin01)|[@jeus1112](https://github.com/jeus1112)|

---
## 📅 Development ( 2021-10-12 ~ 2021-11-19 )
---
## ⚙ Development Environment

작성 날짜: 2021-11-19

CPU: Intel(R) Core(TM) i7-9750H CPU @ 2.60GHz
RAM: 16.0 GB

Ubuntu: 20.04 LTS (GNU/Linux 5.4.0-1018-aws x68_64)
Docker: v20.10.10
Nginx: 1.18.0

Vue: v2.6.14
Vuetify: v2.5.14
Vuex: v3.6.2
Bootstrap: v5.0.0

Java JDK: v1.8.0_302 (Zulu 8.58.0.13-CA-linux64)
Spring: v2.5.6
Mysql: v8.0.27

Unity: v2019.4.32f1
Pun: 2.39
Photon lib: 4.1.6.10

Openvidu: On-premise 2.19.0

---

## ✔ How to run
## [📚Manual](https://lab.ssafy.com/s05-final/S05P31A404/-/blob/master/exec/%ED%8F%AC%ED%8C%85%EB%A7%A4%EB%89%B4%EC%96%BC.md) 
### Port check!
> frontend: 8080  
> backend: 8877  
> openvidu: 8011  
> photon: TCP(4520) | UDP(5000번대 or 27000번대)

### Git clone

```sh
$ git clone https://lab.ssafy.com/s05-final/S05P31A404.git
$ sudo service nginx start
$ java -jar S05P31A404/deploy/backend-0.0.1-SNAPSHOT.jar
```

--- 

## 🛠 Development && Deploy Flow
![v-ket 아키텍쳐](https://user-images.githubusercontent.com/56018285/142554437-fd6d7c9c-5f5c-423c-bd4f-5fc06810dd1f.PNG)


--- 
## 📋 Project Materials

| 구분         |                                                         |
| ------------ | ------------------------------------------------------------ |
|Meeting Log|[Daily Scrum Meetings](https://lab.ssafy.com/s05-final/S05P31A404/-/blob/master/docs/%EB%8D%B0%EC%9D%BC%EB%A6%AC%EC%8A%A4%ED%81%AC%EB%9F%BC.pdf)|
|와이어프레임|[Figma](https://lab.ssafy.com/s05-final/S05P31A404/-/blob/master/docs/%EC%99%80%EC%9D%B4%EC%96%B4%ED%94%84%EB%A0%88%EC%9E%84.pdf)|
|ERD|[ERDcloud](https://lab.ssafy.com/s05-final/S05P31A404/-/blob/master/docs/ERD.PNG)|
|Convention|[Convention](https://lab.ssafy.com/s05-final/S05P31A404/-/blob/master/docs/Convention.md)|
|API Docs|[API Docs](https://lab.ssafy.com/s05-final/S05P31A404/-/blob/master/docs/API_List.pdf)|
|JIRA|[JIRA](https://lab.ssafy.com/s05-final/S05P31A404/-/blob/master/docs/JIRA.pdf)|
