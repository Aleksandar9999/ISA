<template>
  <div style="padding-top: 5%">
    <w-flex justify-end class="pa3" style="padding: 0% 20%">
      <w-input type="date" v-model="newPeriod.startDate" > </w-input>
      <w-input
        type="text"
        placeholder="12:00"
        v-model="newPeriod.startTime"
         :min="minDate"
      ></w-input>
      <w-input type="date" v-model="newPeriod.endDate" :min="newPeriod.startDate" > </w-input>
      <w-input
        type="text"
        placeholder="12:00"
        v-model="newPeriod.endTime"
       :min="newPeriod.startTime"
      ></w-input>
      <w-button @click="addNewPeriod">Add new</w-button>
    </w-flex>
    <ResortsTable :dataList="data" :headerList="headers" :itemRow="itemRow" />
  </div>
</template>
<script>
import ResortsTable from "../../../ResortsTable.vue";
import AvailablePeriodRow from "./AvailablePeriodRow.vue";
import config from "../../../../configuration/config";
import moment from 'moment'
export default {
  components: {
    ResortsTable,
    AvailablePeriodRow,
  },
  data() {
    return {
      data: [],
       minDate: moment().format("YYYY-MM-DD"),
      headers: ["START DATE", "END DATE", ""],
      itemRow: AvailablePeriodRow,
      resorts: [],
      newPeriod: {
        startDate: "",
        startTime: "",
        endDate: "",
        endTime: "",
      },
      selectedResort: "",
      idresort: this.$route.params.idresort,
    };
  },
  mounted() {
    console.log("ROUTE ID RESORT:" + this.$route.params.idresort);
    this.fetchData();
  },
  methods: {
    fetchStandardPeriodsForResort() {
      this.$axios.get(`${config.apiStart}/api/resorts/${this.$route.params.idresort}/all-standard-periods`).then((resp) => {
        this.data = resp.data;
        console.log(this.data);
      });
    },
    fetchData() {
      this.fetchStandardPeriodsForResort();
    },
    selectedResortChange(event) {
      let value = event.target.value;
      if (!value) {
        this.fetchStandardPeriodsForResort();
        return;
      }
      this.$axios
        .get(`${config.apiStart}/api/resorts/${value}/all-standard-periods`)
        .then((resp) => {
          this.data = resp.data;
          console.log(this.data);
        });
    },
    addNewPeriod() {
      console.log({
        startDate: `${this.newPeriod.startDate} ${this.newPeriod.startTime}`,
        endDate: `${this.newPeriod.endDate} ${this.newPeriod.endTime}`,
      });
      this.$axios
        .post(
          `${config.apiStart}/api/resorts/${this.$route.params.idresort}/standard-periods`,
          {
            startDate: `${this.newPeriod.startDate} ${this.newPeriod.startTime}:00`,
            endDate: `${this.newPeriod.endDate} ${this.newPeriod.endTime}:00`,
          }
        )
        .then(() => {
          this.newPeriod.startDate = "";
          this.newPeriod.endDate = "";
          this.newPeriod.startTime = "";
          this.newPeriod.endTime = "";
          this.selectedService = "";
          this.fetchData();
        })
        .catch((err)=>{
        alert(err.response.data.message)
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
