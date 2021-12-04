<template>
  <w-card class="main-card" title="Dodatne usluge" no-border>
    <ExtraServiceCard v-for="(service,index) in extras_services_local" :key="index" :service="service"/>
  </w-card>
</template>
<script>
import ExtraServiceCard from './ExtraServiceCard.vue'
export default {
    props:['extraServices'],
    data() {
      return {
        extras_services_local:[]
      }
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
.main-card {
  margin: 0% 10%;
  padding: 3%;
}
</style>
