
import request from '../utils/request'

export function getClassList(keyword = '') {
  return request.get('/class/list', { params: { keyword } })
}

export function addClass(data) {
  return request.post('/class/add', data)
}

export function updateClass(data) {
  return request.put('/class/update', data)
}

export function deleteClass(id) {
  return request.delete(`/class/delete/${id}`)
}
