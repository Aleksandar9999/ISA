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
            >Akcija vazi: {{ discountOfferLocal.validityPeriod.startDate }} -
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
          <w-button class="ma1" bg-color="success">Rezervisi</w-button>
         </div>
          </w-flex>

           </div>
      </div>
    </w-flex>
  </w-card>
</template>

<script>
//TODO: Povezati sa proslijedjenom rezervacijom
import moment from "moment";
export default {
  props: ["fast_appoinement"],
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
        ).format("DD-MM-YYYY");

        this.discountOfferLocal.validityPeriod.endDate = moment(
          this.discountOfferLocal.validityPeriod.endDate
        ).format("DD-MM-YYYY");
        this.discountOfferLocal.reservationPeriod.startDate = moment(
          this.discountOfferLocal.reservationPeriod.startDate
        ).format("DD-MM-YYYY");
        this.discountOfferLocal.reservationPeriod.endDate = moment(
          this.discountOfferLocal.reservationPeriod.endDate
        ).format("DD-MM-YYYY");
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
