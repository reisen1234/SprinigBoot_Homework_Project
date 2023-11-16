import axios from "axios";
const axiosInstance = axios.create({
  baseURL: "http://127.0.0.1:8899/book", // 设置您的 API 基础URL
  timeout: 5000, // 设置请求超时时
});
const bookApi = {
  getBookByName(data) {
    const headers = { "Content-Type": "application/json" };
    return axiosInstance.get(
      "/page/username",
      {
        params: {
          username: data,
        },
      },
      { headers }
    );
  },
  deleteBookById(Id) {
    console.log(Id);
    const headers = { "Content-Type": "application/json" };
    return axiosInstance.delete(`/id/${Id}`, { headers });
  },
  updatedBook(data) {
    const headers = { "Content-Type": "application/json" };
    return axiosInstance.put(``, data, { headers });
  },
};

export default bookApi;
