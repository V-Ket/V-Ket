


# Convention

## 1. Git Convention

### 1) Git Branch Convention

```powershell
master
	- release
	- develop (주1회, 월요일 전에 머지)
	    - feature
	        - BE, FE, Unity _ 브랜치명 _ #지라이슈번호
	    - feature/BE_Login#지라이슈번호
	    - feature/FE_Login_CSS#지라이슈번호
	    - feature/Unity_Map#지라이슈번호
```

### 2) Git Commit Message Prefix

```shell
types = {      
	feat: 새로운 기능에 대한 커밋      
	fix: 버그 수정에 대한 커밋      
	build: 빌드 관련 파일 수정에 대한 커밋      
	chore: 그 외 자잘한 수정에 대한 커밋      
	ci: CI관련 설정 수정에 대한 커밋      
	docs: 문서 수정에 대한 커밋      
	style: 코드 스타일 혹은 포맷 등에 관한 커밋      
	refactor:  코드 리팩토링에 대한 커밋      
	test: 테스트 코드 수정에 대한 커밋   
}
```

### 3) Git Commit Message Convention

1. Commit Message 구조

   - \#지라이슈번호  ${Types} : "커밋 메시지"
   - "커밋 메시지" : "${커밋 시작 동사(소문자)} + ${[BE], [FE], [Unity] 말머리} + 내용"
   - \#{지라이슈번호(어떤거 작업 했는지} Feat: add [BE] login controller
   - \#{지라이슈번호} Fix: fix [BE] login controller
   - 예시 : #S05P12A308-105 Feat: add [BE] login controller

2. Commit Message 규칙

   ```shell
   1. 제목은 동사 원형으로 시작
   2. 총 글자 수는 50자 이내로 작성
   3. 마지막에 특수문자는 삽입하지 않는다(마침표, 느낌표, 물음표 등)
   4. 영어로 작성하며 첫 글자는 소문자로 작성한다.
   ```

3. Commit Message 시작 동사

   ```shell
   FIX -> 올바르지 않은 동작을 고친 경우에 사용합니다.
   ADD -> 코드나 테스트, 예제, 문서 등의 추가 생성이 있을 때 사용합니다
   REMOVE -> 코드의 삭제가 있을 때 사용합니다. ‘Clean’이나 ‘Eliminate’를 사용하기도 합니다.
   IMPROVE -> 향상이 있을 때 사용합니다. 호환성, 테스트 커버리지, 성능, 검증 기능, 접근성 등 다양한 것들이 목적이 될 수 있습니다.
   IMPLEMENT -> 코드가 추가된 정도보다 더 주목할 만한 구현체를 완성시켰을 때 사용합니다.  
   REFACTOR -> 코드 전면 수정이 있을 때 사용합니다.
   MOVE -> 코드의 이동이 있을 때 사용합니다.
   RENAME -> 이름 변경이 있을 때 사용합니다.  
   UPDATE -> 개정이나 버전 업데이트가 있을 때 사용합니다. 주로 코드보다는 주로 문서나 리소스, 라이브러리등에 사용합니다.
   ```

## 2. Jira Convention

- Sprint 단위 : 매주 월 AM 9:00 ~ 금 PM 6:00

- Sprint 이름 : N주차

- 🔮epic

  >  큰 단위의 업무

  - 기준: 개발 프로세스 단위 - 기획 / 설계 / 구현 / 테스트 or 개발 기능 단위

- 📗user story

  > 최종 고객에게 가치를 제공하는 기능

  - sprint 내에 완료 가능한 단위로 분할 필요
  - story point 설정 - 1 = 1 HR (소요시간 예측)
  - 말머리 : [BE], [FE], [Unity]

- 🛠 task

  > story 외에 기술적, 관리적 업무

  - 설계, 서버설치, 문서작업, 발표준비 등

- ☑ sub-task

  > user story와 task 내에 속하는 작은 단위의 업무

  - 부작업은 story point 입력 불가능하므로 1시간이 걸리지 않는 업무 단위로 쪼개면 좋을듯

- 🐝 bug

  > 개발 과정 중 보고된 버그
