<template>
  <tr>
    <td>{{ item.id }}</td>
    <td>{{ item.name }}</td>
    <td>{{ item.numOfRooms }}</td>
    <td>{{ item.rules }}</td>
    <td>{{ item.status }}</td>
    <td>{{ rate }}</td>
    <td>
      <a :href="showResort">More info</a>
    </td>
       <td>
      <a :href="showPeriods">Periods</a>
    </td>
       <td>
      <a :href="showAppointments">Appointments</a>
    </td>
         <td>
      <a :href="showCalendar">Calendar</a>
    </td>
    <td style="padding: 10px 0px" v-if="showAdminButtons">
      <w-button class="mr2" @click="deleteResort" bg-color="error">
        Delete
      </w-button>
    </td>
  </tr>
</template>
<script>
 import axios from "axios";
 import config from "../../../configuration/config";
 import YearlyCalendarResort from "./ResortCalendar/ResortCalendarYear/YearlyCalendarResort.vue";
 export default {
   props: ["item", "showAdminButton"],
   data() {
     return {
       item_local: {},
       showResort: "",
       showPeriods:"",
       showAppointments:"",
        showCalendar:"",
       showAdminButtons:false,
      year: YearlyCalendarResort.year,
       rate:''
     };
   },
   mounted() {
     this.showDeleteButton();
     this.$axios.get(`${config.apiStart}/api/revision/resort/${this.item.id}/rate`).then((resp)=>
       this.rate=resp.data
     );
 
   },
   methods: {
     showDeleteButton() {
       if (!localStorage.roles) return;
       if (!localStorage.roles.includes("ROLE_RESORTOWNER")) return;
       if (localStorage.id == this.$route.params.idresortowner) this.showAdminButtons = true;
     },
     deleteResort() {
       if (confirm("Do you really want to delete your resort?")) {
         axios
           .delete(
             config.apiStart + "/api/resorts/" + this.item_local.id,
             config.requestHeader
           )
           .then(() => {
             alert(this.item_local.name + " deleted");
             this.$emit("resortDeleted", true);
           })
           .catch((err)=>{
             alert(err.response.data.message)
           });
       }
     },
   },
   watch: {
     item: {
       immediate: true,
       handler(itemFromProps) {
         if (itemFromProps) {
           this.item_local = {
             ...this.item_local,
             ...itemFromProps,
           };
           console.log(itemFromProps);
           this.status = itemFromProps.status;
           this.showResort =
             "/resortowners/" +
             this.item_local.resortOwnerId +
             "/resorts/" +
             this.item_local.id;
             this.showPeriods= 
              "/resorts/" + this.item_local.id +"/all-standard-periods";
                this.showAppointments= 
              "/resort/" + this.item_local.id +"/appointments";
               this.showCalendar="/resort/" + this.item_local.id + "/calendar/year/" + this.year;

         }
       },
     },
   },
 };
</script>
<style>
td {
  padding: 10px;
}
</style>