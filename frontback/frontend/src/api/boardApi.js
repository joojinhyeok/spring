import api from "@/api";

/*
- 게시판 관련 API 통신을 담당하는 모듈
-  
 */

const BASE_URL = "/api/board";
const headers = { "Content-Type": "multipart/form-data" };

export default {
  // 게시판 조회
  async getList(params) {
    const { data } = await api.get(BASE_URL, { params });

    console.log("BOARD GET LIST: ", data);

    return data;
  },

  // 글 작성
  async create(article) {
    const formData = new FormData();
    formData.append("title", article.title);
    formData.append("writer", article.writer);
    formData.append("content", article.content);
    if (article.files) {
      for (let i = 0; i < article.files.length; i++) {
        formData.append("files", article.files[i]);
      }
    }

    const { data } = await api.post(BASE_URL, formData, { headers });
    console.log("BOARD POST: ", data);
    return data;
  },

  // 게시글 상세조회(pk 홀용)
  async get(no) {
    const { data } = await api.get(`${BASE_URL}/${no}`);
    console.log("BOARD GET", data);
    return data;
  },

  // 게시글 삭제(pk 활용)
  async delete(no) {
    const { data } = await api.delete(`${BASE_URL}/${no}`);
    console.log("BOARD DELETE: ", data);
    return data;
  },
};
