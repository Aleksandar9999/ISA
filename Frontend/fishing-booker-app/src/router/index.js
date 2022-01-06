import TutorServicePage from "../components/TutorPanel/TutorServices/TutorService/TutorServicePage.vue";
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
import BoatProfilePage from '../components/BoatProfile/BoatProfilePage.vue'

import UsersList from '../components/AdminPanel/Users/UsersList.vue'
import SystemSettings from '../components/AdminPanel/SystemSettings.vue'
import ResetPasswordModalDialog from '../components/AdminPanel/ResetPasswordModalDialog.vue'
import RevisionsList from "../components/AdminPanel/Revisions/RevisionsList.vue"
import ComplaintsList from "../components/AdminPanel/Complaints/ComplaintsList.vue"
import TutorServicesList from '../components/TutorPanel/TutorServices/TutorServicesList.vue'
import TutorAvailablePeriods from '../components/TutorPanel/TutorProfile/TutorAvailablePeriods.vue'
import AppointmentsList from '../components/TutorPanel/TutorServices/Appointments/AppointmentsList.vue'

import AppointmentsBoat from '../components/BoatProfile/AppointmentsBoat.vue'
import BoatsList from '../components/BoatProfile/BoatsList.vue'
import BoatOwnerAvailablePeriods from '../components/BoatProfile/BoatOwnerAvailablePeriods.vue'

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
  {path: "/tutors/:idtutor/available-periods", name: "TutorAvailablePeriods", component: TutorAvailablePeriods},
  {path: "/tutors/:idtutor/appointments", name: "TutorServicesAppointments", component: AppointmentsList},
  {path: "/tutors/:idtutor/services/:idservice", name: "TutorService", component: TutorServicePage},
  
  {path: "/revisions",name:"RevisionsList",component: RevisionsList},
  {path: "/complaints",name:"ComplaintsList",component: ComplaintsList},
  {path: "/users",name:"UsersList",component: UsersList},
  {path: "/system/settings",name: "SystemSettings", component:SystemSettings},
  {path: "/admin/reset-password",name: "ResetPassword", component:ResetPasswordModalDialog},
  
  {path:"", name:"FrontPage", component: FrontPage},

  {path: "/boatowners/:idboatowner/boats/:idboat", name: "BoatProfilePage", component: BoatProfilePage},
  {path: "/boatowners/:idboatowner/boats", name: "Boats", component: BoatsList},
  {path: "/boatowners/:idboatowner/available-periods", name: "BoatOwnerAvailablePeriods", component: BoatOwnerAvailablePeriods},
  {path: "/boatowners/:idboatowner/appointments", name: "BoatsAppointments", component: AppointmentsBoat},
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
