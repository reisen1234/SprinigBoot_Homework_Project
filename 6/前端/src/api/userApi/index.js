import axios from "axios";
const axiosInstance = axios.create({
  baseURL: "http://127.0.0.1:8899/user", // 设置您的 API 基础URL
  timeout: 5000, // 设置请求超时时
});
const userApi = {
  register(data) {
    return axiosInstance.post("/register", data);
  },

  login(data) {
    return axiosInstance.post("/login", data);
  },
  checkLogin(data) {
    const headers = { "Content-Type": "application/json" };
    return axiosInstance.post("/checkLogin", data ,{headers});
  },
};

export default userApi;
