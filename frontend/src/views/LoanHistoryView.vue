<script setup>
import { onMounted, ref, watch } from 'vue'
import * as equipmentApi from '@/api/equipment'

const records = ref([])
const equipmentList = ref([])
const equipmentId = ref(null)
const errorMessage = ref('')

async function load() {
  errorMessage.value = ''
  try {
    records.value = await equipmentApi.listLoans({ equipmentId: equipmentId.value })
  } catch (error) {
    errorMessage.value = error.message
  }
}

function formatTime(value) {
  return new Date(value).toLocaleString('zh-TW')
}

watch(equipmentId, load)

onMounted(async () => {
  load()
  try {
    equipmentList.value = await equipmentApi.list()
  } catch (error) {
    errorMessage.value = error.message
  }
})
</script>

<template>
  <div class="toolbar">
    <p>借用歷史</p>
    <select v-model="equipmentId">
      <option :value="null">全部設備</option>
      <option v-for="equipment in equipmentList" :key="equipment.id" :value="equipment.id">
        {{ equipment.name }}
      </option>
    </select>
  </div>

  <p v-if="errorMessage" class="error">{{ errorMessage }}</p>

  <table>
    <thead>
      <tr>
        <th>設備</th>
        <th>借用人</th>
        <th>借出時間</th>
        <th>歸還時間</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="record in records" :key="record.id">
        <td>{{ record.equipmentName }}</td>
        <td>{{ record.borrowerName }}</td>
        <td>{{ formatTime(record.borrowedAt) }}</td>
        <td>{{ record.returnedAt ? formatTime(record.returnedAt) : '未歸還' }}</td>
      </tr>
      <tr v-if="records.length === 0">
        <td colspan="4">沒有借用紀錄</td>
      </tr>
    </tbody>
  </table>
</template>
