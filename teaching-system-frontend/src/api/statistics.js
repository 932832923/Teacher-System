
import request from '../utils/request'

export function getSubmissionStats(courseId) {
  return request.get(`/statistics/submission-rate/${courseId}`)
}
