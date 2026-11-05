import http from './http'

export function list(params) {
  return http.get('/equipment', { params }).then((response) => response.data)
}

export function create(equipment) {
  return http.post('/equipment', equipment)
}

export function update(id, equipment) {
  return http.put(`/equipment/${id}`, equipment)
}

export function remove(id) {
  return http.delete(`/equipment/${id}`)
}

export function borrow(id, borrowerName) {
  return http.post(`/equipment/${id}/borrow`, { borrowerName })
}

export function returnEquipment(id) {
  return http.post(`/equipment/${id}/return`)
}

export function listLoans(params) {
  return http.get('/loans', { params }).then((response) => response.data)
}
