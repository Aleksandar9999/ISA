<template>
  <div>
    <w-dialog
      v-model="dialog.show"
      :fullscreen="dialog.fullscreen"
      :width="dialog.width"
      :persistent="dialog.persistent"
      :persistent-no-animation="dialog.persistentNoAnimation"
      title-class="backgorund-color: green;"
    >
      <template #title> Discount Offer </template>
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Validity period</p>
        </div>
      </w-flex>
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Start date:</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="date" v-model="discountOfferLocal.validityPeriod.startDate"> </w-input>
        </div>
      </w-flex>
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>End date:</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="date" v-model="discountOfferLocal.validityPeriod.endDate"> </w-input>
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Reservation period</p>
        </div>
      </w-flex>
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Start date:</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="date" v-model="discountOfferLocal.reservationPeriod.startDate"> </w-input>
        </div>
      </w-flex>
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>End date:</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="date" v-model="discountOfferLocal.reservationPeriod.endDate"> </w-input>
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Max num of persons:</p>
        </div>
        <div class="xs6 pa1">
          <input type="number" v-model="discountOfferLocal.maxPerson" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Additional services</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="discountOfferLocal.additionalServices" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Price:</p>
        </div>
        <div class="xs6 pa1">
          <input type="number" v-model="discountOfferLocal.price" />
        </div>
      </w-flex>

      <template #actions>
        <div class="spacer" />
        <w-button class="mr2" @click="hideDialog" bg-color="error">
          Discard
        </w-button>
        <w-button @click="save" bg-color="success"> Save </w-button>
      </template>
    </w-dialog>
  </div>
</template>
<script>
import axios from "axios";
import config from "../../../../configuration/config";
export default {
  props: ["show", "idservice"],
  data() {
    return {
      dialog: {
        show: false,
        fullscreen: false,
        persistent: true,
        persistentNoAnimation: false,
        width: 400,
      },
      success: false,
      clients: [],
      services: [],
      discountOfferLocal:{
        validityPeriod:{
          startDate:'',
          endDate:''
        },
        reservationPeriod:{
          startDate:'',
          endDate:''
        },
        maxPerson:'',
        additionalServices:'',
        price:''
      }
    };
  },
  mounted() {
  },
  methods: {
    hideDialog() {
      this.dialog.show = false;
      this.$emit("hideDialog", {
        dialog: this.dialog.show,
        success: this.success,
      });
    },
    save() {
  
      axios.post(config.apiStart+"/api/tutor-services/"+this.idservice+"/discount-offers",this.discountOfferLocal,config.requestHeader).then(console.log("CREATED"))
      console.log(this.discountOfferLocal)
    },
  },
  watch: {
    show: {
      immediate: true,
      handler(fromProp) {
        if (fromProp) this.dialog.show = fromProp;
      },
    },
  },
};
</script>
<style>
p {
  color: black;
}
</style>
