<template>
  <w-app>
    <div>
      <Navbar />
      <router-view />
    </div>
  </w-app>

</template>

<script>
import Navbar from "./components/NavBar.vue";
//import axios from "axios"
export default {
  name: "App",
  components: {
    Navbar,
  },
  data() {
    return {
       token:"eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzcHJpbmctc2VjdXJpdHktZXhhbXBsZSIsInN1YiI6ImRyYWdvT3Jhc2FuaW5AZ21haWwuY29tIiwiYXVkIjoid2ViIiwiaWF0IjoxNjM4OTE5NDI5LCJleHAiOjE2Mzg5MjEyMjl9._sgOGos3QS0LAzrvQxUD4Zth3A2dFI1N2kToZO_IbSwfQiIlMBU8ZqPv4bvZduasf5jR7hY3P5JrQSgzSr29WA"
    }
  },
  mounted() {
    //axios.defaults.headers.common['Authorization']="Bearer " + this.token
  },
};
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
