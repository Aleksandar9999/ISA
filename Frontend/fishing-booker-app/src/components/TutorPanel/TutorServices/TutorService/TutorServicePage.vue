<template>
  <div>
    <TutorServiceHeader :service_info="service_info" />
    <Gallery :photos=gallery />
    <ExtrasServices />
    <FastAppointments />
  </div>
</template>

<script>
import TutorServiceHeader from "./TutorServiceHeader.vue";
import Gallery from "./Gallery.vue";
import ExtrasServices from "./ExtrasServices.vue";
import FastAppointments from "./FastAppointmets.vue";
import axios from "axios";
import config from "../../../../configuration/config";
export default {
  components: {
    TutorServiceHeader,
    Gallery,
    ExtrasServices,
    FastAppointments,
  },

  data() {
    return {
      gallery: [],
      service_info:{
        address:{
          country:'',
          street:''
        }
      },
      extra_services: [],
      fast_appointments: [],
    };
  },
  methods: {
    async fetchData() {
      await axios
        .get(
          config.apiStart +
            "/api/users/tutors/" +
            this.$route.params.idtutor +
            "/services/" +
            this.$route.params.idservice
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
