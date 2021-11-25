import { createRouter, createWebHistory } from "vue-router";
import Login from "../views/LoginModalWindow.vue";
import Register from "../views/RegistrationModalWindow.vue";
import Profile from "../views/ClientProfile.vue";
import TutorServicePage from "../components/TutorServices/TutorServicePage.vue";
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
  
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
