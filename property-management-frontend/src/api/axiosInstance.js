import axios from 'axios';
import Cookies from 'universal-cookie';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api/v1/',
});

const cookies = new Cookies();

axiosInstance.interceptors.request.use(
  function (config) {

    const token = cookies.get('accessToken');

    if (token && !config.headers.Authorization) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  function (error) {
    console.error(error);
    return Promise.reject(error);
  }
);

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
  }

export default axiosInstance;