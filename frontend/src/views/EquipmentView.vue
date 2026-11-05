<script setup>
import { onMounted, reactive, ref } from 'vue'
import * as equipmentApi from '@/api/equipment'
import { STATUS_LABELS, TYPE_LABELS } from '@/constants/equipment'

const equipmentList = ref([])
const errorMessage = ref('')
const showForm = ref(false)
const editId = ref(null)
const form = reactive({ name: '', type: 'NOTEBOOK' })
const formError = ref('')

async function load() {
  errorMessage.value = ''
  try {
    equipmentList.value = await equipmentApi.list()
  } catch (error) {
    errorMessage.value = error.message
  }
}

function openForm(equipment) {
  editId.value = equipment?.id ?? null
  form.name = equipment?.name ?? ''
  form.type = equipment?.type ?? 'NOTEBOOK'
  formError.value = ''
  showForm.value = true
}

async function submit() {
  formError.value = ''
  try {
    if (editId.value) {
      await equipmentApi.update(editId.value, form)
    } else {
      await equipmentApi.create(form)
    }
    showForm.value = false
    await load()
  } catch (error) {
    formError.value = error.message
  }
}

async function remove(equipment) {
  if (!window.confirm(`確定要刪除「${equipment.name}」嗎？`)) {
    return
  }
  errorMessage.value = ''
  try {
    await equipmentApi.remove(equipment.id)
    await load()
  } catch (error) {
    errorMessage.value = error.message
  }
}

onMounted(load)
</script>

<template>
  <div class="toolbar">
    <p>設備列表</p>
    <button @click="openForm(null)">新增設備</button>
  </div>

  <form v-if="showForm" class="equipment-form" @submit.prevent="submit">
    <p>{{ editId ? '編輯設備' : '新增設備' }}</p>
    <label>
      名稱
      <input v-model="form.name" />
    </label>
    <label>
      類型
      <select v-model="form.type">
        <option v-for="(label, value) in TYPE_LABELS" :key="value" :value="value">
          {{ label }}
        </option>
      </select>
    </label>
    <p v-if="formError" class="error">{{ formError }}</p>
    <div>
      <button type="submit">送出</button>
      <button type="button" @click="showForm = false">取消</button>
    </div>
  </form>

  <p v-if="errorMessage" class="error">{{ errorMessage }}</p>

  <table>
    <thead>
      <tr>
        <th>名稱</th>
        <th>類型</th>
        <th>狀態</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="equipment in equipmentList" :key="equipment.id">
        <td>{{ equipment.name }}</td>
        <td>{{ TYPE_LABELS[equipment.type] }}</td>
        <td>{{ STATUS_LABELS[equipment.status] }}</td>
        <td>
          <button @click="openForm(equipment)">編輯</button>
          <button @click="remove(equipment)">刪除</button>
        </td>
      </tr>
      <tr v-if="equipmentList.length === 0">
        <td colspan="4">沒有設備</td>
      </tr>
    </tbody>
  </table>
</template>
