<script setup>
import api from "@/api/boardApi";
import { ref, reactive, computed } from "vue";
import moment from "moment";
import { useRoute, useRouter } from "vue-router";
import { useAuthStore } from "@/stores/auth";

const cr = useRoute();
const router = useRouter();

const auth = useAuthStore();
const islogin = auth.isLogin;

const page = ref({}); // ref() 반응형 데이터

const articles = computed(() => page.value); // computed(): 반응형 값을 읽어서 계산된 값 제공(자동 갱신됨)

const load = async () => {
  try {
    page.value = await api.getList(); // 2. load()가 호출되면서 page변수에 api실행결과를 넣음
    console.log(page.value);
  } catch {}
};

load(); // 1. 컴포넌트가 load되면 load()를 호출
</script>
<template>
  <div>
    <h1 class="mb-3"><i class="fa-solid fa-paste"></i> 게시글 목록</h1>

    <table class="table table-striped">
      <thead>
        <tr>
          <th style="width: 60px">No</th>
          <th>제목</th>
          <th style="width: 100px">작성자</th>
          <th style="width: 120px">작성일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="article in articles" :key="article.no">
          <td>{{ article.no }}</td>
          <td>
            <router-link
              :to="{ name: 'board/detail', params: { no: article.no } }"
            >
              {{ article.title }}
            </router-link>
          </td>
          <td>{{ article.writer }}</td>
          <td>{{ moment(article.regDate).format("YYYY-MM-DD") }}</td>
        </tr>
      </tbody>
    </table>
    <div class="my-5 d-flex">
      <div class="flex-grow-1 text-center">페이지 네이션</div>
      <div>
        <template v-if="islogin">
          <router-link :to="{ name: 'board/create' }" class="btn btn-primary">
            <i class="fa-solid fa-pen-to-square"></i> 글 작성</router-link
          >
        </template>
      </div>
    </div>
  </div>
</template>
