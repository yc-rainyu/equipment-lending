import { createRouter, createWebHistory } from 'vue-router'
import EquipmentView from '@/views/EquipmentView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/equipment' },
    { path: '/equipment', component: EquipmentView },
  ],
})

export default router
