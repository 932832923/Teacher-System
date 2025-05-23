
<template>
  <div class="assignment-list">
    <el-card>
      <div style="margin-bottom: 16px; display: flex; justify-content: space-between;">
        <el-select v-model="selectedCourseId" placeholder="请选择课程" style="width: 300px;" @change="loadData">
          <el-option v-for="course in courseList" :key="course.id" :label="course.courseName" :value="course.id" />
        </el-select>
        <el-button type="primary" @click="openAddDialog" :disabled="!selectedCourseId">新增实验</el-button>
      </div>

      <el-table :data="assignmentList" v-if="selectedCourseId">
        <el-table-column prop="experimentNumber" label="实验编号" width="120" />
        <el-table-column prop="title" label="实验标题" />
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
    <el-dialog :title="form.id ? '编辑实验' : '新增实验'" v-model="dialogVisible" width="500px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="实验编号">
          <el-input-number v-model="form.experimentNumber" :min="1" />
        </el-form-item>
        <el-form-item label="实验标题">
          <el-input v-model="form.title" />
        </el-form-item>
        <el-form-item label="截止时间">
          <el-date-picker v-model="form.deadline" type="datetime" style="width: 100%" />
        </el-form-item>
        <el-form-item label="实验说明">
          <el-input type="textarea" v-model="form.description" rows="4" />
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
import { getAssignmentList, addAssignment, updateAssignment, deleteAssignment } from '@/api/assignment'
import { getCourseList } from '@/api/course'

const courseList = ref([])
const selectedCourseId = ref(null)
const assignmentList = ref([])
const dialogVisible = ref(false)
const form = ref({ id: null, courseId: null, experimentNumber: 1, title: '', deadline: '', description: '' })

function loadCourses() {
  getCourseList().then(res => {
    courseList.value = res.data
  })
}

function loadData() {
  if (!selectedCourseId.value) return
  getAssignmentList(selectedCourseId.value).then(res => {
    assignmentList.value = res.data
  })
}

function openAddDialog() {
  form.value = { id: null, courseId: selectedCourseId.value, experimentNumber: 1, title: '', deadline: '', description: '' }
  dialogVisible.value = true
}

function openEditDialog(row) {
  form.value = { ...row, courseId: selectedCourseId.value }
  dialogVisible.value = true
}

function handleSave() {
  const action = form.value.id ? updateAssignment : addAssignment
  action(form.value).then(() => {
    dialogVisible.value = false
    loadData()
  })
}

function handleDelete(id) {
  if (confirm('确认删除该实验？')) {
    deleteAssignment(id).then(() => {
      loadData()
    })
  }
}

onMounted(() => {
  loadCourses()
})
</script>

<style scoped>
.assignment-list {
  padding: 20px;
}
</style>
