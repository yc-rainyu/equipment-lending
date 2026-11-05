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
