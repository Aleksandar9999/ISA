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
export default {
  components: {
    TutorServiceHeader,
    Gallery,
    ExtrasServices,
    FastAppointments,
    DiscountOfferModalDialog,
  },

  data() {
    return {
      token: "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzcHJpbmctc2VjdXJpdHktZXhhbXBsZSIsInN1YiI6ImRyYWdvT3Jhc2FuaW5AZ21haWwuY29tIiwiYXVkIjoid2ViIiwiaWF0IjoxNjM4OTIxODc2LCJleHAiOjE2Mzg5Mzk4NzZ9.s-hJV_7yyNe5ftQn2ftTu-W7fuQb5O3CXjvIPMoVjNJXt4niUoYmu6NuqdsW5-XqonofcLtMVrksU6HupQc9bA",
      discountOfferDialog: {
        show: false,
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
    hideDiscountOfferDialog(value) {
      this.discountOfferDialog.show = value.dialog;
    },
    fetchData() {
      axios
        .get(
          config.apiStart +
            "/api/tutor-services/" +
            this.$route.params.idservice,
          {
            headers: {
              Authorization: "Bearer " + this.token,
            },
          }
        )
        .then((resp) => {
          this.gallery = resp.data.photos;
          this.extra_services = resp.data.extrasServices;
          this.service_info = {
            address: resp.data.address,
            cancelProcentage: resp.data.cancelProcentage,
            description: resp.data.description,
            rules: resp.data.rules,
            fishingEquipment: resp.data.fishingEquipment,
            name: resp.data.name,
            maxPerson: resp.data.maxPerson,
            id: resp.data.id,
          };
          console.log(this.service_info.address);
        });
    },
  },
  mounted() {
    this.fetchData();
  },
};
</script>
<style></style>
