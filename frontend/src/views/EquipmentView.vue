<script setup>
import { onMounted, ref } from 'vue'
import * as equipmentApi from '@/api/equipment'
import { STATUS_LABELS, TYPE_LABELS } from '@/constants/equipment'

const equipmentList = ref([])
const errorMessage = ref('')

async function load() {
  errorMessage.value = ''
  try {
    equipmentList.value = await equipmentApi.list()
  } catch {
    errorMessage.value = '無法載入設備列表'
  }
}

onMounted(load)
</script>


<template>
  <p>設備列表</p>

  <p v-if="errorMessage" class="error">{{ errorMessage }}</p>

  <table>
    <thead>
      <tr>
        <th>名稱</th>
        <th>類型</th>
        <th>狀態</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="equipment in equipmentList" :key="equipment.id">
        <td>{{ equipment.name }}</td>
        <td>{{ TYPE_LABELS[equipment.type] }}</td>
        <td>{{ STATUS_LABELS[equipment.status] }}</td>
      </tr>
      <tr v-if="equipmentList.length === 0">
        <td colspan="3">沒有設備</td>
      </tr>
    </tbody>
  </table>
</template>
