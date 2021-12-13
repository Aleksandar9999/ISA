<template>
  <div style="padding-top: 5%">
    <w-flex justify-end class="pa3" style="padding: 0% 20%">
      <w-input type="date" v-model="newPeriod.startDate"> </w-input>
      <w-input type="date" v-model="newPeriod.endDate"> </w-input>
      <w-button @click="addNewPeriod">Add new</w-button>
    </w-flex>
    <CustomTable :dataList="data" :headerList="headers" :itemRow="itemRow" />
  </div>
</template>
<script>
import axios from "axios";
import CustomTable from "../CustomTable.vue";
import AvailablePeriodInfoRow from "../TutorPanel/TutorProfile/AvailablePeriodInfoRow.vue";
import config from "../../configuration/config";
export default {
  components: {
    CustomTable,
    AvailablePeriodInfoRow,
  },
  data() {
    return {
      data: [],
      headers: ["START DATE", "END DATE", ""],
      itemRow: AvailablePeriodInfoRow,
      newPeriod: {
        startDate: "",
        endDate: "",
      },
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      axios
        .get(config.apiStart + "/api/users/boatowners/4/available-periods",config.requestHeader)
        .then((resp) => {
          this.data = resp.data;
          console.log(this.data);
        });
    },
    addNewPeriod() {
      console.log(this.newPeriod);
      axios
        .post(
          config.apiStart + "/api/users/boatowners/4/available-periods",
          this.newPeriod,config.requestHeader
        )
        .then((resp) => {
          console.log(resp);
          this.newPeriod.startDate = "";
          this.newPeriod.endDate = "";
          this.fetchData()
        });
    },
  },
};
</script>
<style>
w-input {
  margin: 50px;
}
</style>
