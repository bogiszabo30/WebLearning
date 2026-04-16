<script setup>
import { RouterLink } from 'vue-router'
</script>


<template>
    <nav class="sticky top-0 z-50 bg-white shadow-md">
        <div class="max-w-7xl mx-auto px-4 py-4 flex items-center justify-between relative">
            <div class="text-2xl font-bold text-blue-600">WebLearning</div>
            <div id="nav-links" class="flex gap-6 absolute left-1/2 -translate-x-1/2">
                <RouterLink to="/login" class="text-gray-700 hover:text-blue-600 transition">
                    Login
                </RouterLink>
                <RouterLink to="/register" class="text-gray-700 hover:text-blue-600 transition">
                    Register
                </RouterLink>
                <RouterLink to="/dashboard" class="text-gray-700 hover:text-blue-600 transition">
                    Dashboard
                </RouterLink>
            </div>
            <div>
                XY
            </div>
        </div>
    </nav>
</template>

<style scoped></style>