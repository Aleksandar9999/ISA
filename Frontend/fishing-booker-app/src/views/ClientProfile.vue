<template>
<div class="profile">
    <div class=items>
            <div style="margin:30px">
                <span class="profile-title">Name:</span>
                <span class="profile-value" id='name'><input type="text"></span>
            </div>
            <div style="margin:30px">
                <span class="profile-title">Surname:</span>
                <span class="profile-value" id='lastname'><input type="text"></span>
            </div>
            <div style="margin:30px">
                <span class="profile-title">E-mail address:</span>
                <span class="profile-value" id='username'><input type="text" disabled></span>
            </div>

            <div style="margin:30px">
                <span class="profile-title">Datum rođenja:</span>
                <span class="profile-value" id='birthDate'><input type="text"></span>
            </div>
            <div style="margin:30px">
                <span class="profile-title">Address:</span>
                <span class="profile-value" id='gender' ><input type="text"></span>
            </div>
            <div style="margin:30px">
                <span class="profile-title">City:</span>
                <span class="profile-value" id='category' ><input type="text"></span>
            </div>
            <div style="margin:30px">
                <span class="profile-title">State:</span>
                <span class="profile-value" id='points' ><input type="text"></span>
            </div>
            <div style="margin:30px">
                <span class="profile-title">Phone number:</span>
                <span class="profile-value" id='discount' ><input type="text"></span>
            </div>
    </div>
    <div class="edit-profile">
        <button >Edit profile data</button>
    </div>
    <div class="edit-profile">
        <button  v-bind="togglePass" v-on:click="toggle()">Edit password</button>
        <br/>
    </div>
    <div class="edit-profile">
        <div id="passChangeBox" class="toggle-pass-hidden">
            <label for="">New Password: </label>
            <input type="password"> <br/><br/>
            <label for="">Retype Password: </label>
            <input type="password"> <br/>
            <div id="warnBox" class="pass-warning"><p >Passwords are not equal.</p></div>
            <button v-on:click="submitPass()">Submit</button>
        </div>
    </div>
        </div>
</template>

<script>
 //var warningB= false;
 
 export default {
     data(){
         return {
             togglePass: false,
             newPass:'',
             newPassRetype:'',
            
         }
     },
     methods: {
         toggle(){
             this.togglePass=true;
             document.getElementById("passChangeBox").classList.toggle("toggle-pass-show")
         },

         submitPass(){
             this.validatePass()
         },

         validatePass(){
             if(this.newPass===this.newPassRetype){
                 return
             } else {
                 document.getElementById("warnBox").classList.toggle("pass-warning-act")
             }
         }
     }
}
</script>

<style scoped>
.profile{
    width: 60%;
    background-color: white;
    height: 450px;
    border-radius: 10px;
    margin-top: 50px;
    align-items: left;
    justify-content: left;
    display: inline-block;
}

.edit-profile button{
    margin: 20px;
    width: 160px;
    height: 50px;
    border-radius: 15px;
    border: 0px;
    background-color: green;
    font-size: medium;
    color: white;
    transition: 0.5s;
}
.edit-profile button:hover{
    background-color: chartreuse;
}

.profile-title{
    font-weight: bolder;
    font-size: 18px;
    float: left;
}

.profile-value{
    margin-left: 10px;
    font-size: 18px;
    font-weight: 500;  
  }

.items{
    float: left;
}

.toggle-pass-hidden{
    display: none;
    border: solid 2px black;
    padding: 15px
}

.toggle-pass-show{
    display: inline-block;
}

.pass-warning{
    display: none;
}

.pass-warning p {
    font-size: 16px;
    color: red;
}

.pass-warning-act{
    display: inline-block;
}
</style>