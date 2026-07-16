<script setup>
import { RouterView, useRoute } from 'vue-router'
import AppNavBar from './components/AppNavBar.vue'
import { computed } from 'vue';

const route = useRoute()
const isAuthRoute = computed(() => {
  return route.path.startsWith("/auth")
})
</script>

<template>
  <div class="flex flex-col min-h-screen">

    <AppNavBar v-if="!isAuthRoute" />

    <div class="flex-1 flex items-center justify-center">
      <RouterView></RouterView>
    </div>
  </div>
</template>
