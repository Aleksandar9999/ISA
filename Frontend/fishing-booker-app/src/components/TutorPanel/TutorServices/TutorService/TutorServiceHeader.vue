<template>
  <div>
    <w-card class="info-card" no-border>
      <w-form no-keyup-validation no-blur-validation>
        <div class="text-right mt6" style="margin: 0px 0px 25px 0px">
          <w-button type="submit" :bg-color="subscribeButton.bgColor" color="white" v-if="showSubscribeButtonsFunc()" @click="subscribe" >{{subscribeButton.title}}</w-button>
        </div>
        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Service name:</p>
          </div>
          <div class="xs6 pa1">
            <w-input 
              class="mb3"
              style="color: black"
              v-model="service_form.name"
              bg-color="white"
              border
              placeholder="Enter service name"
              outline
            >
            </w-input>
          </div>
        </w-flex>

        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Max number of persons</p>
          </div>
          <div class="xs6 pa1">
            <w-input
              type="number"
              class="mb3"
              style="color: black"
              v-model="service_form.maxPerson"
              bg-color="white"
              border
              placeholder="Enter maximum number of persons"
              outline
            >
            </w-input>
          </div>
        </w-flex>

        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Cancel procentage</p>
          </div>
          <div class="xs6 pa1">
              <w-input
              type="number"
              class="mb3"
              style="color: black"
              v-model="service_form.cancelProcentage"
              bg-color="white"
              border
              placeholder="Percentage in case of appointment cancellation"
              outline
            />
          </div>
        </w-flex>
        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Address</p>
          </div>
          <div class="xs6 pa1">
              <w-input
              
              class="mb3"
              style="color: black"
              v-model="service_form.address.country"
              bg-color="white"
              border
              placeholder="Country"
              outline
            />
<w-input
              
              class="mb3"
              style="color: black"
              v-model="service_form.address.city"
              bg-color="white"
              border
              placeholder="City"
              outline
            />

              <w-input
             
              class="mb3"
              style="color: black"
              v-model="service_form.address.street"
              bg-color="white"
              border
              placeholder="Street"
              outline
            />
          </div>
        </w-flex>
        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p style="margin-top: 3%">Short description</p>
          </div>
          <div class="xs6 pa1">
              <w-textarea
              class="mb3"
              style="color: black"
              v-model="service_form.description"
              bg-color="white"
              border
              placeholder="Service description"
              outline
            />
          </div>
        </w-flex>
        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p style="margin-top: 3%">Equipment</p>
          </div>
          <div class="xs6 pa1">
            
              <w-textarea
              class="mb3"
              style="text-alignment:center"
              color: black
              v-model="service_form.fishingEquipment"
              bg-color="white"
              border
              placeholder="Equipment"
              outline
            ></w-textarea>
            </div>
          </w-flex>

        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p style="margin-top: 3%">Rules</p>
          </div>
          <div class="xs6 pa1">
            <w-textarea
              class="mb3"
              style="text-alignment:center"
              color: black
              v-model="service_form.rules"
              bg-color="white"
              border
              placeholder="Rules"
              outline
            ></w-textarea>
          </div>
        </w-flex>

<w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p style="margin-top: 3%">Extras services</p>
          </div>
          <div class="xs6 pa1">
            <w-textarea
              class="mb3"
              style="text-alignment:center"
              color: black
              v-model="service_form.extrasServices"
              bg-color="white"
              border
              placeholder="Extras Services"
              outline
            ></w-textarea>
          </div>
        </w-flex>

<w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p style="margin-top: 3%">Tutor bio:</p>
          </div>
          <div class="xs6 pa1">
            <w-textarea
              class="mb3"
              style="text-alignment:center"
              color: black
              v-model="service_form.tutorBio"
              bg-color="white"
              border
              placeholder="Tutor bio"
              outline
            ></w-textarea>
          </div>
        </w-flex>
        <div v-if="showAdminButtons" class="text-right mt6">
          <w-button type="submit" bg-color="green" color="white" @click="save">Save</w-button>
        </div>
      </w-form>
    </w-card>
  </div>
</template>

<script>
import axios from 'axios';
import config from '../../../../configuration/config';
export default {
  props: ["service_info","tutorId"],
  data() {
    return {
      service_form: {},
      showAdminButtons:false,
      subscribeButton:{
        title:'SUBSCRIBE',
        bgColor:'red'
      }
    };
  },
  mounted() {
    this.showAdminButtonsFunc();
    
  },
  methods: {
    isSubscribed(){
      this.$axios.get(`${config.apiStart}/api/subscriptions/tutor-service/${this.service_info.id}`).then(resp=>{
        this.subscribeButton.title="UNSUBSCRIBE"
        this.subscribeButton.bgColor=''
        console.log(resp)
      }).catch(()=>{
        this.subscribeButton.title="SUBSCRIBE"
        this.subscribeButton.bgColor='red'
      })
    },
    showAdminButtonsFunc(){
      if (localStorage.roles)
        if (localStorage.roles.includes("ROLE_TUTOR")) {
          this.showAdminButtons = true;
        }
    },
    showSubscribeButtonsFunc(){
      if (localStorage.roles)
        return localStorage.roles.trim() === "ROLE_USER";
    },
    subscribe(){
      if(this.subscribeButton.title=='SUBSCRIBE')
        this.$axios.post(`${config.apiStart}/subscripeTutorService`,this.service_form).then(()=>this.isSubscribed()
        );
      else this.$axios.post(`${config.apiStart}/cancelTutorServiceSubscription`,this.service_form).then(()=>this.isSubscribed()); 
      
    },
    save() {
      console.log(this.service_form);
      axios.put(config.apiStart+"/api/tutor-services/"+this.service_form.id,this.service_form,config.requestHeader).then(resp=>
      {this.service_form=resp.data;
      alert("Updated");
      }
      )
    },
    nameChanged($event) {
      this.service_form.name = $event.data;
      this.$emit("input", this.service);
    },
  },
  watch: {
    service_info: {
      immediate: true,
      handler(serviceFromProps) {
        if (serviceFromProps) {
          this.service_form = {
            ...this.service_form,
            ...serviceFromProps,
          };
          this.isSubscribed()
        }
      },
    },
  },
};
</script>
<style>
p {
  text-align: start;
  color: white;
  font-size: 15px;
}
.flex-container {
  display: flex;
}

.flex-child {
  flex: 1;
}
div.row {
  margin-top: 15px;
  margin-left: 25px;
}
.flex-child:first-child {
  margin-right: 10%;
}
input.text-box-forma {
  margin: 0%;
}
label {
  left: 0;
  right: 0;
  top: 5%;
}

.info-card {
  margin: 0% 10%;
  padding: 3%;
}
textarea{
  max-width: inherit;
  color: black;
  text-align: center;
}
</style>
