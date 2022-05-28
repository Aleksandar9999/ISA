<template>
  <div>
    <div class="profile">
      <div class="items">
        <br /><br /><br /><br />
        <div style="margin: 30px">
          <span class="profile-title">Name:</span>
          <span class="profile-value"
            ><input name="input" v-model="name" type="text"
          /></span>
        </div>
        <div style="margin: 30px">
          <span class="profile-title">Surname:</span>
          <span class="profile-value"
            ><input name="input" v-model="surname" type="text"
          /></span>
        </div>
        <div style="margin: 30px">
          <span class="profile-title">E-mail address:</span>
          <span class="profile-value"
            ><input type="text" v-model="mail" disabled
          /></span>
        </div>
        <div style="margin: 30px">
          <span class="profile-title">Address:</span>
          <span class="profile-value"
            ><input name="input" v-model="address" type="text"
          /></span>
        </div>
        <div style="margin: 30px">
          <span class="profile-title">City:</span>
          <span class="profile-value"
            ><input name="input" v-model="city" type="text"
          /></span>
        </div>
        <div style="margin: 30px">
          <span class="profile-title">State:</span>
          <span class="profile-value"
            ><input name="input" v-model="state" type="text"
          /></span>
        </div>
        <div style="margin: 30px">
          <span class="profile-title">Phone number:</span>
          <span class="profile-value"
            ><input name="input" v-model="phoneNum" type="text"
          /></span>
        </div>
        <div>
          <button
            id="btnSave"
            class="toggle-btn-hidden"
            v-on:click="saveEdited()"
          >
            Save changes
          </button>
        </div>
      </div>
      <br />
      <div class="edit-profile">
        <button v-on:click="editData()">{{ editProfile }}</button>
      </div>
      <div class="edit-profile">
        <button v-bind="togglePass" v-on:click="toggle()">Edit password</button>
        <br />
      </div>
      <div class="edit-profile">
        <div id="passChangeBox" class="toggle-pass-hidden">
          <label for="">New Password: </label>
          <input type="password" v-model="newPass" /> <br /><br />
          <label for="">Retype Password: </label>
          <input type="password" v-model="newPassRetype" /> <br />
          <div id="warnBox" class="pass-warning">
            <p>Passwords are not equal.</p>
          </div>
          <button v-on:click="submitPass()">Submit</button>
        </div>
      </div>
      <div class="points">
        <div class="points-row">
          <p>Points: {{profileData.points}}</p>
        </div>
        <div class="points-row">
          <p>Penalties count: {{profileData.penaltyCount}}</p>
        </div>
        <div class="points-row">
          <p>Category: {{userCategory.name}}</p>
        </div>
        <div class="points-row">
          <p>{{userCategory.benefits}}</p>
        </div>
      </div>
    </div>
    <div>
      <input v-model="deleteReqComment" placeholder="Delete reason" />
      <button class="delete" @click="deleteProfile">Delete profile</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import config from "../configuration/config";

export default {
  data() {
    return {
      deleteReqComment: "",
      userCategory:{},
      profileData: {},
      togglePass: false,
      newPass: "",
      newPassRetype: "",
      editProfile: "Edit profile data",
      name: "",
      surname: "",
      mail: "",
      bdate: "",
      address: "",
      city: "",
      state: "",
      phoneNum: "",
      id: "",
      password: "",
      profileReqData: {},
      jwtToken: "",
    };
  },
  methods: {
    toggle() {
      this.togglePass = true;
      document
        .getElementById("passChangeBox")
        .classList.toggle("toggle-pass-show");
    },

    submitPass() {
      if (this.validatePass()) return;
      this.collectPassData();
      axios
        .put(
          config.apiStart + `/api/users/${this.profileReqData.id}`,
          this.profileReqData,
          config.requestHeader
        )
        .then((response) => console.log(response.data));
    },

    validatePass() {
      if (this.newPass === this.newPassRetype) {
        return false;
      } else {
        document.getElementById("warnBox").classList.toggle("pass-warning-act");
        return true;
      }
    },
    collectPassData() {
      this.profileReqData.id = this.id;
      this.profileReqData.email = this.mail;
      this.profileReqData.password = this.newPass;
      this.profileReqData.name = this.name;
      this.profileReqData.surname = this.surname;
      this.profileReqData.address = {
        street: this.address,
        city: this.city,
        country: this.state,
      };
      this.profileReqData.phoneNumber = this.phoneNum;
      this.profileReqData.status = "CONFIRMED";
    },

    editData() {
      if (this.editProfile === "Quit editing") {
        let ipts = document.getElementsByName("input");
        for (let i = 0; i < ipts.length; i++) {
          ipts[i].setAttribute("disabled", true);
        }
        this.editProfile = "Edit profile data";
      } else {
        let ipts = document.getElementsByName("input");
        for (let i = 0; i < ipts.length; i++) {
          ipts[i].removeAttribute("disabled");
        }
        this.editProfile = "Quit editing";
      }

      document.getElementById("btnSave").classList.toggle("toggle-btn-show");
    },

    saveEdited() {
      this.collectData();
      axios
        .put(
          config.apiStart + `/api/users/${this.profileReqData.id}`,
          this.profileReqData,
          config.requestHeader
        )
        .then((response) => console.log(response.data));

      let ipts = document.getElementsByName("input");
      for (let i = 0; i < ipts.length; i++) {
        ipts[i].setAttribute("disabled", true);
      }
      this.editProfile = "Edit profile data";
    },
    populateProfileData(response) {
      this.profileData = response.data;
      this.id = this.profileData.id;
      this.mail = this.profileData.email;
      this.newPass = this.profileData.password;
      this.name = this.profileData.name;
      this.surname = this.profileData.surname;
      this.address = this.profileData.address.street;
      this.city = this.profileData.address.city;
      this.state = this.profileData.address.country;
      this.phoneNum = this.profileData.phoneNumber;
      
      
    },

    collectData() {
      this.profileReqData.id = this.id;
      this.profileReqData.email = this.mail;
      this.profileReqData.password = this.newPass;
      this.profileReqData.name = this.name;
      this.profileReqData.surname = this.surname;
      this.profileReqData.address = {
        street: this.address,
        city: this.city,
        country: this.state,
      };
      this.profileReqData.phoneNumber = this.phoneNum;
      this.profileReqData.status = "CONFIRMED";
    },

    deleteProfile() {
      if (confirm("Do you really want to delete your profile?")) {
        axios
          .post(
            config.apiStart + "/api/delete-request",
            { userId: this.id, reason: this.deleteReqComment },
            config.requestHeader
          )
          .then(() => alert("Delete request successfully created. Our adim team will review your request soon."))
          .catch((error)=>alert(error.response.data));
      }
    },
  },
  mounted() {
    this.jwtToken = localStorage.jwtToken;
    console.log(localStorage.jwtToken)
    console.log(config)
    this.$axios.get(`${config.apiStart}/api/system-data/user-categories/me`).then(resp=>this.userCategory=resp.data)

    axios
      .get(config.apiStart + "/api/users/me", config.requestHeader)
      .then((response) => {
        console.log(response.data)
        this.populateProfileData(response)
          this.$axios.get(`${config.apiStart}/api/system-data/user-categories/me`).then(resp=>{
            this.userCategory=resp.data;
            console.log(this.userCategory);
            if(this.profileData.className.includes("User")){
              this.userCategory.benefits="Discount procentage: " + this.userCategory.discountProcentage
            }else{
              this.userCategory.benefits="Revenue procentage: " + this.userCategory.revenueProcentage
            }
            
            })

        });


    if (localStorage.roles.includes("ROLE_ADMIN")) {
      axios
        .get(`${config.apiStart}/api/admins/reset-password`, config.requestHeader)
        .then((response) => {
          console.log(response);
          if (!response.data) this.$router.push("/admin/reset-password");
        });
    }
  },
};
</script>

<style scoped>
.profile {
  width: 60%;
  background-color: white;
  height: 600px;
  border-radius: 10px;
  margin-top: 50px;
  align-items: left;
  justify-content: left;
  display: inline-block;
}

.edit-profile button {
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
.edit-profile button:hover {
  background-color: chartreuse;
}

.profile-title {
  font-weight: bolder;
  font-size: 18px;
  float: left;
}

.profile-value {
  margin-left: 10px;
  font-size: 18px;
  font-weight: 500;
}

.items {
  float: left;
}

.toggle-pass-hidden {
  display: none;
  border: solid 2px black;
  padding: 15px;
}

.toggle-pass-show {
  display: inline-block;
}

.toggle-btn-hidden {
  display: none;
}

.toggle-btn-show {
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

.toggle-btn-show:hover {
  background-color: chartreuse;
}

.pass-warning {
  display: none;
}

.pass-warning p {
  font-size: 16px;
  color: red;
}

.pass-warning-act {
  display: inline-block;
}

.points {
  margin-top: 5px;
  align-items: left;
  display: inline-block;
  width: 300px;
  border: 2px solid black;
  border-radius: 20px;
  padding: 3%;
}

.points-row {
  display: flex;
  justify-content: space-around;
  margin: 5px;
}

.points-row input {
  border: none;
}

.delete {
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

.delete:hover {
  background-color: rgb(184, 91, 91);
}
</style>
