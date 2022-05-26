<template>
  <tr>
    <td>{{ item.id }}</td>
    <td>{{ item.name }}</td>
    <td>{{ item.maxPerson }}</td>
    <td>{{ item.rules }}</td>
    <td>{{ item.status }}</td>
    <td>{{ rate }}</td>
    <td>
      <a :href="showBoat">More info</a>
    </td>
       <td>
      <a :href="showPeriods">Periods</a>
    </td>
    <td style="padding: 10px 0px" v-if="showAdminButtons">
      <w-button class="mr2" @click="deleteBoat" bg-color="error">
        Delete
      </w-button>
    </td>
  </tr>
</template>
<script>
 import axios from "axios";
 import config from "../../../configuration/config";
 export default {
   props: ["item", "showAdminButton"],
   data() {
     return {
       item_local: {},
       showBoat: "",
       showPeriods:"",
       showAdminButtons:false,
       rate:''
     };
   },
   mounted() {
     this.showDeleteButton();
     this.$axios.get(`${config.apiStart}/api/revision/boat/${this.item.id}/rate`).then((resp)=>
       this.rate=resp.data
     );
   },
   methods: {
     showDeleteButton() {
       if (!localStorage.roles) return;
       if (!localStorage.roles.includes("ROLE_BOATOWNER")) return;
       if (localStorage.id == this.$route.params.idboatowner) this.showAdminButtons = true;
     },
     deleteBoat() {
       if (confirm("Do you really want to delete your boat?")) {
         axios
           .delete(
             config.apiStart + "/api/boats/" + this.item_local.id,
             config.requestHeader
           )
           .then(() => {
             alert(this.item_local.name + " deleted");
             this.$emit("boatDeleted", true);
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
           this.showBoat =
             "/boatowners/" +
             this.item_local.boatOwnerId +
             "/boats/" +
             this.item_local.id;
            this.showPeriods= 
             "/boats/" + this.item_local.id +"/all-standard-periods";

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