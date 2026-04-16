<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goToLogin = () => {
  router.push('/login')
}
const goToRegister = () => {
  router.push('/register')
}
</script>


<template>
  <div class="flex flex-col items-center justify-center max-w-2xl island">
    <h1>Dashboard</h1>
    <p>Welcome to your dashboard!</p>
    <div class="flex gap-4 mt-6">
      <button class="btn-primary" @click="goToLogin">Go to Login</button>
      <button class="btn-secondary" @click="goToRegister">Go to Register</button>
    </div>
  </div>
</template>

<style scoped></style>
