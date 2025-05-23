
<template>
  <div class="upload-container">
    <el-card>
      <h3>上传实验报告</h3>
      <el-form label-width="100px">
        <el-form-item label="选择文件">
          <el-upload
            action="/api/upload"
            :headers="uploadHeaders"
            :on-success="handleSuccess"
            :on-error="handleError"
            :show-file-list="true"
            :limit="1"
            name="file">
            <el-button type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <div v-if="uploadedUrl">
        ✅ 文件已上传：<a :href="uploadedUrl" target="_blank">{{ uploadedUrl }}</a>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const uploadedUrl = ref('')
const uploadHeaders = {
  Authorization: 'Bearer ' + localStorage.getItem('token')
}

function handleSuccess(response) {
  if (response.code === 200) {
    uploadedUrl.value = response.data
  } else {
    alert('上传失败：' + response.message)
  }
}

function handleError(err) {
  alert('上传失败，请检查网络或文件格式')
}
</script>

<style scoped>
.upload-container {
  padding: 20px;
}
</style>
