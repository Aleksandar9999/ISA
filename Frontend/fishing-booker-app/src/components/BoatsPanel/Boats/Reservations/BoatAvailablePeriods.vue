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
    <BoatsTable :dataList="data" :headerList="headers" :itemRow="itemRow" />
  </div>
</template>
<script>
import BoatsTable from "../../../BoatsTable.vue";
import AvailablePeriodRow from "./AvailablePeriodRow.vue";
import config from "../../../../configuration/config";
export default {
  components: {
    BoatsTable,
    AvailablePeriodRow,
  },
  data() {
    return {
      data: [],
      headers: ["START DATE", "END DATE", ""],
      itemRow: AvailablePeriodRow,
      boats: [],
      newPeriod: {
        startDate: "",
        startTime: "",
        endDate: "",
        endTime: "",
      },
      selectedBoat: "",
      idboat: this.$route.params.idboat,
    };
  },
  mounted() {
    console.log("ROUTE ID BOAT:" + this.$route.params.idboat);
    this.fetchData();
  },
  methods: {
    fetchStandardPeriodsForBoat() {
      this.$axios.get(`${config.apiStart}/api/boats/${this.$route.params.idboat}/all-standard-periods`).then((resp) => {
        this.data = resp.data;
        console.log(this.data);
      });
    },
    fetchData() {
      this.fetchStandardPeriodsForBoat();
    },
    selectedServiceChange(event) {
      let value = event.target.value;
      if (!value) {
        this.fetchStandardPeriodsForBoat();
        return;
      }
      this.$axios
        .get(`${config.apiStart}/api/boats/${value}/all-standard-periods`)
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
          `${config.apiStart}/api/boats/${this.$route.params.idboat}/standard-periods`,
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
