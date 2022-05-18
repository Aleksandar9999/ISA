<template lang="">
  <table className="default-table">
    <tr>
      <th>Start date</th>
      <th>Duration</th>
      <th>Price</th>
      <th>Service:</th>
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
import config from "../../../configuration/config";
import AppointmentItem from "./AppointmentItem.vue";
import moment from "moment";
export default {
  props: ["date"],
  data() {
    return {
      role: "tutor",
      startDate: this.$route.params.date,
      appointments: [],
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
        }/calendar/week?startDate=${moment(this.startDate).format(
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
