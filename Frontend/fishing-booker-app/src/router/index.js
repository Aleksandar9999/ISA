import TutorServicePage from "../components/TutorServices/TutorServicePage.vue";
import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/LoginModalWindow.vue'
import Register from '../views/RegistrationModalWindow.vue'
import Profile from '../views/ClientProfile.vue'
import ResortProfile from '../components/unauthenthicated_view/ResortProfile.vue'
import BoatProfile from '../components/unauthenthicated_view/BoatProfile.vue'
import AdventureProfile from '../components/unauthenthicated_view/AdventureProfile.vue'

const routes = [
  {
    path: "/about",
    name: "About",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
  {path: "/login", name: "Login", component: Login},
  {path: "/register", name: "Register",component: Register},
  {path: "/profile", name: "Profile", component: Profile},
  {path: "/users/tutors/:idtutor/services/:idservice", name: "TutorService", component: TutorServicePage},
  
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
  },
  {
    path:'/adventureProfile',
    name:'AdventureProfile',
    component: AdventureProfile,
    props:true
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
