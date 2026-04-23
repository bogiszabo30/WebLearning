<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { authAPI } from '../services/apiService'

const router = useRouter()
const username = ref('')
const password = ref('')
const message = ref('')
const isError = ref(false)

const submitLogin = async () => {
  message.value = ''
  isError.value = false

  try {
    const response = await authAPI.login({ username: username.value, password: password.value })

    const text = response.data

    if (response.ok) {
      message.value = 'Login successful!'
      isError.value = false
      username.value = ''
      password.value = ''
      console.log(text)


      await new Promise(resolve => setTimeout(resolve, 500))
      router.push({ name: 'dashboard' })
    } else {
      message.value = 'Invalid username or password'
      isError.value = true
    }
  } catch (e) {
    message.value = 'Network error'
    isError.value = true
  }
}
</script>

<template>
  <div class="flex flex-col items-center justify-center max-w-2xl island">
    <h2>Login</h2>
    <div class="flex flex-col gap-2">
      <label for="username">Username:</label>
      <input id="username" v-model="username" type="text" required class="ui-input" />
    </div>
    <div class="flex flex-col gap-2">
      <label for="password">Password:</label>
      <input id="password" v-model="password" type="password" required class="ui-input" />
    </div>
    <button class="btn-primary mt-2" @click="submitLogin">Login</button>
    <p v-if="message" :class="{ error: isError }">{{ message }}</p>
  </div>
</template>



<style scoped>
.error {
  color: red;
}
</style>