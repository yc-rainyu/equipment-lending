import http from './http'

export function list(params) {
  return http.get('/equipment', { params }).then((response) => response.data)
}
