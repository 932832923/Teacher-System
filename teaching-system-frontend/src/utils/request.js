
import axios from 'axios'

const request = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 5000
})

// 请求拦截器
request.interceptors.request.use(config => {
  // 这里可以添加token
  return config
})

// 响应拦截器
request.interceptors.response.use(res => {
  return res.data
}, err => {
  alert(err.response.data.message || '服务器错误')
  return Promise.reject(err)
})

export default request
