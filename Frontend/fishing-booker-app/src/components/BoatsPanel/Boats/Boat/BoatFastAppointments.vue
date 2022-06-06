<template>
  <div>
    <w-card class="main-card" title="Fast appointments with discount offer" no-border text-align="left">
      <w-flex v-if="showAdminButton" justify-end class="pa3">
        <w-button @click="showDialog">Add new</w-button>
      </w-flex>
      <FastAppointmentCard
        v-for="(fast_appoinement, index) in fast_appoinements_local"
        :key="index" :showReserveButton="showReserveButton"
        :fast_appoinement="fast_appoinement"
      />
    </w-card>
  </div>
</template>
<script>
import axios from "axios";
import config from "../../../../configuration/config";
import FastAppointmentCard from "../../../TutorPanel/TutorServices/TutorService/FastAppointmentCard.vue";
export default {
  props: ["idboat", "showAdminButton"],
  data() {
    return {
      fast_appoinements_local: [],
      showReserveButton: false,
    };
  },
  components: {
    FastAppointmentCard,
  },
  methods: {
    showDialog() {
      this.$emit("showDiscountOfferDialog", true);
    },
     showReserveButtonFunc() {
    if (localStorage.roles.trim() === "ROLE_USER")
        this.showReserveButton=true;
    },
     currentDateTime() {
      const current = new Date();
      const date = current.getFullYear()+'-'+(current.getMonth()+1)+'-'+current.getDate();
      const time = current.getHours() + ":" + current.getMinutes();
      const dateTime = date +' '+ time;

      return dateTime;
    }
 
  },
  mounted() {
    axios
      .get(
        config.apiStart +
          "/api/boats/" +
          this.idboat +
          "/discount-offers",
        config.requestHeader
      )
      .then((resp) => {
        this.fast_appoinements_local = resp.data;
      });
      this.showReserveButtonFunc();
  },
};
</script>
<style>
.main-card {
  margin-top: 0px;
}
</style>
