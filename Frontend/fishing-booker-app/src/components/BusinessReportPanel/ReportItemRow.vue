<template lang="">
  <tr>
    <td>{{ getDate() }}</td>
    <td>{{ report.duration }}</td>
    <td>{{ report.price }}</td>
    <td>{{ report.user.name }} {{ report.user.surname }}</td>
    <td>{{ report.tutorService.name }}</td>
    <td>{{ report.status }}</td>
    <td>{{ getRevenue() }}</td>
  </tr>
</template>
<script>
import moment from "moment";
export default {
  props: ["item"],
  data() {
    return {
      report: {},
      appellant: {},
      appelle: {},
      adminResponse: {},
    };
  },
  methods: {
    getDate() {
      return moment(this.report.start).format("DD-MM-YYYY");
    },
    getRevenue() {
      if ((this.report.status == "PENDING")) return "";
      if ((this.report.status == "CANCELED"))
        return this.report.price * this.report.tutorService.cancelProcentage / 100
      return this.report.price;
    },
  },
  watch: {
    item: {
      immediate: true,
      handler(itemFromProps) {
        if (itemFromProps) {
          this.report = {
            ...itemFromProps,
          };
        }
      },
    },
  },
};
</script>
<style lang=""></style>
