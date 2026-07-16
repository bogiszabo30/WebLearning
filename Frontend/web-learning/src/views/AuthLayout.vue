<script setup>
import { GraduationCap } from 'lucide-vue-next';

</script>


<template>
  <div class="min-h-screen w-full flex">
    <aside
      class="flex flex-col justify-center items-center gap-3 w-1/2 relative bg-gradient-to-br from-blue-600 via-indigo-600 to-purple-700">
      <div class="relative flex items-center gap-3 group w-fit">
        <GraduationCap class="size-8 text-white" />
        <span class="text-white text-2xl font-bold">WebLearning</span>
      </div>
      <div class="relative max-w-md ">
        <ul class="space-y-2">
          <li class="text-white gap-3 flex items-center"><span
              class="flex items-center justify-center size-9 rounded-lg bg-white/15 ring-1 ring-white/20">
              <GraduationCap class="size-5" />
            </span><span>Learn at your own pace</span></li>
          <li class="text-white gap-3 flex items-center"><span
              class="flex items-center justify-center size-9 rounded-lg bg-white/15 ring-1 ring-white/20">
              <GraduationCap class="size-5" />
            </span><span>Track your progress</span></li>
          <li class="text-white gap-3 flex items-center"><span
              class="flex items-center justify-center size-9 rounded-lg bg-white/15 ring-1 ring-white/20">
              <GraduationCap class="size-5" />
            </span><span>Join a community of learners</span></li>
        </ul>
      </div>
    </aside>
    <main class="bg-purple-300 flex-1 flex items-center justify-center">
      <RouterView />
    </main>
  </div>
</template>

<style scoped></style>
