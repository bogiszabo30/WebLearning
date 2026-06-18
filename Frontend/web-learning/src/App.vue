<script setup>
import { RouterView, useRoute } from 'vue-router'
import AppNavBar from './components/AppNavBar.vue'
import { computed } from 'vue';

const route = useRoute()
const isLoginOrRegisterRoute = computed(() => {
  return route.path === '/login' || route.path === '/register'
})
</script>

<template>
  <div class="flex flex-col min-h-screen">

    <AppNavBar v-if="!isLoginOrRegisterRoute" />

    <div class="flex-1 flex items-center justify-center">
      <RouterView></RouterView>
    </div>
  </div>
</template>
