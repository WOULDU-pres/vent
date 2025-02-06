// src/api/instance.ts
import axios from "axios";

// Docker Dev
// const baseURL = import.meta.env.VITE_API_BASE_URL || "/api";

// local Dev
const baseURL = "http://localhost:8080/api";

// 공통 요청 헤더 설정 (더미 사용자 UUID)
const dummyUserUuid = localStorage.getItem("dummyUuid") || crypto.randomUUID();
localStorage.setItem("dummyUuid", dummyUserUuid);

export const apiClient = axios.create({
  baseURL,
  headers: {
    "X-User-UUID": dummyUserUuid,
  },
});
