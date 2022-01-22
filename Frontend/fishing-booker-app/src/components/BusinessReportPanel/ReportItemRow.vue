<template lang="">
  <tr>
    <td>{{ getDate() }}</td>
    <td><p>{{ report.duration }}</p></td>
    <td><p>{{ report.price }}</p></td>
    <td><p v-if="report.user"> {{ report.user.name }} {{ report.user.surname }}</p></td>
    <td><p v-if="report.tutorService">{{ report.tutorService.name }}</p></td>
    <td>{{ report.status }}</td>
    <td ><p v-if="!showAdminProps">{{ getRevenue() }}</p></td>
    <td ><p v-if="showAdminProps"> {{ report.type }}</p></td>
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
      showAdminProps:false
    };
  },
  mounted() {
    this.showAdminReport()
  },
  methods: {
    showAdminReport(){
      if (localStorage.roles.includes("ROLE_ADMIN")) {
          this.showAdminProps=true;
      }
    },
    getDate() {
      return moment(this.report.start).format("DD-MM-YYYY");
    },
    getRevenue() {
      this.showAdminReport();
       if(!this.showAdminProps){

      if ((this.report.status == "PENDING")) return 0;
      if ((this.report.status == "CANCELED"))
        return this.report.price * this.report.tutorService.cancelProcentage / 100
      
      return this.report.price;
       }
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
