<template>
  <div style="padding-top: 5%">
    <w-flex justify-end class="pa3" style="padding: 0% 20%">
      <w-input type="date" v-model="newPeriod.startDate" > </w-input>
      <w-input
        type="text"
        placeholder="12:00"
        v-model="newPeriod.startTime"
      ></w-input>
      <w-input type="date" v-model="newPeriod.endDate" :min="newPeriod.startDate" > </w-input>
      <w-input
        type="text"
        placeholder="12:00"
        v-model="newPeriod.endTime"
      ></w-input>
      <w-button @click="addNewPeriod">Add new</w-button>
    </w-flex>
    <CustomTable :dataList="data" :headerList="headers" :itemRow="itemRow" />
  </div>
</template>
<script>
import CustomTable from "../../CustomTable.vue";
import AvailablePeriodInfoRow from "./AvailablePeriodInfoRow.vue";
import config from "../../../configuration/config";
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
      services: [],
      newPeriod: {
        startDate: "",
        startTime: "",
        endDate: "",
        endTime: "",
      },
      selectedService: "",
      idTutor: this.$route.params.idtutor,
      api: `${config.apiStart}/api/users/tutors/${this.$route.params.idtutor}`,
    };
  },
  mounted() {
    console.log("ROUTE ID TUTOR:" + this.$route.params.idtutor);
    this.fetchData();
  },
  methods: {
    fetchStandardPeriodsForAllTutorServices() {
      this.$axios.get(`${this.api}/standard-periods`).then((resp) => {
        this.data = resp.data;
        console.log(this.data);
      });
    },
    fetchTutorServices() {
      this.$axios
        .get(`${this.api}/services`)
        .then((resp) => (this.services = resp.data));
    },
    fetchData() {
      this.fetchStandardPeriodsForAllTutorServices();
      this.fetchTutorServices();
    },
    selectedServiceChange(event) {
      let value = event.target.value;
      if (!value) {
        this.fetchStandardPeriodsForAllTutorServices();
        return;
      }
      this.$axios
        .get(`${config.apiStart}/api/tutor-services/${value}/standard-periods`)
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
          `${config.apiStart}/api/users/tutors/standard-periods`,
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
