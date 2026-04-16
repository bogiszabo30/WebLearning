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

<template>
  <div class="flex flex-col items-center justify-center max-w-2xl island">
    <h2>Register</h2>
    <div class="flex flex-col gap-2">
      <label for="username">Username:</label>
      <input id="username" v-model="username" type="text" required class="ui-input" />
    </div>
    <div class="flex flex-col gap-2">
      <label for="email">Email:</label>
      <input id="email" v-model="email" type="email" required class="ui-input" />
    </div>
    <div class="flex flex-col gap-2">
      <label for="password">Password:</label>
      <input id="password" v-model="password" type="password" required class="ui-input" />
    </div>
    <button class="btn-primary mt-2" @click="submitRegister">Register</button>
    <p v-if="message" :class="{ error: isError }">{{ message }}</p>
  </div>
</template>



<style scoped>
.error {
  color: red;
}
</style>
