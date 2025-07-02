import axios from "axios";
import { useAuthStore } from "@/stores/auth";
import router from "@/router";

// axios 객체 생성
// --> 프로젝트 전체에서 사용할 객체
// --> request/response 인터셉터 설정이된 axios 객체

const instance = axios.create({
  timeout: 1000,
});

// request 인터셉터 설정 --> JWT를 header에 포함
instance.interceptors.request.use(
  (config) => {
    // JWT를 가지고 와야함.
    const { getToken } = useAuthStore();
    const token = getToken();

    // config 객체를 이용해서 http의 header에 넣어줌
    if (token) {
      // 토큰이 있는 경우 header에 포함시킴
      config.headers["Authorization"] = `Bearer ${token}`;
      console.log("전송될 jwt >> " + config.headers.Authorization);
    }
    return config;
  },
  (error) => {
    // 요청중 에러가 난 경우
    return Promise.reject(error);
  }
);
// 응답 인터셉터
instance.interceptors.response.use(
  (response) => {
    // 정상 응답인 경우 (200, 404)

    return response;
  },
  async (error) => {
    // 에러 응답인 경우(401, 403, 305, 500 등)

    return Promise.reject(error);
  }
);

export default instance; // 인터셉터가 적용된 axios 인스턴스

// response 인터셉터 설정 --> 응답처리
