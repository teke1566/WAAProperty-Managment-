import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api/v1/',
});

axiosInstance.interceptors.request.use(
  function (config) {
    const accessToken = getCookie('access_token');

    config.headers.Authorization = `Bearer ${accessToken}`;

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