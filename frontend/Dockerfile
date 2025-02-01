# frontend/Dockerfile
FROM node:18-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
# [수정사항] 빌드 결과물을 확인하기 위해 임시로 dist 디렉토리 목록을 출력
RUN ls -la /app/dist


FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
