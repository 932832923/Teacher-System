
<template>
  <div class="course-list">
    <el-card>
      <div style="margin-bottom: 16px; display: flex; justify-content: space-between;">
        <el-input v-model="keyword" placeholder="搜索课程或教师" style="width: 300px;" clearable @clear="loadData" @keyup.enter="loadData">
          <template #append>
            <el-button icon="el-icon-search" @click="loadData" />
          </template>
        </el-input>
        <el-button type="primary" @click="openAddDialog">新增课程</el-button>
      </div>

      <el-table :data="courseList" style="width: 100%">
        <el-table-column prop="courseCode" label="课程编号" width="120" />
        <el-table-column prop="courseName" label="课程名称" />
        <el-table-column prop="teacherName" label="任课教师" />
        <el-table-column prop="experimentCount" label="实验数量" width="100" />
        <el-table-column prop="deadline" label="截止时间" />
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button size="small" @click="openEditDialog(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 新增/编辑对话框 -->
    <el-dialog :title="form.id ? '编辑课程' : '新增课程'" v-model="dialogVisible" width="500px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="课程编号">
          <el-input v-model="form.courseCode" :disabled="!!form.id" />
        </el-form-item>
        <el-form-item label="课程名称">
          <el-input v-model="form.courseName" />
        </el-form-item>
        <el-form-item label="任课教师">
          <el-input v-model="form.teacherName" />
        </el-form-item>
        <el-form-item label="实验数量">
          <el-input-number v-model="form.experimentCount" :min="0" />
        </el-form-item>
        <el-form-item label="截止时间">
          <el-date-picker v-model="form.deadline" type="datetime" placeholder="选择截止时间" style="width: 100%;" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSave">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getCourseList, addCourse, updateCourse, deleteCourse } from '@/api/course'

const keyword = ref('')
const courseList = ref([])
const dialogVisible = ref(false)
const form = ref({ id: null, courseCode: '', courseName: '', teacherName: '', experimentCount: 0, deadline: '' })

function loadData() {
  getCourseList(keyword.value).then(res => {
    courseList.value = res.data
  })
}

function openAddDialog() {
  form.value = { id: null, courseCode: '', courseName: '', teacherName: '', experimentCount: 0, deadline: '' }
  dialogVisible.value = true
}

function openEditDialog(row) {
  form.value = { ...row }
  dialogVisible.value = true
}

function handleSave() {
  const action = form.value.id ? updateCourse : addCourse
  action(form.value).then(() => {
    dialogVisible.value = false
    loadData()
  })
}

function handleDelete(id) {
  if (confirm('确认删除该课程？')) {
    deleteCourse(id).then(() => {
      loadData()
    })
  }
}

onMounted(() => {
  loadData()
})
</script>

<style scoped>
.course-list {
  padding: 20px;
}
</style>
