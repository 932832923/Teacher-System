
<template>
  <div class="statistics-chart">
    <el-card>
      <div style="margin-bottom: 16px;">
        <el-select v-model="selectedCourseId" placeholder="请选择课程" style="width: 300px;" @change="loadChartData">
          <el-option v-for="course in courseList" :key="course.id" :label="course.courseName" :value="course.id" />
        </el-select>
      </div>
      <div ref="chartRef" style="height: 400px;"></div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue'
import * as echarts from 'echarts'
import { getCourseList } from '@/api/course'
import { getSubmissionStats } from '@/api/statistics'

const chartRef = ref(null)
const chartInstance = ref(null)
const selectedCourseId = ref(null)
const courseList = ref([])

function loadCourseList() {
  getCourseList().then(res => {
    courseList.value = res.data
  })
}

function loadChartData() {
  if (!selectedCourseId.value) return
  getSubmissionStats(selectedCourseId.value).then(res => {
    const data = res.data
    const xData = data.map(item => item.experiment)
    const yData = data.map(item => item.rate)

    nextTick(() => {
      if (!chartInstance.value) {
        chartInstance.value = echarts.init(chartRef.value)
      }
      const option = {
        title: {
          text: '实验提交率统计',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: xData
        },
        yAxis: {
          type: 'value',
          axisLabel: { formatter: '{value}%' }
        },
        series: [{
          data: yData,
          type: 'line',
          smooth: true
        }]
      }
      chartInstance.value.setOption(option)
    })
  })
}

onMounted(() => {
  loadCourseList()
})
</script>

<style scoped>
.statistics-chart {
  padding: 20px;
}
</style>
