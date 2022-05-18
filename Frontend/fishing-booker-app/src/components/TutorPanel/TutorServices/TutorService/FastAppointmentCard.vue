<template lang="">
  <w-card shadow style="margin-top: 15px; background-color: white">
    <w-flex wrap class="text-center">
      <div class="xs6 pa1">
        <div class="py3">
          <b
            >Period: {{ discountOfferLocal.reservationPeriod.startDate }} -
            {{ discountOfferLocal.reservationPeriod.endDate }}
          </b>
          <br />
          <span class="action-label"
            >Discount valid: {{ discountOfferLocal.validityPeriod.startDate }} -
            {{ discountOfferLocal.validityPeriod.endDate }}</span
          >
        </div>
      </div>
      <div class="xs6 pa1">
        <div class="py3">

          <w-flex wrap class="text-center">
            <div class="xs6 pa1">
              <p style="color:red;"><b>{{discountOfferLocal.price}} RSD</b></p>
            </div>
            <div class="xs6 pa1">
          <w-button v-if="showReserveButton" class="ma1" bg-color="success">RESERVE</w-button>
         </div>
          </w-flex>

           </div>
      </div>
    </w-flex>
  </w-card>
</template>

<script>
import moment from "moment";
export default {
  props: ["fast_appoinement", "showReserveButton"],
  data() {
    return {
      discountOfferLocal: {
        validityPeriod: {
          startDate: "",
          endDate: "",
        },
        reservationPeriod: {
          startDate: "",
          endDate: "",
        },
        maxPerson: "",
        additionalServices: "",
        price: "",
      },
    };
  },
  watch: {
    fast_appoinement: {
      immediate: true,
      handler(propsItem) {
        if (propsItem)
          this.discountOfferLocal = {
            ...this.discountOfferLocal,
            ...propsItem,
          };
        this.discountOfferLocal.validityPeriod.startDate = moment(
          this.discountOfferLocal.validityPeriod.startDate
        ).format("MMM DD, YYYY HH:MM");

        this.discountOfferLocal.validityPeriod.endDate = moment(
          this.discountOfferLocal.validityPeriod.endDate
        ).format("MMM DD, YYYY HH:MM");
        this.discountOfferLocal.reservationPeriod.startDate = moment(
          this.discountOfferLocal.reservationPeriod.startDate
        ).format("MMM DD, YYYY HH:MM");
        this.discountOfferLocal.reservationPeriod.endDate = moment(
          this.discountOfferLocal.reservationPeriod.endDate
        ).format("MMM DD, YYYY HH:MM");
      },
    },
  },
};
</script>
<style>
.action-label {
  color: red;
  font-size: 13px;
}
</style>
