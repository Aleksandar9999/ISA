<template lang="">
  <table className="default-table">
    <tr>
      <th>Start date</th>
      <th>Duration</th>
      <th>Price</th>
      <th>Resort:</th>
      <th>User name and surname:</th>
      <th></th>
    </tr>
    <AppointmentItem
      v-for="appointment in appointments"
      :key="appointment"
      :item="appointment"
      @createReportShow="createReportShow"
    />
  </table>
</template>
<script>
import config from "../../../../../configuration/config";
import AppointmentItem from "./AppointmentItem.vue";
import moment from "moment";
export default {
  props: ["date"],
  data() {
    return {
      role: "resort",
      startDate: this.$route.params.date,
      appointments: [],
      idresort: this.$route.params.idresort,
    };
  },
  components: { AppointmentItem },
  methods: {
    createReportShow(value) {
      this.$emit("createReportShow", value);
    },
  },
  mounted() {
    this.$axios
      .get(
        `${config.apiStart}/api/appointments/${
          this.role
        }/${this.$route.params.idresort}/calendar/week?startDate=${moment(this.startDate).format(
          "YYYY-MM-DD"
        )}`
      )
      .then((resp) => {
        this.appointments = resp.data;
        console.log(resp.data);
      });
  },
};
</script>
