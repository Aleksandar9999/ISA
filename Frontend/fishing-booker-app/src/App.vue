<template>
  <w-app>
    <div>
      <Navbar :loginStatusTrigger="logginStatusTrigger" />
      <router-view @login="loginTrigger" />
    </div>
  </w-app>
</template>

<script>
import Navbar from "./components/NavBar.vue";
export default {
  name: "App",
  methods: {
    loginTrigger(){
      console.log("Loggin trigger");
      this.logginStatusTrigger=!this.logginStatusTrigger;
    }
  },
  data() {
    return {
      logginStatusTrigger:false
    }
  },
  components: {
    Navbar,
  }
 };
</script>

<style>
body{
  background:linear-gradient(to right,rgb(224, 224, 224),rgb(200, 200, 200));
    height: 100%;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background:linear-gradient(to bottom,rgb(224, 224, 224),rgb(200, 200, 200));
    height: 100%;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
