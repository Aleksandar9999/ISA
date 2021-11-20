import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/LoginModalWindow.vue'
import Register from '../views/RegistrationModalWindow.vue'
import Profile from '../views/ClientProfile.vue'
import ResortProfile from '../components/unauthenthicated_view/ResortProfile.vue'
import BoatProfile from '../components/unauthenthicated_view/BoatProfile.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/profile',
    name:'Profile',
    component: Profile
  },
  {
    path: '/resortProfile',
    name: 'ResortProfile',
    component: ResortProfile,
    props: true
  },
  {
    path:'/boatProfile',
    name: 'BoatProfile',
    component:BoatProfile,
    props:true
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
