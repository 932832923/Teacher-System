
import { createRouter, createWebHistory } from 'vue-router'
import ClassList from '../views/class/ClassList.vue'

const routes = [
  { path: '/', redirect: '/class' },
  { path: '/class', component: ClassList }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router

routes.push({ path: '/course', component: () => import('../views/class/CourseList.vue') })

routes.push({ path: '/assignment', component: () => import('../views/class/AssignmentList.vue') })

routes.push({ path: '/submission', component: () => import('../views/class/SubmissionList.vue') })

routes.push({ path: '/statistics', component: () => import('../views/class/StatisticsChart.vue') })

routes.push({ path: '/upload', component: () => import('../views/class/Upload.vue') })
