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
    <NewAppointmentModalDialog :idTutor=idTutor :show=dialog.show @hideDialog="hideModalDialog" />
  </div>
</template>
<script>
import axios from "axios";
import CustomTable from "../../../CustomTable.vue";
import AppointmentInfoRow from "./AppointmentInfoRow.vue";
import NewAppointmentModalDialog from "./NewAppointmentModalDialog.vue"
import config from '../../../../configuration/config';
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
        show: false
      },
      idTutor: this.$route.params.idtutor
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      let api=`${config.apiStart}/api/users/tutors/${this.idTutor}/tutor-service/appointments`
      axios.get(api, config.requestHeader)
        .then((resp) => {
          this.data = resp.data;
          console.log(this.data);
        });
    },
    showUserDialog(event) {
      console.log("IVENT");
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
<style>
w-input {
  margin: 50px;
}
</style>
