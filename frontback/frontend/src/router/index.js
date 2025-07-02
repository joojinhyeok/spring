import { createRouter, createWebHistory } from "vue-router";
import HomePage from "../pages/HomePage.vue";
import authRouters from "./auth";
import boardRouters from "./board";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomePage,
    },
    ...authRouters, // 로그인/회원가입 관련 라우터
    ...boardRouters, // 게시판 관련 라우터
  ],
});
export default router;
