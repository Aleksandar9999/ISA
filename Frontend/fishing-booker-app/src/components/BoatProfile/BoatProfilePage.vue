<template>
  <div>
    <BoatProfileHeader :boat_info="boat_info" />
    <Gallery :photos=gallery />
    <ExtrasServices />
    <FastAppointments />
  </div>
</template>

<script>
import Gallery from "../TutorServices/Gallery.vue";
import ExtrasServices from "../TutorServices/ExtrasServices.vue";
import FastAppointments from "../TutorServices/FastAppointmets.vue";
import axios from "axios";
import config from "../../configuration/config";
import BoatProfileHeader from "./BoatProfileHeader.vue";

export default {
  components: {
    BoatProfileHeader,
    Gallery,
    ExtrasServices,
    FastAppointments,
  },
   data() {
    return {
      gallery: [],
      boat_info:{
        boatAddress:{
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
            "/api/users/boatowners/" +
            this.$route.params.idboatowner +
            "/boats/" +
            this.$route.params.idboat
        )
        .then((resp) => {
          this.gallery = resp.data.photos;
          this.extra_services = resp.data.extrasServices;
          this.boat_info = {
            boatAddress: resp.data.boatAddress,
            cancelPercentage: resp.data.cancelPercentage,
            description: resp.data.description,
            rules: resp.data.rules,
            fishingEquipment: resp.data.fishingEquipment,
            name: resp.data.name,
            typeOfBoat: resp.data.typeOfBoat,
            length: resp.data.length,
            engineId: resp.data.engineId,
            maxSpeed: resp.data.maxSpeed,
            navigationEquipment: resp.data.navigationEquipment,
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

<style scoped>
</style>