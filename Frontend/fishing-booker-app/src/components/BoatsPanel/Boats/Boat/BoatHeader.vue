<template>
  <div>
    <w-card class="info-card" no-border>
      <w-form no-keyup-validation no-blur-validation>
        <div class="text-right mt6" style="margin: 0px 0px 25px 0px">
          <w-button type="submit" :bg-color="subscribeButton.bgColor" color="white" v-if="showSubscribeButtonsFunc()" @click="subscribe" >{{subscribeButton.title}}</w-button>
          <w-button bg-color="blue" style="margin:0px 0px 0px 25px" color="white" v-if="showSubscribeButtonsFunc()" @click="showNewAppointmentDialog" >Make reservation</w-button>
        
        </div>
        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Boat name:</p>
          </div>
          <div class="xs6 pa1">
            <w-input 
              class="mb3"
              style="color: black"
              v-model="boat_form.name"
              bg-color="white"
              border
              placeholder="Enter boat name"
              outline
            >
            </w-input>
          </div>
        </w-flex>

           <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Boat type:</p>
          </div>
          <div class="xs6 pa1">
            <w-input 
              class="mb3"
              style="color: black"
              v-model="boat_form.typeOfBoat"
              bg-color="white"
              border
              placeholder="Enter boat type"
              outline
            >
            </w-input>
          </div>
        </w-flex>

           <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Boat length:</p>
          </div>
          <div class="xs6 pa1">
            <w-input 
              class="mb3"
              style="color: black"
              v-model="boat_form.length"
              bg-color="white"
              border
              placeholder="Enter boat length"
              outline
            >
            </w-input>
          </div>
        </w-flex>

           <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Boat engine id:</p>
          </div>
          <div class="xs6 pa1">
            <w-input 
              class="mb3"
              style="color: black"
              v-model="boat_form.engineId"
              bg-color="white"
              border
              placeholder="Enter boat engine id"
              outline
            >
            </w-input>
          </div>
        </w-flex>

           <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Boat engine power:</p>
          </div>
          <div class="xs6 pa1">
            <w-input 
              class="mb3"
              style="color: black"
              v-model="boat_form.enginePower"
              bg-color="white"
              border
              placeholder="Enter boat engine power"
              outline
            >
            </w-input>
          </div>
        </w-flex>

           <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Boat max speed:</p>
          </div>
          <div class="xs6 pa1">
            <w-input 
              class="mb3"
              style="color: black"
              v-model="boat_form.maxSpeed"
              bg-color="white"
              border
              placeholder="Enter boat max speed"
              outline
            >
            </w-input>
          </div>
        </w-flex>

           <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Boat navigation equipment:</p>
          </div>
          <div class="xs6 pa1">
            <w-input 
              class="mb3"
              style="color: black"
              v-model="boat_form.navigationEquipment"
              bg-color="white"
              border
              placeholder="Enter boat navigation equipment"
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
              v-model="boat_form.maxPerson"
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
            <p>Cancel percentage</p>
          </div>
          <div class="xs6 pa1">
              <w-input
              type="number"
              class="mb3"
              style="color: black"
              v-model="boat_form.cancelPercentage"
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
              v-model="boat_form.boatAddress.country"
              bg-color="white"
              border
              placeholder="Country"
              outline
            />
<w-input
              
              class="mb3"
              style="color: black"
              v-model="boat_form.boatAddress.city"
              bg-color="white"
              border
              placeholder="City"
              outline
            />

              <w-input
             
              class="mb3"
              style="color: black"
              v-model="boat_form.boatAddress.street"
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
              v-model="boat_form.description"
              bg-color="white"
              border
              placeholder="Boat description"
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
              v-model="boat_form.fishingEquipment"
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
              v-model="boat_form.rules"
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
              v-model="boat_form.extrasServices"
              bg-color="white"
              border
              placeholder="Extras Services"
              outline
            ></w-textarea>
          </div>
        </w-flex>


        <div v-if="showAdminButton" class="text-right mt6">
          <w-button type="submit" bg-color="green" color="white" @click="save">Save</w-button>
        </div>
      </w-form>
    </w-card>
  </div>
</template>

<script>
import config from '../../../../configuration/config';
export default {
  props: ["boat_info","boatOwnerId","showNewAppointmentDialog","showAdminButton"],
  data() {
    return {
      boat_form: {},
      subscribeButton:{
        title:'SUBSCRIBE',
        bgColor:'red'
      }
    };
  },
  methods: {
    isSubscribed(){
      this.$axios.get(`${config.apiStart}/api/boats/${this.boat_info.id}/customers/me`).then(resp=>{
        this.subscribeButton.title="UNSUBSCRIBE"
        this.subscribeButton.bgColor=''
        console.log(resp)
      }).catch(()=>{
        this.subscribeButton.title="SUBSCRIBE"
        this.subscribeButton.bgColor='red'
      })
    },
    showSubscribeButtonsFunc(){
      if (localStorage.roles)
        return localStorage.roles.trim() === "ROLE_USER";
    },
    subscribe(){
      if(this.subscribeButton.title=='SUBSCRIBE')
        this.$axios.post(`${config.apiStart}/api/boats/${this.boat_info.id}/customers`,this.boat_form).then(()=>this.isSubscribed()
        );
      else this.$axios.delete(`${config.apiStart}/api/boats/${this.boat_info.id}/customers`,this.boat_form).then(()=>this.isSubscribed()); 
      
    },
    save() {
      console.log(this.boat_form);
      this.$axios.put(config.apiStart+"/api/boats/"+this.boat_form.id,this.boat_form,config.requestHeader)
      .then(resp=>{this.boat_form=resp.data; alert("Updated");})
      .catch((err)=>{
        alert(err.response.data.message)
        window.location.reload();
       })
    },
    nameChanged($event) {
      this.boat_form.name = $event.data;
      this.$emit("input", this.boat);
    },
  },
  watch: {
    boat_info: {
      immediate: true,
      handler(boatFromProps) {
        if (boatFromProps) {
          this.boat_form = {
            ...this.boat_form,
            ...boatFromProps,
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
