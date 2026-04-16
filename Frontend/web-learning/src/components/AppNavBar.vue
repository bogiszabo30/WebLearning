<script setup>
import { ref } from 'vue'
import { RouterLink } from 'vue-router'
import { UserRound, LogOut } from 'lucide-vue-next'

const isMenuOpen = ref(false)

const toggleMenu = () => {
    isMenuOpen.value = !isMenuOpen.value
}

const closeMenu = () => {
    isMenuOpen.value = false
}
</script>


<template>
    <nav class="sticky top-0 z-50 bg-white shadow-md">
        <div class="max-w-7xl mx-auto p-2 flex items-center justify-between relative">
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
            <div class="relative">
                <button @click="toggleMenu"
                    class="size-10 bg-blue-400 rounded-full flex items-center justify-center text-white font-bold text-lg hover:bg-blue-500 transition cursor-pointer">
                    XY
                </button>

                <div v-if="isMenuOpen" @click="closeMenu" class="fixed inset-0 z-40"></div>

                <div v-if="isMenuOpen"
                    class="border border-gray-200 absolute right-0 mt-2 w-48 bg-white rounded-lg shadow-lg z-50 overflow-hidden">
                    <button class="w-full text-left px-3 py-2 text-gray-700 hover:bg-blue-50 transition">
                        <UserRound class="inline size-5" />
                        Profil
                    </button>
                    <div class="border-t border-gray-200"></div>
                    <button class="w-full text-left px-3 py-2 text-red-600 hover:bg-red-50 transition">
                        <LogOut class="inline size-5" />
                        Kilépés
                    </button>
                </div>
            </div>
        </div>
    </nav>
</template>

<style scoped></style>