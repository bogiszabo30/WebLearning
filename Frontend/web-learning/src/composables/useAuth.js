import {ref, computed} from 'vue'
 
const username = ref(localStorage.getItem('username') || '')
const token = ref(localStorage.getItem('token') || '')

export function useAuth() {
  const isAuthenticated = computed(() => !!token.value)
  
  function getToken(){
    return token.value
  } 

  return {
    isAuthenticated,
    getToken
  }
}
