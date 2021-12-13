<template>
  <div>
    <BoatProfileHeader :boat_info="boat_info" />
    <Gallery :photos="gallery" />
    <ExtrasServices />
      <FastAppointmentsBoats
      @showDiscountOfferDialog="discountOfferDialog.show = true"
      :idboat="idboat"
    />
    <discount-offer-dialog
      :show="discountOfferDialog.show"
      :idboat="idboat"
      @hideDialog="hideDiscountOfferDialog"
    />
    <prices :idboat=idboat @showDialog="this.priceDialog.show=true;" :fetch=this.priceDialog.success />
    <price-dialog :idboat=idboat :show=this.priceDialog.show @hideDialog=checkForUpdatePrices />
  </div>
</template>

<script>
import Gallery from "../TutorPanel/TutorServices/TutorService/Gallery.vue";
import DiscountOfferDialog from "./DiscountOfferDialog.vue";
import FastAppointmentsBoats from "./FastAppointmentsBoats.vue";
import ExtrasServices from "../TutorPanel/TutorServices/TutorService/ExtrasServices.vue";
import Prices from "./Prices.vue";
import PriceDialog from "./PriceDialog.vue";
import axios from "axios";
import config from "../../configuration/config";
import BoatProfileHeader from "./BoatProfileHeader.vue";

export default {
  components: {
    BoatProfileHeader,
    Gallery,
    ExtrasServices,
    FastAppointmentsBoats,
    DiscountOfferDialog,
    Prices,
    PriceDialog
  },
   data() {
    return {
      discountOfferDialog: {
        show: false,
      },
      priceDialog:{
        show:false,
        success:false
      },
      gallery: [],
      boat_info: {
        boatAddress: {
          country: "",
          street: "",
        },
      },
      idboat: this.$route.params.idboat,
      extra_services: [],
      fast_appointments: [],
    };
  },
  methods: {
    checkForUpdatePrices(value){
      this.priceDialog.show=value.dialog;
      if(value.success)
        this.priceDialog.success=!this.priceDialog.success
    },
    fetchData() {
      axios
        .get(
          config.apiStart +
            "/api/boatowner-boats/" +
            this.$route.params.idboat,
          config.requestHeader
        )
        .then((resp) => {
          this.service_info = resp.data;
        });
    },
  },
  mounted() {
    this.fetchData();
  },
};
</script>

<style scoped>
</style>