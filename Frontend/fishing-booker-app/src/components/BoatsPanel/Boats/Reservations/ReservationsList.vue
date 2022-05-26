<template>
  <div style="padding-top: 5%">
    <w-flex justify-end class="pa3" style="padding: 0% 20%">
      <w-button @click="dialog.show=true">Add new</w-button>
    </w-flex>
    <BoatsTable
      :dataList="data"
      :headerList="headers"
      :itemRow="itemRow"
      @showUserProfile="showUserDialog"
    />
    <NewReservationDialog :idBoatOwner=idBoatOwner :show=dialog.show @hideDialog="hideModalDialog" />
  </div>
</template>
<script>
import axios from "axios";
import BoatsTable from "../../../BoatsTable.vue";
import ReservationInfo from "./ReservationInfo.vue";
import NewReservationDialog from "./NewReservationDialog.vue"
import config from '../../../../configuration/config';
export default {
  components: {
    BoatsTable,
    ReservationInfo,
    NewReservationDialog
  },
  data() {
    return {
      data: [],
      headers: ["START DATE", "DURATION", "NUMBER OF PERSONS", "PRICE", ""],
      itemRow: ReservationInfo,
      dialog:{
        show: false
      },
      idBoat: this.$route.params.idboat
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      let api=`${config.apiStart}/api/boat/${this.idBoat}/appointments`
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
