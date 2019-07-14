import axios from 'axios'

axios.defaults.baseURL = '/api'

function createService (serviceName) {
  const svc = axios.create({
    baseURL: axios.defaults.baseURL + '/' + serviceName,
    timeout: 60000
  })
  return svc
}

export default createService
