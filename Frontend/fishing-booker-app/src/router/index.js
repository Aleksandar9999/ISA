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
import ReservationPage from '../components/ReservationClientPage.vue'

import MonthlyCalendar from '../components/Calendar/CalendarMonths/MonthlyCalendar.vue'
import WeeklyCalendar from '../components/Calendar/CalendarWeeks/WeeklyCalendar.vue'
import YearlyCalendar from '../components/Calendar/CalendarYear/YearlyCalendar.vue'

import MonthlyCalendarBoat from '../components/BoatsPanel/Boats/BoatCalendar/BoatCalendarMonths/MonthlyCalendarBoat.vue'
import WeeklyCalendarBoat from '../components/BoatsPanel/Boats/BoatCalendar/BoatCalendarWeeks/WeeklyCalendarBoat.vue'
import YearlyCalendarBoat from '../components/BoatsPanel/Boats/BoatCalendar/BoatCalendarYear/YearlyCalendarBoat.vue'


import UsersList from '../components/AdminPanel/Users/UsersList.vue'
import DeleteRequestsList from '../components/AdminPanel/Users/DeleteRequests/DeleteRequestsList.vue'

import SystemSettings from '../components/AdminPanel/SystemSettings/SystemSettings.vue'
import ResetPasswordModalDialog from '../components/AdminPanel/ResetPasswordModalDialog.vue'

import RevisionsList from "../components/AdminPanel/Revisions/RevisionsList.vue"
import ComplaintsList from "../components/AdminPanel/Complaints/ComplaintsList.vue"
import AppointmentReportsList from '../components/AdminPanel/AppointmentReport/AppointmentReportsList.vue'

import ReportList from '../components/BusinessReportPanel/ReportList.vue'

import TutorServicesList from '../components/TutorPanel/TutorServices/TutorServicesList.vue'
import TutorAvailablePeriods from '../components/TutorPanel/TutorProfile/TutorAvailablePeriods.vue'
import AppointmentsList from '../components/TutorPanel/TutorServices/Appointments/AppointmentsList.vue'
import DiscountOffer from '../components/DiscountOffers.vue'

import BoatsList from '../components/BoatsPanel/Boats/BoatsList.vue'
import BoatPage from '../components/BoatsPanel/Boats/Boat/BoatPage.vue'
import BoatAvailablePeriods from '../components/BoatsPanel/Boats/Reservations/BoatAvailablePeriods.vue'
import ReservationsList from '../components/BoatsPanel/Boats/Reservations/ReservationsList.vue'
import BoatReportList from '../components/BoatsPanel/Boats/BoatBusinessReport/BoatReportList.vue'
//import BoatOwnerChart from '../components/BoatsPanel/Boats/BoatOwnerChart.vue';

import ResortsList from '../components/ResortsPanel/Resorts/ResortsList.vue'
import ResortPage from '../components/ResortsPanel/Resorts/Resort/ResortPage.vue'
import ResortAvailablePeriods from '../components/ResortsPanel/Resorts/Reservations/ResortAvailablePeriods.vue'
import ResortReservationList from '../components/ResortsPanel/Resorts/Reservations/ResortReservationList.vue'




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

  {path: "/calendar/year/:year", name: "YearlyCalendar", component: YearlyCalendar},
  {path: "/calendar/year/:year/month/:month", name: "MonthlyCalendar", component: MonthlyCalendar},
  {path: "/calendar/week/:date", name: "WeeklyCalendar", component: WeeklyCalendar},

  {path: "/boat/:idboat/calendar/year/:year", name: "YearlyCalendarBoat", component: YearlyCalendarBoat},
  {path: "/boat/:idboat/calendar/year/:year/month/:month", name: "MonthlyCalendarBoat", component: MonthlyCalendarBoat},
  {path: "/boat/:idboat/calendar/week/:date", name: "WeeklyCalendarBoat", component: WeeklyCalendarBoat},

  {path: "/tutors/:idtutor/services", name: "TutorServices", component: TutorServicesList},
  {path: "/tutors/:idtutor/available-periods", name: "TutorAvailablePeriods", component: TutorAvailablePeriods},
  {path: "/tutors/:idtutor/appointments", name: "TutorServicesAppointments", component: AppointmentsList},
  {path: "/tutors/:idtutor/services/:idservice", name: "TutorService", component: TutorServicePage},


  {path: "/business/reports",name:"ReportList",component: ReportList},
  {path: "/revisions",name:"RevisionsList",component: RevisionsList},
  {path: "/complaints",name:"ComplaintsList",component: ComplaintsList},
  {path: "/appointment-reports",name:"AppointmentReportsList",component: AppointmentReportsList},
  
  {path: "/users",name:"UsersList",component: UsersList},
  {path: "/users/delete-requests",name:"DeleteRequestsList",component: DeleteRequestsList},
  
  {path: "/system/settings",name: "SystemSettings", component:SystemSettings},
  {path: "/admin/reset-password",name: "ResetPassword", component:ResetPasswordModalDialog},
  
  {path:"", name:"FrontPage", component: FrontPage},

  {path: "/boatowners/:idboatowner/boats", name: "Boats", component: BoatsList},
  {path: "/boatowners/:idboatowner/boats/:idboat", name: "Boat", component: BoatPage},
  {path: "/boats/:idboat/all-standard-periods", name: "BoatPeriodsPage", component: BoatAvailablePeriods},
  {path: "/boat/:idboat/appointments", name: "BoatAppointmentsPage", component: ReservationsList},
  {path: "/business/reports/boats",name:"ReportListBoats",component: BoatReportList},
 // {path: "/boatowner/charts",name:"Boatowner charts",component: BoatOwnerChart},

 {path: "/resortowners/:idresortowner/resorts", name: "Resorts", component: ResortsList},
 {path: "/resortowners/:idresortowner/resorts/:idresort", name: "Resort", component: ResortPage},
 {path: "/resorts/:idresort/all-standard-periods", name: "ResortPeriodsPage", component: ResortAvailablePeriods},
 {path: "/resort/:idresort/appointments", name: "ResortAppointmentsPage", component: ResortReservationList},



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
  },
  {
    path: '/reservation',
    name:'ReservationPage',
    component: ReservationPage,
    props:true
  },
  {
    path:'/discountOffers',
    name: 'DiscountOffers',
    component: DiscountOffer,
    props: true
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
