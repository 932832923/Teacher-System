
<template>
  <div class="submission-list">
    <el-card>
      <div style="margin-bottom: 16px;">
        <el-select v-model="selectedAssignmentId" placeholder="请选择实验" style="width: 300px;" @change="loadData">
          <el-option v-for="a in assignmentList" :key="a.id" :label="a.title" :value="a.id" />
        </el-select>
      </div>

      <el-table :data="submissionList" v-if="selectedAssignmentId">
        <el-table-column prop="studentId" label="学生ID" width="120" />
        <el-table-column prop="fileUrl" label="文件地址" />
        <el-table-column prop="status" label="状态" width="100" />
        <el-table-column prop="score" label="分数" width="80" />
        <el-table-column prop="comment" label="评语" />
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button size="small" @click="openEditDialog(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog :title="'编辑提交记录'" v-model="dialogVisible" width="500px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="状态">
          <el-select v-model="form.status">
            <el-option label="未提交" value="未提交" />
            <el-option label="已提交" value="已提交" />
            <el-option label="已批改" value="已批改" />
          </el-select>
        </el-form-item>
        <el-form-item label="文件地址">
          <el-input v-model="form.fileUrl" />
        </el-form-item>
        <el-form-item label="分数">
          <el-input-number v-model="form.score" :min="0" :max="100" />
        </el-form-item>
        <el-form-item label="评语">
          <el-input type="textarea" v-model="form.comment" />
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
import { getAssignmentList } from '@/api/assignment'
import { getSubmissionList, updateSubmission, deleteSubmission } from '@/api/submission'

const assignmentList = ref([])
const submissionList = ref([])
const selectedAssignmentId = ref(null)
const dialogVisible = ref(false)
const form = ref({})

function loadAssignments() {
  // 可以默认加载课程ID为1的实验列表或后续动态传入
  getAssignmentList(1).then(res => {
    assignmentList.value = res.data
  })
}

function loadData() {
  if (!selectedAssignmentId.value) return
  getSubmissionList(selectedAssignmentId.value).then(res => {
    submissionList.value = res.data
  })
}

function openEditDialog(row) {
  form.value = { ...row }
  dialogVisible.value = true
}

function handleSave() {
  updateSubmission(form.value).then(() => {
    dialogVisible.value = false
    loadData()
  })
}

function handleDelete(id) {
  if (confirm('确认删除该记录？')) {
    deleteSubmission(id).then(() => {
      loadData()
    })
  }
}

onMounted(() => {
  loadAssignments()
})
</script>

<style scoped>
.submission-list {
  padding: 20px;
}
</style>
