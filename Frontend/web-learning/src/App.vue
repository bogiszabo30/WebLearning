<script setup lang="ts">
import { RouterView } from 'vue-router'
import AppNavBar from './components/AppNavBar.vue'
</script>

<template>
  <div class="flex flex-col min-h-screen">

    <AppNavBar />

    <div class="flex-1 flex items-center justify-center">
      <RouterView></RouterView>
    </div>
  </div>
</template>
