<template>
     <div class="modal-mask">
          <div class="modal-wrapper">
            <div class="modal-container">

              <div class="modal-header">
                <slot name="header">
                  Login
                </slot>
              </div>

              <div class="modal-body">
                <slot name="body">
                    <tr><td><label for="">Email:</label></td><td><input type="text" v-model="email"></td></tr>
                    <tr><td><label for="">Password:</label></td><td><input type="password" v-model="password"></td></tr>                                                               
                </slot>
              </div>

              <div class="modal-footer">
                <slot name="footer">
                <button class="butn" @click="login()">Login</button>
                <router-link to="/">
                <button class="butn" >
                    Close
                </button>
                </router-link>
                </slot>
              </div>
            </div>
          </div>
        </div>
</template>

<script>
import axios from 'axios'


export default {
    name: 'Login',
    data(){
      return{
        email:'',
        password:'',
        regReqData:{}
      }
    },
    methods:{
      storageLoginData(response){
        if(response.data){    
          localStorage.logedIn=true    
          localStorage.jwtToken=response.data 
          this.$router.push('/')       
        }
      },
      login(){
        this.collectData()
        axios.post('http://localhost:8080/api/login', this.regReqData)
          .then(response=>this.storageLoginData(response))
      },
      collectData(){
        this.regReqData.email=this.email;
        this.regReqData.password=this.password;
        this.regReqData.status='CONFIRMED';
      }

    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Montserrat&display=swap');

td{
  text-align: left;
}
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 300px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  font-family: 'Montserrat', sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.butn{
	font-size: 16px;
	background-color: lightgreen;
	border: 1px;
	border-radius: 6px;
	padding: 10px 20px;
	margin: 4px 2px;
	transition-duration: 0.4s;
}

.butn:hover {
  background-color: #4CAF50; /* Green */
  color: white;
}

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>