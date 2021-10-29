<template>
    <header class="navbar">
        <router-link to="/about"><img class="logo" src="../assets/logo.jpg" alt="Logo"></router-link>
        <ul>
            <li><router-link to="">Home</router-link></li>
        </ul>
        <ul>
            <li><router-link to="/login">Login</router-link></li>
            <li><router-link to="">Register</router-link></li>
        </ul>
    </header>
</template>

<script>
export default {
    name:'Navbar'
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

li, a, router-link {
    font-family: 'Montserrat', sans-serif;
    font-size: 18px;
    color:lightgray;
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

</style>
