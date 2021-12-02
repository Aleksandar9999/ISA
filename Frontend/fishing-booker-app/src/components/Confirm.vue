<template>
<div>
<h1>Put your id here:</h1>
<input type="text" v-model="this.id">

<button @click="confirm()">Confirm registration</button>
</div>
</template>

<script>
import axios from 'axios'

export default {
    data(){
        return {
            id:-1,
        }
    },
    methods:{
        confirm(){
            axios.post('http://localhost:8080/confirm', this.id).then(response=>
            this.checkResponse(response)
            )
        },
        checkResponse(response){
            if(response.data==='Success'){
                this.$router.push('')
            } else {
                alert('Wrong ID number entered.')
            }
        }
    }
}
</script>

<style scoped>
*{
    margin: 2%;
    display: block;
    justify-content: center;
}
h1{
    margin-left: 7%;
}
input{
    width:80px;
    height: 40px;
    font-size: 16px;
    margin-left: 50%; 
}
button {
    font-size: 18px;
    border: 3px black;
    padding: 4px 5px;
    color: whitesmoke;
    background-color: rgb(15, 71, 10);
    border-radius: 15px;
    width: 160px;
    transition: 0.8s;
    margin-left: 48%; 
  }

  button:hover{
    background-color: rgb(10, 226, 28);
  }
</style>