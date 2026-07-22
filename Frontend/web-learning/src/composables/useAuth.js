import {ref, computed} from 'vue'
 
const username = ref(localStorage.getItem('username'))
const token = ref(localStorage.getItem('token'))

export function useAuth() {
  const isAuthenticated = computed(() => !!token.value)
  
  function getToken(){
    return token.value
  } 

  function getUsername() {
    return username.value
  }

  function clearSession() {
    username.value = null
    token.value = null
    localStorage.removeItem('username')
    localStorage.removeItem('token')
  }

  function setSession(newToken, newUsername) {
    username.value = newUsername
    token.value = newToken
    if (newUsername) {
      localStorage.setItem('username', newUsername)
    }
    localStorage.setItem('token', newToken)
  }
  

  return {
    isAuthenticated,
    getToken,
    getUsername,
    clearSession,
    setSession
  }
}
