import axios from 'axios'

const BACKEND_API_URL = import.meta.env.VITE_BACKEND_API_URL

const apiClient = axios.create({
    baseURL: BACKEND_API_URL,
    headers: {
        'Content-Type': 'application/json'
    }
})

export const endpoints = {
    login: '/login',
    register: '/register',
    me: '/me'
}

export const authAPI = {
    login: (credentials) => apiClient.post(endpoints.login, credentials),
    register: (data) => apiClient.post(endpoints.register, data),
    getMe: () => apiClient.get(endpoints.me)
}

export default apiClient
