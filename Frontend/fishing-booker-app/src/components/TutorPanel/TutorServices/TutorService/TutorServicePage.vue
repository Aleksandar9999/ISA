<template>
  <div>
    <TutorServiceHeader :service_info="service_info" />
    <Gallery :photos="gallery" />
    <ExtrasServices />
    <FastAppointments
      @showDiscountOfferDialog="discountOfferDialog.show = true"
      :idservice="idservice"
    />
    <discount-offer-modal-dialog
      :show="discountOfferDialog.show"
      :idservice="idservice"
      @hideDialog="hideDiscountOfferDialog"
    />
    <prices-list :idservice=idservice @showDialog="this.priceDialog.show=true;" :fetch=this.priceDialog.success />
    <price-modal-dialog :idservice=idservice :show=this.priceDialog.show @hideDialog=checkForUpdatePrices />
  </div>
</template>

<script>
import TutorServiceHeader from "./TutorServiceHeader.vue";
import Gallery from "./Gallery.vue";
import ExtrasServices from "./ExtrasServices.vue";
import FastAppointments from "./FastAppointmets.vue";
import axios from "axios";
import config from "../../../../configuration/config";
import DiscountOfferModalDialog from "./DiscountOfferModalDialog.vue";
import PricesList from "./PricesList.vue"
import PriceModalDialog from "./PriceModalDialog.vue"
export default {
  components: {
    TutorServiceHeader,
    Gallery,
    ExtrasServices,
    FastAppointments,
    DiscountOfferModalDialog,
    PricesList, 
    PriceModalDialog
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
      service_info: {
        address: {
          country: "",
          street: "",
        },
      },
      idservice: this.$route.params.idservice,
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
            "/api/tutor-services/" +
            this.$route.params.idservice,
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
<style></style>
