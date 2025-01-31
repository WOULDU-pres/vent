#!/bin/bash

# 기본 경로
BASE_DIR="src/main/java/me/hwjoo/backend"

# 생성할 디렉토리 목록
mkdir -p "$BASE_DIR/flash-deal/controller"
mkdir -p "$BASE_DIR/flash-deal/service"
mkdir -p "$BASE_DIR/flash-deal/repository"
mkdir -p "$BASE_DIR/flash-deal/entity"
mkdir -p "$BASE_DIR/flash-deal/dto"
mkdir -p "$BASE_DIR/raffle/controller"
mkdir -p "$BASE_DIR/raffle/service"
mkdir -p "$BASE_DIR/raffle/repository"
mkdir -p "$BASE_DIR/raffle/entity"
mkdir -p "$BASE_DIR/raffle/dto"
mkdir -p "$BASE_DIR/common/controller"
mkdir -p "$BASE_DIR/common/service"
mkdir -p "$BASE_DIR/common/repository"
mkdir -p "$BASE_DIR/common/entity"
mkdir -p "$BASE_DIR/common/dto"


echo "패키지 디렉토리 생성 완료!"
