#!/bin/bash

# 기본 경로
BASE_DIR="src/main/java/me/hwjoo/backend"

# 생성할 디렉토리 목록
# mkdir -p "$BASE_DIR/flashDeal/controller"
# mkdir -p "$BASE_DIR/flashDeal/service"
# mkdir -p "$BASE_DIR/flashDeal/repository"
# mkdir -p "$BASE_DIR/flashDeal/entity"
# mkdir -p "$BASE_DIR/flashDeal/dto"
# mkdir -p "$BASE_DIR/common/controller"
# mkdir -p "$BASE_DIR/common/service"
# mkdir -p "$BASE_DIR/common/repository"
# mkdir -p "$BASE_DIR/common/entity"
# mkdir -p "$BASE_DIR/common/dto"

# mkdir -p "$BASE_DIR/raffle/controller"
# mkdir -p "$BASE_DIR/raffle/service"
# mkdir -p "$BASE_DIR/raffle/repository"
# mkdir -p "$BASE_DIR/raffle/entity"
# mkdir -p "$BASE_DIR/raffle/dto"

touch "$BASE_DIR/raffle/controller/RaffleController.java"
touch "$BASE_DIR/raffle/service/RaffleService.java"
touch "$BASE_DIR/raffle/repository/RaffleRepository.java"
touch "$BASE_DIR/raffle/entity/RaffleEvent.java"

echo "패키지 디렉토리 생성 완료!"
