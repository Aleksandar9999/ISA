<template>
  <Navbar/>
  <router-view/>
</template>

<script>
import Navbar from './components/NavBar.vue'


export default {
  name: 'App',
  components: {
    Navbar
  }
}
</script>

<style>
body{
  background:linear-gradient(to right,#7980ce,rgb(134, 190, 209));
    height: 100%;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background:linear-gradient(to right,#7980ce,rgb(134, 190, 209));
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
