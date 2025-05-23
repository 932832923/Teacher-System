
<template>
  <div class="class-list">
    <el-card>
      <div style="margin-bottom: 16px; display: flex; justify-content: space-between;">
        <el-input v-model="keyword" placeholder="搜索班级或专业" style="width: 300px;" clearable @clear="loadData" @keyup.enter="loadData">
          <template #append>
            <el-button icon="el-icon-search" @click="loadData" />
          </template>
        </el-input>
        <el-button type="primary" @click="openAddDialog">新增班级</el-button>
      </div>

      <el-table :data="classList" style="width: 100%">
        <el-table-column prop="classCode" label="班级编号" width="120" />
        <el-table-column prop="className" label="班级名称" />
        <el-table-column prop="major" label="专业" />
        <el-table-column prop="grade" label="年级" />
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button size="small" @click="openEditDialog(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 新增/编辑对话框 -->
    <el-dialog :title="form.id ? '编辑班级' : '新增班级'" v-model="dialogVisible" width="500px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="班级编号">
          <el-input v-model="form.classCode" :disabled="!!form.id" />
        </el-form-item>
        <el-form-item label="班级名称">
          <el-input v-model="form.className" />
        </el-form-item>
        <el-form-item label="专业">
          <el-input v-model="form.major" />
        </el-form-item>
        <el-form-item label="年级">
          <el-input v-model="form.grade" />
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
import { getClassList, addClass, updateClass, deleteClass } from '@/api/class'

const keyword = ref('')
const classList = ref([])
const dialogVisible = ref(false)
const form = ref({ id: null, classCode: '', className: '', major: '', grade: '' })

function loadData() {
  getClassList(keyword.value).then(res => {
    classList.value = res.data
  })
}

function openAddDialog() {
  form.value = { id: null, classCode: '', className: '', major: '', grade: '' }
  dialogVisible.value = true
}

function openEditDialog(row) {
  form.value = { ...row }
  dialogVisible.value = true
}

function handleSave() {
  const action = form.value.id ? updateClass : addClass
  action(form.value).then(() => {
    dialogVisible.value = false
    loadData()
  })
}

function handleDelete(id) {
  if (confirm('确认删除该班级？')) {
    deleteClass(id).then(() => {
      loadData()
    })
  }
}

onMounted(() => {
  loadData()
})
</script>

<style scoped>
.class-list {
  padding: 20px;
}
</style>
