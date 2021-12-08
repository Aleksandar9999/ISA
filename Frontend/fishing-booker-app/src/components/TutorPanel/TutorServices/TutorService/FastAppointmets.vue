<template>
  <div>
    
    <w-card class="main-card" title="Brze rezervacije" no-border>
    <w-flex justify-end class="pa3" >
      <w-button @click="showDialog">Add new</w-button>
    </w-flex>  
      <FastAppointmentCard
        v-for="(fast_appoinement, index) in fast_appoinements_local"
        :key="index"
        :fast_appoinement="fast_appoinement"
      />
    </w-card>
  </div>
</template>
<script>
import axios from "axios"
import config from "../../../../configuration/config"
import FastAppointmentCard from "./FastAppointmentCard.vue";
export default {
  props: ["idservice"],
  data() {
    return {
      fast_appoinements_local: [],
      token:"eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzcHJpbmctc2VjdXJpdHktZXhhbXBsZSIsInN1YiI6ImRyYWdvT3Jhc2FuaW5AZ21haWwuY29tIiwiYXVkIjoid2ViIiwiaWF0IjoxNjM4OTIxODc2LCJleHAiOjE2Mzg5Mzk4NzZ9.s-hJV_7yyNe5ftQn2ftTu-W7fuQb5O3CXjvIPMoVjNJXt4niUoYmu6NuqdsW5-XqonofcLtMVrksU6HupQc9bA"
    };
  },
  components: {
    FastAppointmentCard,
  },
  methods: {
    showDialog(){
      this.$emit("showDiscountOfferDialog", true);
    }
  },
  mounted() {
    axios.get(config.apiStart+"/api/users/tutors/4/services/"+this.idservice+"/discount-offers", {
            headers: {
              Authorization: "Bearer " + this.token,
            },
          }).then(resp=>{
      console.log(resp.data)
      this.fast_appoinements_local=resp.data
    })
  },
};
</script>
<style>
.main-card {
  margin-top: 0px;
}
</style>
