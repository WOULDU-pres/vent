# backend/Dockerfile

# 멀티 스테이지 빌드 적용 (빌드 환경 분리)
FROM eclipse-temurin:21-jdk-jammy as builder
WORKDIR /workspace

# Gradle Wrapper 및 필요한 파일 복사 (캐싱 최적화)
COPY gradle /workspace/gradle
COPY gradlew /workspace/gradlew
COPY build.gradle /workspace/build.gradle
COPY settings.gradle /workspace/settings.gradle

# Gradle Wrapper 실행 권한 부여 및 종속성 캐시 다운로드 (캐싱 최적화)
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

# 소스 코드 복사 및 빌드 실행 (테스트 제외)
COPY . .
RUN ./gradlew build -x test
RUN #./gradlew clean build -x test

# 최종 이미지 생성 단계
FROM eclipse-temurin:21-jdk-jammy

# 임시 디렉토리 설정 및 JAR 파일 복사
VOLUME /tmp
ARG JAR_FILE=build/libs/backend-*.jar
COPY --from=builder /workspace/${JAR_FILE} app.jar

# Spring Boot 애플리케이션 실행 명령어 (도커 프로파일 활성화)
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar","/app.jar"]
