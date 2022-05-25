<template>
  <w-card class="main-card" title="Extras services" no-border>
    <ExtraServiceCard v-for="(service,index) in extras_services_local" :key="index" :service="service"/>
  </w-card>
</template>
<script>
import ExtraServiceCard from '../../../TutorPanel/TutorServices/TutorService/ExtraServiceCard.vue'
export default {
    props:['idboat'],
    data() {
      return {
        extras_services_local:[]
      }
    },
    mounted() {
      //axios.get(config.apiStart+"/api/")
    },
    components:{
        ExtraServiceCard
    },
    watch:{
    photos: {
      immediate: true,
      handler(itemFromProp) {
        if (itemFromProp) {
          this.extras_services_local = {
            ...this.extras_services_local,
            ...itemFromProp,
          };
        }
      },
    },
  },
};
</script>
<style>
.w-card__title{
  color: black;
  text-transform: uppercase;
}
.main-card {
  padding: 3%;
}
</style>
