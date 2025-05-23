
import request from '../utils/request'

export function getAssignmentList(courseId) {
  return request.get(`/assignment/list/${courseId}`)
}

export function addAssignment(data) {
  return request.post('/assignment/add', data)
}

export function updateAssignment(data) {
  return request.put('/assignment/update', data)
}

export function deleteAssignment(id) {
  return request.delete(`/assignment/delete/${id}`)
}
