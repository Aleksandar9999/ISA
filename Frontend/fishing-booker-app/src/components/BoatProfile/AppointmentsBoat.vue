<template>
  <div style="padding-top: 5%">
    <w-flex justify-end class="pa3" style="padding: 0% 20%">
      <w-button @click="dialog.show=true">Add new</w-button>
    </w-flex>
    <CustomTable
      :dataList="data"
      :headerList="headers"
      :itemRow="itemRow"
      @showUserProfile="showUserDialog"
    />
    <NewAppointmentModalDialog :idTutor=dialog.id :show=dialog.show @hideDialog="hideModalDialog" />
  </div>
</template>
<script>
import axios from "axios";
import CustomTable from "../CustomTable.vue";
import AppointmentInfoRow from "../TutorPanel/TutorServices/Appointments/AppointmentInfoRow.vue";
import NewAppointmentModalDialog from "./NewAppointmentDialog.vue"
import config from '../../configuration/config';
export default {
  components: {
    CustomTable,
    AppointmentInfoRow,
    NewAppointmentModalDialog
  },
  data() {
    return {
      data: [],
      headers: ["START DATE", "DURATION", "NUMBER OF PERSONS", "PRICE", ""],
      itemRow: AppointmentInfoRow,
      dialog:{
        show: false,
        id:4
      }
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      axios
        .get(
          "http://localhost:8080/api/users/boatowners/4/boatowner-boat/boats",
          config.requestHeader
        )
        .then((resp) => {
          this.data = resp.data;
          console.log(this.data);
        });
    },
    showUserDialog(event) {
      console.log("EVENT");
      console.log(event);
    },
    addNewPeriod() {},
    hideModalDialog(event){
      this.dialog.show=event.dialog
      console.log(event)
    }
  },
};
</script>
<style scoped>
w-input {
  margin: 50px;
}
</style>
