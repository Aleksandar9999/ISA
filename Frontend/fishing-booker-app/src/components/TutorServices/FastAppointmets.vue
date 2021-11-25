<template>
    <w-card class="main-card" title="Brze rezervacije" no-border>
    <FastAppointmentCard v-for="(fast_appoinement, index) in fast_appoinements_local" :key=index :fast_appoinement=fast_appoinement />
  </w-card>
</template>
<script>
import FastAppointmentCard from './FastAppointmentCard.vue'
export default {
    props:['fast_appoinements'],
    data() {
        return {
            fast_appoinements_local:[]
        }
    },
    components:{
        FastAppointmentCard
    },
    watch:{
    fast_appoinements: {
      immediate: true,
      handler(itemFromProp) {
        if (itemFromProp) {
          this.fast_appoinements_local = {
            ...this.fast_appoinements_local,
            ...itemFromProp,
          };
        }
      },
    }}

}
</script>
<style>
.main-card{
    margin-top: 0px;
}
</style>