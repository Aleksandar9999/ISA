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
      <TasksTable :date="date" />
    </section>
  </div>
</template>
<script>
import HeaderDays from "./HeaderDays.vue";
import TasksTable from "./TasksTable.vue";
import moment from 'moment'
export default {
  components: { HeaderDays, TasksTable },
  data() {
    return {
      date: this.$route.params.date,
    };
  },
  methods: {
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
