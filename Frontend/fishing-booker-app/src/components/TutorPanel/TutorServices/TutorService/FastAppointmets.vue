<template>
  <div>
    
    <w-card class="main-card" title="Brze rezervacije" no-border>
    <w-flex justify-end class="pa3" >
      <w-button @click="showDialog">Add new</w-button>
    </w-flex>  
      <FastAppointmentCard
        v-for="(fast_appoinement, index) in fast_appoinements_local"
        :key="index"
        :fast_appoinement="fast_appoinement"
      />
    </w-card>
  </div>
</template>
<script>
import axios from "axios"
import config from "../../../../configuration/config"
import FastAppointmentCard from "./FastAppointmentCard.vue";
export default {
  props: ["idservice"],
  data() {
    return {
      fast_appoinements_local: [],
    };
  },
  components: {
    FastAppointmentCard,
  },
  methods: {
    showDialog(){
      this.$emit("showDiscountOfferDialog", true);
    }
  },
  mounted() {
    axios.get(config.apiStart+"/api/users/tutors/4/services/"+this.idservice+"/discount-offers").then(resp=>{
      console.log(resp.data)
      this.fast_appoinements_local=resp.data
    })
  },
};
</script>
<style>
.main-card {
  margin-top: 0px;
}
</style>
