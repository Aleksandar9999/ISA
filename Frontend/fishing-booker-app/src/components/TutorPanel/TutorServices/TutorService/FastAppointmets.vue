<template>
  <div>
    <w-card class="main-card" title="Fast appointments with discount offer" no-border>
      <w-flex v-if="showAdminButtons" justify-end class="pa3">
        <w-button @click="showDialog">Add new</w-button>
      </w-flex>
      <FastAppointmentCard
        v-for="(fast_appoinement, index) in fast_appoinements_local"
        :key="index" :showReserveButton="false"
        :fast_appoinement="fast_appoinement"
      />
    </w-card>
  </div>
</template>
<script>
import axios from "axios";
import config from "../../../../configuration/config";
import FastAppointmentCard from "./FastAppointmentCard.vue";
export default {
  props: ["idservice"],
  data() {
    return {
      fast_appoinements_local: [],
      showAdminButtons: false,
    };
  },
  components: {
    FastAppointmentCard,
  },
  methods: {
    showDialog() {
      this.$emit("showDiscountOfferDialog", true);
    },
    showAdminButtonsFunc() {
      if (localStorage.roles)
        if (localStorage.roles.includes("ROLE_TUTOR")) {
          this.showAdminButtons = true;
        }
    },
  },
  mounted() {
    this.showAdminButtonsFunc();
    axios
      .get(
        config.apiStart +
          "/api/tutor-services/" +
          this.idservice +
          "/discount-offers",
        config.requestHeader
      )
      .then((resp) => {
        this.fast_appoinements_local = resp.data;
      });
  },
};
</script>
<style>
.main-card {
  margin-top: 0px;
}
</style>
