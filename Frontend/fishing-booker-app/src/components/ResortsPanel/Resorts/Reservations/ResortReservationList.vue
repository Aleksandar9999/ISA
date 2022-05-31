<template>
  <div style="padding-top: 5%">
    <w-flex justify-end class="pa3" style="padding: 0% 20%">
      <w-button @click="dialog.show=true">Add new</w-button>
    </w-flex>
    <ResortsTable
      :dataList="data"
      :headerList="headers"
      :itemRow="itemRow"
      @showUserProfile="showUserDialog"
    />
    <NewReservationDialog :idResortOwner=idResortOwner :show=dialog.show @hideDialog="hideModalDialog" />
  </div>
</template>
<script>
import axios from "axios";
import ResortsTable from "../../../ResortsTable.vue";
import ReservationInfo from "./ReservationInfo.vue";
import NewReservationDialog from "./NewReservationDialog.vue"
import config from '../../../../configuration/config';
export default {
  components: {
    ResortsTable,
    ReservationInfo,
    NewReservationDialog
  },
  data() {
    return {
      data: [],
      headers: ["START DATE", "DURATION", "PRICE", ""],
      itemRow: ReservationInfo,
      dialog:{
        show: false
      },
      idResort: this.$route.params.idresort
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      let api=`${config.apiStart}/api/resort/${this.idResort}/appointments`
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
