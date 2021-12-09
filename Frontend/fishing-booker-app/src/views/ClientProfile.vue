<template>
<div>
<div class="profile">
    <div class=items>
        <br/><br/><br/><br/>
            <div style="margin:30px">
                <span class="profile-title">Name:</span>
                <span class="profile-value"><input name="input" v-model="name" type="text"></span>
            </div>
            <div style="margin:30px">
                <span class="profile-title">Surname:</span>
                <span class="profile-value"><input name="input" v-model="surname" type="text"></span>
            </div>
            <div style="margin:30px">
                <span class="profile-title">E-mail address:</span>
                <span class="profile-value"><input type="text" v-model="mail" disabled></span>
            </div>
            <div style="margin:30px">
                <span class="profile-title">Address:</span>
                <span class="profile-value"><input name="input" v-model="address" type="text"></span>
            </div>
            <div style="margin:30px">
                <span class="profile-title">City:</span>
                <span class="profile-value"><input name="input" v-model="city" type="text"></span>
            </div>
            <div style="margin:30px">
                <span class="profile-title">State:</span>
                <span class="profile-value" ><input name="input" v-model="state" type="text"></span>
            </div>
            <div style="margin:30px">
                <span class="profile-title">Phone number:</span>
                <span class="profile-value"><input name="input" v-model="phoneNum" type="text"></span>
            </div>
            <div >
                <button id="btnSave" class="toggle-btn-hidden" v-on:click="saveEdited()">Save changes</button>               
            </div>
    </div>
    <br/>
    <div class="edit-profile">
        <button v-on:click="editData()">{{editProfile}}</button>
    </div>
    <div class="edit-profile">
        <button  v-bind="togglePass" v-on:click="toggle()">Edit password</button>
        <br/>
    </div>
    <div class="edit-profile">
        <div id="passChangeBox" class="toggle-pass-hidden">
            <label for="">New Password: </label>
            <input type="password" v-model="newPass"> <br/><br/>
            <label for="">Retype Password: </label>
            <input type="password" v-model="newPassRetype"> <br/>
            <div id="warnBox" class="pass-warning"><p >Passwords are not equal.</p></div>
            <button v-on:click="submitPass()">Submit</button>
        </div>
    </div>
    <div class="points">
        <div class="points-row"><label for="">Points:</label><input type="text"><br/></div>
        <div class="points-row"><label for="">Cathegory:</label><input type="text"><br/></div>
        <div class="points-row"><label for="">Benefits:</label><input type="text"><br/></div>        
    </div>
    </div>
    <div>
        <button class="delete" v-on:click="deleteProfile">Delete profile</button>
    </div>
    </div>
</template>

<script>
import axios from 'axios';
import config from '../configuration/config';
 
 export default {
     data(){
         return {
             profileData:{},
             togglePass: false,
             newPass:'',
             newPassRetype:'',
             editProfile: 'Edit profile data',
             name:'',
             surname:'',
             mail:'',
             bdate:'',
             address:'',
             city:'',
             state:'',
             phoneNum:'',
             id:'',
             password:'',
             profileReqData:{},
             jwtToken:''
         }
     },
     methods: {
         toggle(){
             this.togglePass=true;
             document.getElementById("passChangeBox").classList.toggle("toggle-pass-show")
         },

         submitPass(){
             if(this.validatePass())
                return
             this.collectPassData()
             axios.post('http://localhost:8080/editUserProfile', this.profileReqData).then(response=>console.log(response.data))
         },

         validatePass(){
             if(this.newPass===this.newPassRetype){
                 return false
             } else {
                 document.getElementById("warnBox").classList.toggle("pass-warning-act")
                 return true
             }
         },
         collectPassData(){
            this.profileReqData.id=this.id;
            this.profileReqData.email=this.mail;
            this.profileReqData.password=this.newPass
            this.profileReqData.name=this.name;
            this.profileReqData.surname=this.surname;
            this.profileReqData.address=this.address;
            this.profileReqData.city=this.city;
            this.profileReqData.country=this.state;
            this.profileReqData.phoneNumber=this.phoneNum;
            this.profileReqData.status='CONFIRMED';
         },

         editData(){
             if(this.editProfile==='Quit editing'){
                 let ipts= document.getElementsByName('input');
                 for(let i =0; i < ipts.length; i++){
                     ipts[i].setAttribute('disabled', true);
                 }   
                 this.editProfile='Edit profile data'
             } else {
                 let ipts= document.getElementsByName('input');
                 for(let i =0; i < ipts.length; i++){
                     ipts[i].removeAttribute('disabled');
                 }   
                 this.editProfile='Quit editing' 
            }


             document.getElementById("btnSave").classList.toggle("toggle-btn-show")
         },

         saveEdited(){
             this.collectData()
             axios.put(config.apiStart+`/api/users/${this.profileReqData.id}`, this.profileReqData,config.requestHeader).then(response=>console.log(response.data))

             let ipts= document.getElementsByName('input');
                 for(let i =0; i < ipts.length; i++){
                     ipts[i].setAttribute('disabled', true);
                 }   
                 this.editProfile='Edit profile data'
         },
         populateProfileData(response){
             this.profileData=response.data;
             this.id=this.profileData.id
             this.mail=this.profileData.email
             this.name=this.profileData.name
             this.surname=this.profileData.surname
             this.address=this.profileData.address.street
             this.city=this.profileData.address.city
             this.state=this.profileData.address.country;
             this.phoneNum=this.profileData.phoneNumber;
         },

         collectData(){
            this.profileReqData.id=this.id;
            this.profileReqData.email=this.mail;
            this.profileReqData.password=this.password;
            this.profileReqData.name=this.name;
            this.profileReqData.surname=this.surname;
            this.profileReqData.address={
                street:this.address,
                city: this.city,
                country: this.state
            }
            this.profileReqData.phoneNumber=this.phoneNum;
            this.profileReqData.status='CONFIRMED';
         },

         deleteProfile(){
             //TODO: Potrebno je da se napravi zahtjev za brisanje profila
             if(confirm('Do you really want to delete your profile?')){
                 axios.delete(config.apiStart+'api/delete-request',this.id).then(response=>console.log(response.data))
             }
         }
     },
     mounted() {
         this.jwtToken=localStorage.jwtToken;
         axios.get(config.apiStart+'/api/userProfile',{headers:{'Authorization':'Bearer ' + this.jwtToken}}).then(response=> this.populateProfileData(response))
     }
}
</script>

<style scoped>
.profile{
    width: 60%;
    background-color: white;
    height: 600px;
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

.toggle-btn-hidden{
    display: none;
}

.toggle-btn-show{
    display: inline-block;
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

.toggle-btn-show:hover{
    background-color: chartreuse;
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

.points{
    margin-top: 5px;
    align-items: left;
    display: inline-block;
    width: 300px;
    border: 2px solid black;
    border-radius: 20px;
    padding: 3%;
}

.points-row{
    display: flex;
    justify-content: space-around;
    margin:5px;
}

.points-row input{
    border:none;
}

.delete{
    display: inline-block;
    margin: 20px;
    width: 160px;
    height: 50px;
    border-radius: 15px;
    border: 0px;
    background-color: red;
    font-size: medium;
    color: black;
    transition: 0.5s;
}

.delete:hover{
    background-color: rgb(184, 91, 91);
}
</style>