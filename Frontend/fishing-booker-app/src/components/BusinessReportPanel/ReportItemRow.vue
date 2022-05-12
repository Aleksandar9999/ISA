<template lang="">
  <tr>
    <td>{{ getDate() }}</td>
    <td><p>{{ report.appointment.duration }} days</p></td>
    <td><p>{{ report.payedPrice }}</p></td>
    <td><p v-if="report.appointment.user"> {{ report.appointment.user.name }} {{ report.appointment.user.surname }}</p></td>
    <td><p v-if="report.appointment.tutorService">{{ report.appointment.tutorService.name }}</p></td>
    <td>{{ report.appointment.status }}</td>
    <td v-if="report.appointment.status=='SUCCESSFUL'" ><p>{{ Math.round(report.ownerRevenue * 100) / 100 }}</p></td>
    <td v-if="report.appointment.status=='CANCELED'" ><p  >{{ Math.round(report.canceledAppointmentOwnerRevenue * 100) / 100 }}</p></td>
    <td ><p > {{ report.systemRevenue }}</p></td>
    <td><p v-if="showAdminReport"> {{ report.appointment.owner.name }} {{ report.appointment.owner.surname }}</p></td>
    
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
      showAdminReport:false
    };
  },
  mounted() {
    this.showAdminReport=localStorage.roles.includes("ROLE_ADMIN")
   
  },
  methods: {
    getDate() {
      return moment(this.report.appointment.period.startDate).format("DD-MM-YYYY");
    },
    getRevenue() {
       if(!this.showAdminReport){

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
