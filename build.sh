#!/bin/bash

# 에러 발생 시 즉시 종료
set -e

echo "🔨 1. 기존 컨테이너 정리..."
docker compose down

echo "🚀 2. 백엔드 빌드 시작..."
# cd backend && ./gradlew clean build && cd ..
# cd backend && ./gradlew build && cd ..

echo "🛠️ 3. 도커 컨테이너 빌드 및 실행..."
docker compose up --build -d

echo "✅ 4. 컨테이너 상태 확인..."
# docker container ls --filter "name=vent" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
docker container ls -a

# 에러 처리 해제
set +e
