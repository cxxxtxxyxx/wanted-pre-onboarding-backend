# 원티드 프리온보딩 백엔드 인턴십 - 선발 과제

### 지원자 : 최태윤

<br>

## 환경 세팅

### 버전

- Java : 11
- SpringBoot : 2.7.14
- MySQL : 8.0

### Database

- MySQL을 사용하였습니다.

### 설정 파일

- application-secret.yml 파일은 업로드를 하지 않고 EC2 환경 내에 직접 파일을 작성하였습니다.

### 서버

- AWS의 EC2를 사용하여 배포하였습니다.
- 서버 접속용 공인 IP는 “15.165.142.153:8080” 입니다.

## 구현 공통 사항

- JWT 토큰을 사용하여 인증을 구현하였습니다.
- 공통 응답 객체를 만들어 일관된 데이터 포맷을 가지도록 하였습니다. (APIDataResponse, APIErrorResponse)
- 전역 에러 핸들러를 두어 한 곳에서 모든 예외를 처리하도록 하였습니다.
- 도메인 객체 내에 비즈니스 로직을 두지 않고 service단에서 처리하도록 하는 트랜잭션 스크립트 패턴을 사용하였습니다.

<br>

## 데이터베이스 테이블 구조

![image](https://github.com/lordmyshepherd-edu/wanted-pre-onboardung-backend-selection-assignment/assets/109710879/70883467-803f-402b-9dd4-502ca4fd42f7)

<br>

## API 명세서(request/response 포함)

### PostMan API 명세서

- [Postman API 명세서](https://documenter.getpostman.com/view/21873803/2s9Xy6rqXp)

<br>

## 구현한 API의 동작을 촬영한 데모 영상 링크
