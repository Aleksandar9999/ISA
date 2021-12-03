import TutorServicePage from "../components/TutorServices/TutorServicePage.vue";
import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/LoginModalWindow.vue'
import Register from '../views/RegistrationModalWindow.vue'
import Profile from '../views/ClientProfile.vue'
import ResortProfile from '../components/unauthenthicated_view/ResortProfile.vue'
import BoatProfile from '../components/unauthenthicated_view/BoatProfile.vue'
import AdventureProfile from '../components/unauthenthicated_view/AdventureProfile.vue'
import ResortList from '../components/ClientMenuLists/ResortList.vue'
import BoatList from '../components/ClientMenuLists/BoatList.vue'
import TutorList from '../components/ClientMenuLists/TutorList.vue'
import ObjectionPage from '../components/ClientMenuLists/ObjectionPage.vue'
import PenaltyList from '../components/ClientMenuLists/PenaltyList.vue'
import MyDiscountOffers from '../components/ClientMenuLists/MyDiscountOffers.vue'
import PendingReservations from '../components/ClientMenuLists/PendingReservations.vue'
import BoatAppointmentHistory from '../components/ClientMenuLists/BoatAppointmentHistory.vue'
import ResortAppointmentHistory from '../components/ClientMenuLists/ResortAppointmentHistory.vue'
import TutorAppointmentHistory from '../components/ClientMenuLists/TutorAppointmentHistory.vue'
import FrontPage from '../components/FrontPage.vue'
import Confirmation from '../components/Confirm.vue'
import PendingUsersList from '../components/AdminPanel/PendingUsersList.vue'
import SystemSettings from '../components/AdminPanel/SystemSettings.vue'
import TutorServicesList from '../components/TutorPanel/TutorServicesList.vue'
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

  {path: "/tutors/:idtutor/services", name: "TutorServices", component: TutorServicesList},
  {path: "/users/tutors/:idtutor/services/:idservice", name: "TutorService", component: TutorServicePage},
  {path: "/users/pending",name:"PendingUsersList",component: PendingUsersList},
  {path: "/system/settings",name: "SystemSettings", component:SystemSettings},
  
  
  {path:"", name:"FrontPage", component: FrontPage},
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
  },
  {
    path:'/resortList',
    name:'ResortList',
    component: ResortList,
    props:true
  },
  {
    path:'/boatList',
    name:'BoatList',
    component: BoatList,
    props:true
  },
  {
    path:'/tutorList',
    name:'TutorList',
    component: TutorList,
    props:true
  },
  {
    path:'/penalties',
    name:'PenaltyList',
    component: PenaltyList,
    props:true
  },
  {
    path:'/myDiscOffers',
    name:'MyDiscountOffers',
    component: MyDiscountOffers,
    props:true
  },
  {
    path:'/boatsHistory',
    name:'BoatAppointmentHistory',
    component: BoatAppointmentHistory,
    props:true
  },
  {
    path:'/pendingReservations',
    name:'PendingReservations',
    component: PendingReservations,
    props:true
  },
  {
    path:'/resortsHistory',
    name:'ResortAppointmentHistory',
    component: ResortAppointmentHistory,
    props:true
  },
  {
    path:'/tutorsHistory',
    name:'TutorAppointmentHistory',
    component: TutorAppointmentHistory,
    props:true
  },
  {
    path:'/objections',
    name:'ObjectionPage',
    component: ObjectionPage,
    props:true
  },
  {
    path:'/confirmation',
    name:'Confirmation',
    component:Confirmation,
    props:true
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
