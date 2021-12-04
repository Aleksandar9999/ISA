<template>
  <div style="padding-top: 5%">
    <w-flex justify-end class="pa3" style="padding: 0% 20%">
      <w-button @click="addNewPeriod">Add new</w-button>
    </w-flex>
    <CustomTable :dataList="data" :headerList="headers" :itemRow="itemRow" />
  </div>
</template>
<script>
import axios from "axios";
import CustomTable from "../../../CustomTable.vue";
import AppointmentInfoRow from "./AppointmentInfoRow.vue";
export default {
  components: {
    CustomTable,
    AppointmentInfoRow,
  },
  data() {
    return {
      data: [],
      headers: ["START DATE", "DURATION", "NUMBER OF PERSONS", "PRICE",""],
      itemRow: AppointmentInfoRow,
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      axios
        .get("http://localhost:8080/api/users/tutors/4/tutor-service/appointments")
        .then((resp) => {
          this.data = resp.data;
          console.log(this.data);
        });
    },
    addNewPeriod() {
    },
  },
};
</script>
<style>
w-input {
  margin: 50px;
}
</style>
