<template>
     <div class="modal-mask">
          <div class="modal-wrapper">
            <div class="modal-container">

              <div class="modal-header">
                <slot name="header">
                  Registration
                </slot>
              </div>

              <div class="modal-body">
                <slot name="body">
                    <table>
                        <tr><td><label for="">Email:</label></td><td><input type="text" v-model="email"></td></tr>
                        <tr><td><label for="">Password:</label></td><td><input type="password" v-model="password"></td></tr>
                        <tr><td><label for="">Repeat Password:</label></td><td><input type="password" v-model="rpassword"></td></tr>
                        <tr><td><label for="">Name:</label></td><td><input type="text" v-model="name"></td></tr>
                        <tr><td><label for="">Surname:</label></td><td><input type="text" v-model="surname"></td></tr>
                        <tr><td><label for="">Adress:</label></td><td><input type="text" v-model="adress"></td></tr>
                        <tr><td><label for="">City:</label></td><td><input type="text" v-model="city"></td></tr>
                        <tr><td><label for="">State</label></td><td><input type="text" v-model="state"></td></tr>
                        <tr><td><label for="">Phone number</label></td><td><input type="text" v-model="phoneNum"></td></tr>                        
                    </table>                   
                </slot>
              </div>

              <div class="modal-footer">
                <slot name="footer">
                <button class="butn" v-on:click="this.register">Register</button>
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
    name: 'Registration',
    data() {
        return{
           regReqData: {},
           email:'',
           password:'',
           rpassword:'',
           name:'',
           surname:'',
           adress:'',
           city:'',
           state:'',
           phoneNum:''
        }
    },
    methods: {
      validate(){
        if(this.password!==this.rpassword){
          return false;
        }
        return true;
      },
      register(){
        if(!this.validate()){
          return;
        }
        this.collectData();
        axios.post('http://localhost:4000/register', this.regReqData)
          .then(response=>console.log(response))
      },   
      collectData(){
        this.regReqData.id='';
        this.regReqData.email=this.email;
        this.regReqData.password=this.password;
        this.regReqData.name=this.name;
        this.regReqData.surname=this.surname;
        this.regReqData.address=this.address;
        this.regReqData.city=this.city;
        this.regReqData.country=this.state;
        this.regReqData.phoneNumber=this.phoneNum;
        this.regReqData.status='';
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
</style>