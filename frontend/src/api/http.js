import axios from 'axios'

const http = axios.create({
  baseURL: '/api',
})

http.interceptors.response.use(
  (response) => response,
  (error) => Promise.reject(new Error(error.response?.data?.message ?? '連線失敗')),
)

export default http
