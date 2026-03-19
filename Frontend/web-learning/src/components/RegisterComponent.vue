<template>
  <div class="register-container">
    <h2>Register</h2>
    <form @submit.prevent="submitRegister">
      <div>
        <label for="username">Username:</label>
        <input id="username" v-model="username" type="text" required />
      </div>
      <div>
        <label for="email">Email:</label>
        <input id="email" v-model="email" type="email" required />
      </div>
      <div>
        <label for="password">Password:</label>
        <input id="password" v-model="password" type="password" required />
      </div>
      <button type="submit">Register</button>
    </form>
    <p v-if="message" :class="{ error: isError }">{{ message }}</p>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const username = ref('')
const email = ref('')
const password = ref('')
const message = ref('')
const isError = ref(false)

const submitRegister = async () => {
  message.value = ''
  isError.value = false

  try {
    const response = await fetch('http://localhost:8080/api/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: username.value,
        email: email.value,
        password: password.value,
      }),
    })

    const data = await response.json()

    if (data.status === 'success') {
      message.value = 'Registration successful!'
      isError.value = false
      username.value = ''
      email.value = ''
      password.value = ''
    } else {
      message.value = data.message || 'Registration failed'
      isError.value = true
    }
  } catch (e) {
    message.value = 'Network error'
    isError.value = true
  }
}
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: auto;
  padding: 2rem;
  border: 1px solid #ccc;
  border-radius: 8px;
}

.error {
  color: red;
}
</style>
