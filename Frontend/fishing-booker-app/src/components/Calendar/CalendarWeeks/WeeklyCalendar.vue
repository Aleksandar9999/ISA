<template lang="">
  <div class="wrapper">
    <section class="content">
      <h2><i class="ico timesheet"></i>TimeSheet</h2>
      <div class="grey-box-wrap">
        <div class="top">
          <a href="javascript:;" class="prev">
            <i class="zmdi zmdi-chevron-left"></i>previous week
          </a>
          <span class="center"> February 04 - February 10, 2013 (week 6) </span>
          <a href="javascript:;" class="next">
            next week<i class="zmdi zmdi-chevron-right"></i>
          </a>
        </div>
        <header-days :dateLocal=date ></header-days>
      </div>
      <TasksTable :date="date" />
    </section>
  </div>
</template>
<script>
import HeaderDays from "./HeaderDays.vue";
import TasksTable from "./TasksTable.vue";
export default {
  components: { HeaderDays, TasksTable },
  data() {
    return {
      date: '',
    };
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
