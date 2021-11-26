<template>
    <header class="navbar">
        <router-link to="/about"><img class="logo" src="../assets/logo.jpg" alt="Logo"></router-link>        
        <ul>
            <li><router-link to="">Home</router-link></li>
            <li><router-link to="/profile">My profile</router-link></li>
            <li ><button v-on:click="dropDown()">Lista</button>
            <div class="dropD">
            <div id="Ddown" class="ddown">
            <router-link to="" class="ddown-link">List of resorts</router-link>
            <router-link to="" class="ddown-link">List of boats</router-link>
            <router-link to="" class="ddown-link">List of tutors and their services</router-link>
            <router-link to="" class="ddown-link">Resort reservation history</router-link>
            <router-link to="" class="ddown-link">Boats reservation history</router-link>
            <router-link to="" class="ddown-link">Tutor services reservation history</router-link>
            <router-link to="" class="ddown-link">Pending appointments</router-link>
            <router-link to="" class="ddown-link">Penalties</router-link>
            <router-link to="" class="ddown-link">Actions</router-link>
            <router-link to="" class="ddown-link">Complains</router-link>
            </div> 
            </div>
            </li> 
                                                     
        </ul>   
                   
        <ul>
            <li><router-link to="/login">Login</router-link></li>
            <li><router-link to="/register">Register</router-link></li>
        </ul>
    </header>
    
</template>

<script>
export default {
    name:'Navbar',
    methods:{
        dropDown(){
            document.getElementById("Ddown").classList.toggle("show-d-d");
        }
    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Montserrat&display=swap');

*{
    box-sizing: border-box;
    border: 0;
    padding: 0;
    background-color: green;
}

li, a, router-link, button {
    font-family: 'Montserrat', sans-serif;
    font-size: 18px;
    color:rgb(238, 241, 232);
    text-decoration: none;
    list-style: none;
}
li{
    float: left;
    margin: 15px;
}

.navbar{
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px 20px;
}

.logo{
    cursor: pointer;
    width: 80px;
    height: 80px;
}

ul li::after {
	content: '';
	width: 0%;
	height: 2px;
	background: rgb(3, 83, 3);
	display: block;
	margin: auto;
	transition: 0.5s;
}

ul li:hover::after{
	width: 100%;
}

.dropD{
  position: relative;
  display: inline-block;
}

.ddown{
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 200px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.ddown-link {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.ddown-link:hover {background-color: #ddd}

.show-d-d {
    display:block;
    }
</style>
