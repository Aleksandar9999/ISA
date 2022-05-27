<template lang="">
  <div class="wrapper">
    <section class="content" style="margin: 3% 0px 0px 0px">
      <h2><i class="ico timesheet"></i>Calendar</h2>
      <div class="grey-box-wrap">
        <div class="top">
          <a :href="getPreviousWeekHref()" class="prev">
            <i class="zmdi zmdi-chevron-left"></i>previous week
          </a>
          <span class="center"> {{getHeaderDateText()}} </span>
          <a :href="getNextWeekHref()" class="next">
            next week<i class="zmdi zmdi-chevron-right"></i>
          </a>
        </div>
        <header-days ></header-days>
      </div>
      <AppointmentsTable :date="date" @createReportShow=createReportShow />
    </section>
    <AppointmentReportModalDialog :show=appointmentReportModalDialog.show :idAppointment=appointmentReportModalDialog.appointmentId @hideDialog=hideDialogReport />
  </div>
</template>
<script>
import HeaderDays from "./HeaderDays.vue";
import AppointmentsTable from "./AppointmentsTable.vue";
import moment from 'moment'
import AppointmentReportModalDialog from './AppointmentReportModalDialog.vue'
export default {
  components: { HeaderDays, AppointmentsTable,AppointmentReportModalDialog },
  data() {
    return {
      appointmentReportModalDialog:{
        show:false,
        success:false,
        appointmentId:''
      },
      date: this.$route.params.date,
    };
  },
  methods: {
    hideDialogReport(value){
      if(value.success) {
        this.$forceUpdate();
      }
    },
    createReportShow(value){
      this.appointmentReportModalDialog.appointmentId=value.id;
      this.appointmentReportModalDialog.show=true;
    },
    getHeaderDateText(){
      return `${moment(this.date).format("MMMM DD")} - ${moment(this.date).add(7,'days').format("MMMM DD")}`  
    },
    getNextWeekHref(){
      return `${moment(this.date).add(7,'days').format('YYYY-MM-DD')}`
    },
    getPreviousWeekHref(){
      return `${moment(this.date).subtract(7,'days').format('YYYY-MM-DD')}`
    }
  },
  beforeMount() {
    console.log(this.$route.params.date)
    this.data=this.$route.params.date
  },
  mounted() {
    },
};
</script>
<style lang=""></style>
