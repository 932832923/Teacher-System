
import request from '../utils/request'

export function getCourseList(keyword = '') {
  return request.get('/course/list', { params: { keyword } })
}

export function addCourse(data) {
  return request.post('/course/add', data)
}

export function updateCourse(data) {
  return request.put('/course/update', data)
}

export function deleteCourse(id) {
  return request.delete(`/course/delete/${id}`)
}
