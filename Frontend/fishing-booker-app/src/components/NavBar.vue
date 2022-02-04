<template>
  <header class="navbar">
    <router-link to="/about"
      ><img class="logo" src="../assets/logo.jpg" alt="Logo"
    /></router-link>
    <ul>
      <li v-if="loggedIn == true && userRole == 'USER'">
        <router-link to="/">Home</router-link>
      </li>
      <li v-if="loggedIn == true && userRole == 'TUTOR'">
        <router-link to="/">Home</router-link>
      </li>
      <li v-for="item in tutorNavigationList" :key="item.url">
        <router-link
          v-if="loggedIn == true && userRole == 'TUTOR'"
          :to="item.url"
          >{{ item.title }}</router-link
        >
      </li>

      <li v-if="loggedIn == true">
        <router-link to="/profile">My profile</router-link>
      </li>
      <li v-if="loggedIn == true && userRole == 'USER'">
        <router-link to="/reservation">Make reservation</router-link>
      </li>

      <li v-if="loggedIn == true && userRole == 'USER'">
        <button v-on:click="dropDown()">Lista</button>
        <div class="dropD">
          <div id="Ddown" class="ddown">
            <router-link to="/resortList" class="ddown-link"
              >List of resorts</router-link
            >
            <router-link to="/boatList" class="ddown-link"
              >List of boats</router-link
            >
            <router-link to="/tutorList" class="ddown-link"
              >List of tutors and their services</router-link
            >
            <router-link to="/resortsHistory" class="ddown-link"
              >Resort reservation history</router-link
            >
            <router-link to="/boatsHistory" class="ddown-link"
              >Boats reservation history</router-link
            >
            <router-link to="/tutorsHistory" class="ddown-link"
              >Tutor services reservation history</router-link
            >
            <router-link to="/pendingReservations" class="ddown-link"
              >Pending appointments</router-link
            >
            <router-link to="/penalties" class="ddown-link"
              >Penalties</router-link
            >
            <router-link to="/myDiscOffers" class="ddown-link"
              >Actions</router-link
            >
            <router-link to="/objections" class="ddown-link"
              >Complains</router-link
            >
          </div>
        </div>
      </li>
    </ul>

    <ul>
      <li v-if="loggedIn == false">
        <router-link to="/login">Login</router-link>
      </li>
      <li v-if="loggedIn == false">
        <router-link to="/register">Register</router-link>
      </li>
      <li v-if="loggedIn == true">
        <button @click="logOut()">Log out</button>
      </li>
    </ul>
  </header>
</template>

<script>
import moment from 'moment';
import config from '../configuration/config';
//import config from '../configuration/config';
export default {
  name: "Navbar",
  props: ["loginStatusTrigger"],
  methods: {
    dropDown() {
      document.getElementById("Ddown").classList.toggle("show-d-d");
    },
    logOut() {
      localStorage.clear();
      this.loggedIn=false;
      if (this.$route.fullPath === "/") {
        window.location.reload();
      }
      this.$router.push("/");
    },
  },
  data() {
    return {
      userRole: "",
      loggedIn: true,
      userid: "",
      tutorNavigationList: {},
    };
  },
  mounted() {},
  watch: {
    $route(to, from) {
      console.log(to);
      console.log(from);
      for (let i = 0; i < 1; i++) {
        if ((to.fullPath === "/") & (from.fullPath != "/")) {
          window.location.reload();
        }
      }
    },
    loginStatusTrigger: {
      immediate: true,
      handler() {
        this.loggedIn = true;
        console.log("HENDLER LOGIN STATUS TRIGGER");
        this.loggedIn = localStorage.getItem("jwtToken") ? true : false;
        if (localStorage.getItem("roles")?.split(" ")[1] == "ROLE_TUTOR") {
          this.userRole = "TUTOR";
          this.$axios.get(`${config.apiStart}/api/users/me`,config.requestHeader).then((resp) => {         
            this.tutorNavigationList = {
              1: {
                title: "My Services",
                url: `/tutors/${resp.data.id}/services`,
              },
              2: {
                title: "Calendar",
                url: `/calendar/year/${moment().format('YYYY')}`,
              },
              3: {
                title: "Available periods",
                url: `/tutors/${resp.data.id}/available-periods`,
              },
              4: {
                title: "Business report",
                url: "/business/reports",
              },
            };
          });
        }
      },
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat&display=swap");

* {
  box-sizing: border-box;
  border: 0;
  padding: 0;
  background-color:rgb(224, 224, 224)
}

li,
a,
router-link,
button {
  font-family: "Montserrat", sans-serif;
  font-size: 18px;
  color: rgb(41, 41, 41);
  text-decoration: none;
  list-style: none;
}
li {
  float: left;
  margin: 15px;
}

.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px;
}

.logo {
  cursor: pointer;
  width: 80px;
  height: 80px;
}

ul li::after {
  content: "";
  width: 0%;
  height: 2px;
  background: rgb(26, 26, 26);
  display: block;
  margin: auto;
  transition: 0.5s;
}

ul li:hover::after {
  width: 100%;
}

.dropD {
  position: relative;
  display: inline-block;
}

.ddown {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 200px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.ddown-link {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.ddown-link:hover {
  background-color: #ddd;
}

.show-d-d {
  display: block;
}
</style>
