# 👨‍👩‍👦‍👦 Event Platform Backend Server 👨‍👩‍👦‍👦

[![license](https://img.shields.io/badge/License-AGPL-red)](https://github.com)
[![code](https://img.shields.io/badge/Framework-SpringBoot-green)](https://github.com)
[![DBMS](https://img.shields.io/badge/DBMS-PostgreSQL-blue)](https://github.com)
[![frontend](https://img.shields.io/badge/Frontend-React-blue)](https://github.com)

![event_platform_intro_image](https://github.com/your-username/event-platform-backend/assets/your-asset-id/your-image-id)

## 🗂️ Index

1. [**웹 서비스 개요**](#1)
2. [**기술 스택**](#2)
3. [**BE 챌린지 & 해결**](#3)
4. [**프로젝트 구조**](#4)


<div id="1"></div>

## 📖 Outline

**소개**

이벤트 플랫폼 프로젝트는 `다양한 이벤트 관리`를 통해 기업이 고객과 소통하고 효과적으로 마케팅할 수 있는 기회를 제공합니다. 이 플랫폼은 네 가지 주요 기능을 중심으로 설계되었습니다:

1. **럭키드로우 (Raffle)** - 추첨 이벤트를 쉽게 생성하고 관리할 수 있습니다. 기업은 경품을 설정하고, 참여자는 간단한 등록 과정을 통해 추첨에 참여할 수 있습니다. 시스템은 공정한 방식으로 당첨자를 선정하고, 참여자와 관리자 모두에게 결과를 투명하게 보여줍니다.

2. **출석체크 (Attendance Check)** - 이벤트나 세미나에서 참석자 출석을 효율적으로 관리합니다. QR 코드를 통한 빠른 체크인 시스템과 실시간 출석 현황 대시보드를 제공합니다. 관리자는 참석 데이터를 쉽게 내보내고 분석할 수 있습니다.

3. **랜덤 뽑기 (Random Picker)** - 실시간 이벤트에서 참여자 중 무작위로 선택하는 기능을 제공합니다. 공정하고 투명한 랜덤 알고리즘으로 신뢰성 있는 선택 과정을 보장합니다. 애니메이션 효과로 이벤트의 즐거움을 더합니다.

4. **타임어택 (Time-Attack)** - 특정 시간에만 참여할 수 있는 한정 이벤트를 관리합니다. 정확한 타이밍 시스템으로 모든 참여자에게 공정한 기회를 제공하며, 실시간 참여 현황을 모니터링할 수 있습니다.

이 플랫폼은 로그인 시스템 없이 UUID 기반의 일회성 참여 시스템을 채택하여, 사용자가 복잡한 회원가입 과정 없이도 쉽게 이벤트에 참여할 수 있도록 설계되었습니다. 또한, 모든 기능은 모바일 친화적인 반응형 디자인으로 구현되었습니다.

- 온라인 이벤트 운영을 위한 일원화된 시스템으로, 관리자가 손쉽게 이벤트를 생성하고 관리할 수 있습니다.
  <details>
      <summary>메인 화면 보기</summary>
      <img src="https://github.com/user-attachments/assets/your-asset-id" alt="메인_화면" />     
  </details>

**개발 기간**

- MVP : 2024.02.20 ~ 2024.02.28

<div id="2"></div>

## 🔧 기술 스택

### 프론트엔드
- **언어 및 라이브러리**: TypeScript, React 18
- **상태 관리**: Zustand
- **UI 컴포넌트**: Ant Design
- **API 통신**: Axios, React Query
- **스타일링**: CSS Modules
- **빌드 도구**: Vite
- **코드 품질**: ESLint, Prettier
- **테스트**: Jest, React Testing Library

### 백엔드
- **언어 및 프레임워크**: Java 21, Spring Boot 3.4
- **API 문서화**: Spring Doc (OpenAPI/Swagger)
- **데이터베이스**: PostgreSQL
- **ORM**: Spring Data JPA
- **보안**: Spring Security
- **테스트**: JUnit 5, Mockito
- **빌드 도구**: Gradle
- **서버**: Embedded Tomcat

### 인프라 및 도구
- **버전 관리**: Git, GitHub
- **CI/CD**: GitHub Actions
- **배포**: Docker
- **모니터링**: Spring Actuator

<div id="3"></div>

## 🔥 BE Challenge & Solution

1) 럭키드로우 이벤트 동시 참여 시 Race Condition 발생
- 해결 : 
- 트러블 슈팅 링크 : [해당 링크](https://your-blog-url)

2) 출석체크 QR 코드 보안 취약점 발견
- 해결 : 
- 트러블 슈팅 링크 : [해당 링크](https://your-blog-url)

3) 랜덤 뽑기 이벤트 공정성 문제
- 해결 : 
- 트러블 슈팅 링크 : [해당 링크](https://your-blog-url)

4) 플래시딜 이벤트 타이밍 오차 문제
- 해결 : 
- 트러블 슈팅 링크 : [해당 링크](https://your-blog-url)

5) 대용량 트래픽 처리 성능 문제
- 해결 : 
- 트러블 슈팅 링크 : [해당 링크](https://your-blog-url)

6) 이미지 업로드 및 처리 성능 이슈
- 해결 : 
- 트러블 슈팅 링크 : [해당 링크](https://your-blog-url)

7) JavaScript와 Java 간 날짜/시간 변환 불일치
- 해결 : 
- 트러블 슈팅 링크 : [해당 링크](https://your-blog-url)

8) 이벤트 상태 자동 변경 스케줄링 문제
- 해결 : 
- 트러블 슈팅 링크 : [해당 링크](https://your-blog-url)

<div id="4"></div>
  
## 📂 Project Structure

### 전체 프로젝트 구조

```
projects/ 
├── event-platform-frontend/ # 통합 프론트엔드 
├── event-platform-common/ # 공통 백엔드 모듈 
├── raffle/ # 백엔드 MVP 1 
├── attendance-check/ # 백엔드 MVP 2 
├── random-picker/ # 백엔드 MVP 3 
├── time-attack/ # 백엔드 MVP 4
```

### 백엔드 코어 디렉토리 구조 (각 MVP별)

```
src/main/java/me/hwjoo/[feature]/
├── controller/ # REST API 컨트롤러
├── service/ # 비즈니스 로직
├── repository/ # 데이터 접근 계층
├── dto/ # 데이터 전송 객체
├── entity/ # JPA 엔티티
└── exception/ # 예외 처리
```

### 프론트엔드 디렉토리 구조

```
src/
├── common/ # 공통 컴포넌트 및 유틸리티
└── features/ # 기능별 모듈화
    ├── raffle/ # 럭키드로우 관련 컴포넌트
    ├── attendance-check/ # 출석체크 관련 컴포넌트 
    ├── random-picker/ # 랜덤 뽑기 관련 컴포넌트
    └── time-attack/ # 타임어택 관련 컴포넌트
```

## ETC

- *Initial work, Read me* - [WOULDU-pres](https://github.com/WOULDU-pres)
- *License* - This project is licensed under the AGPL License - see the [LICENSE.md](https://github.com/WOULDU-pres/vent/blob/main/LICENSE.md) file for details
