import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import DashboardView from '../views/DashboardView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginView,
    },
    {
        path: '/register',
        name: 'register',
        component: RegisterView,
    },
    {
        path: '/dashboard',
        name: 'dashboard',
        component: DashboardView,
        meta: { requiresAuth: true }
    }
  ],
})

// Navigation guard to check authentication
router.beforeEach(async (to, from, next) => {
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth)
  
  if (requiresAuth) {
    try {
      // Check if user is authenticated
      const response = await fetch('http://localhost:8080/api/check-auth', {
        method: 'GET',
        credentials: 'include',
      })
      
      if (response.ok) {
        next()
      } else {
        // Not authenticated, redirect to login
        next({ name: 'login' })
      }
    } catch (error) {
      console.error('Auth check failed:', error)
      next({ name: 'login' })
    }
  } else {
    next()
  }
})

export default router