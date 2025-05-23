
import request from '../utils/request'

export function getSubmissionList(assignmentId) {
  return request.get(`/submission/list/${assignmentId}`)
}

export function updateSubmission(data) {
  return request.put('/submission/update', data)
}

export function deleteSubmission(id) {
  return request.delete(`/submission/delete/${id}`)
}
