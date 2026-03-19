<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="submitLogin">
      <div>
        <label for="username">Username:</label>
        <input id="username" v-model="username" type="text" required />
      </div>
      <div>
        <label for="password">Password:</label>
        <input id="password" v-model="password" type="password" required />
      </div>
      <button type="submit">Login</button>
    </form>
    <p v-if="message" :class="{ error: isError }">{{ message }}</p>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const username = ref('')
const password = ref('')
const message = ref('')
const isError = ref(false)

const submitLogin = async () => {
  message.value = ''
  isError.value = false

  try {
    const response = await fetch('http://localhost:8080/api/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: new URLSearchParams({
        username: username.value,
        password: password.value,
      }),
      credentials: 'include', // important for cookies
    })

    const text = await response.text()

    if (response.ok) {
      message.value = 'Login successful!'
      isError.value = false
      username.value = ''
      password.value = ''
      console.log(text) // contains {"status":"success"} from Spring
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

<style scoped>
.login-container {
  max-width: 300px;
  margin: auto;
  padding: 2rem;
  border: 1px solid #ccc;
  border-radius: 8px;
}

.error {
  color: red;
}
</style>