# frBookCloneCoding


### SpringBoot 프로젝트
* 사용했던 기술: Lombok, JPA, JPA Auditing, Spring Security & OAuth2, 구글/네이버 로그인 연동

### DB 설치
* 작업 환경 : VM환경에 설치
* MariaDB 버전 : 10.8.4

### git branch 전략을 적용한 소스 관리
* 항상 master 권한으로만 github를 사용하였는데 권한을 분배하여 깃허브를 사용해보고자 한다.
* 개인프로젝트이니까 master와 developer 브랜치만 두고 테스를 하도록 한다.
* PR 규칙을 정하여 리뷰를 통한 PR이 가능하도록 하였다.

### Ngnix
* 리버스 프록시 역할을 하여  외부의 요청을 받아 백앤드 서버로 요청을 전달하도록 설정한다.
