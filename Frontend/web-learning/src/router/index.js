import { createRouter, createWebHistory } from 'vue-router'
import { useAuth } from '../composables/useAuth.js'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import DashboardView from '../views/DashboardView.vue'
import AuthLayout from '../views/AuthLayout.vue'
import LoginComponent from '../components/LoginComponent.vue'
import RegisterComponent from '../components/RegisterComponent.vue'

const routes = [
  {
    path: '/auth',
    component: AuthLayout,
    children: [
      {
        path: 'login',
        name: 'Login',
        component: LoginComponent
      },
      {
        path: 'register',
        name: 'Register',
        component: RegisterComponent
      }
    ]
  },
  {
    path: '/',
    name: 'Dashboard',
    component: DashboardView,
    meta: { requiresAuth: true }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to) => {
  const { isAuthenticated } = useAuth()
  if (to.meta.requiresAuth && !isAuthenticated.value) {
    return { name: 'Login' }
  }
  if ((to.name === 'Login' || to.name === 'Register') && isAuthenticated.value) {
    return { name: 'Dashboard' }
  }
})


export default router
