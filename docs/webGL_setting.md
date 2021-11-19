## Unity 2019.4.32f1 버전에서 실행했습니다.  
### 2020 버전에서는 webGL build 관련 이슈가 있으니 반드시 체크하세요!

**File > Build Settings**에 들어갑니다.

![image](https://user-images.githubusercontent.com/77473524/138789374-ca58d7b7-46fe-4291-bfd8-ae4000f7f04b.png)

`Add Open Scenes`를 통해 빌드하려는 Scene들을 추가합니다. 현재 지정된 Platform은 유니티 로고 표시가 되어있습니다. 현재는 `PC, Mac & Linux Standalone`이니 `WebGL`을 선택하고 `Switch Platform`을 누릅니다.

![image](https://user-images.githubusercontent.com/77473524/138789735-3adb20b5-72d9-44df-9888-137810cd79fa.png)

`WebGL`로 Platform이 바뀌면 `Development Build`를 체크하고 `Build`를 눌러줍니다.

![image](https://user-images.githubusercontent.com/77473524/138789919-fe523944-2a35-48e9-9732-492f18e3068c.png)

빌드가 끝나면 위와 같이 `Build`와 `TemplateData`, `index.html`이 생깁니다.



### 2. Vue project 적용

![image](https://user-images.githubusercontent.com/77473524/138790322-cff6d08f-8a9f-4612-83e5-7a08336873ae.png)

```
$ npm install https://github.com/votetake/vue-unity-webgl.git --save
```

Vue project를 통해 웹화면에 Unity를 제공하기 위해서는 외부 라이브러리가 필요합니다. 위와 같이 설치를 합니다.

![image](https://user-images.githubusercontent.com/77473524/138790518-170b8c3b-d0af-45f5-ba5a-777b9b693279.png)

Vue project에 `public`에 빌드된 파일들을 옮겨 줍니다.

![image](https://user-images.githubusercontent.com/77473524/138791479-507f54e8-49fd-44b1-be9d-06962d481ff1.png)

```html
<template>
  <unity src="./unity/Build/Build.json" width="1920" height="1080" unityLoader="./unity/Build/UnityLoader.js" ref="hookInstance"></unity>  
</template>

<script>
  import Unity from 'vue-unity-webgl';
  
  export default {
		name : "UnityGame",
		components : {Unity}
	}
</script>
```

`vue-unity-webgl` 라이브러리를 import하면 위의 코드처럼 컴포넌트화 하여 사용가능합니다.

### 3. 결과

![image](https://user-images.githubusercontent.com/77473524/138791783-4d1f44d1-9773-4eec-8738-2a294f9a9e64.png)

Vue project를 실행하면 위와 같이 Unity 게임 화면이 웹화면으로 제공되는 것을 볼 수 있습니다.



#### Reference

- vue-unity-webgl github

  https://github.com/votetake/vue-unity-webgl

- npm

  https://www.npmjs.com/package/vue-unity-webgl


