import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://na2ru2.me:6320/",
});

export default axiosInstance;
