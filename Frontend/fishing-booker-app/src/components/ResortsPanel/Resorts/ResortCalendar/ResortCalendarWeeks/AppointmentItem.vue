<template lang="">
  <tr>
    <td>
      <p>{{ getStartDate() }}</p>
    </td>
    <td>
      <p>{{ item.duration }} days</p>
    </td>
    <td>
      <p>{{ item.price }}</p>
    </td>
    <td>
      <p>{{ item.resort.name }}</p>
    </td>
    <td>
      <p>{{ item.user.name }} {{ item.user.surname }}</p>
    </td>
    <td v-if="showReportButton">
      <w-button @click="createReport" bg-color="success">
        Create report
      </w-button>
    </td>
  </tr>
</template>
<script>
import moment from "moment";
import config from "../../../../../configuration/config";
export default {
  props: ["item"],
  data() {
    return {
      showReportButton: false,
      task: {},
    };
  },
  mounted() {
    this.$axios
      .get(
        `${config.apiStart}/api/resort/appointment-report/appointment/${this.item.id}`
      )
      .then(() => (this.showReportButton = false))
      .catch(() => (this.showReportButton = true));
    console.log(this.item);
  },
  methods: {
    getStartDate() {
      return moment(this.item.period.startDate).format("MMM DD, YYYY");
    },
    createReport() {
      this.$emit("createReportShow", this.item);
    },
  },
};
</script>
