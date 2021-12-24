<template lang="">
  <table class="month-table" ref="calendar-table">
    <tr class="head">
      <th><span>monday</span></th>
      <th>tuesday</th>
      <th>wednesday</th>
      <th>thursday</th>
      <th>friday</th>
      <th>saturday</th>
      <th>sunday</th>
    </tr>
    <tr class="mobile-head">
      <th>mon</th>
      <th>tue</th>
      <th>wed</th>
      <th>thu</th>
      <th>fri</th>
      <th>sat</th>
      <th>sun</th>
    </tr>
    <tr>
        <CalendarDay v-for="i in 7" :key="i" :day=calendarDaysTest[i-1] tdClassName='positive previous' />
    </tr>
    <tr>
        <CalendarDay v-for="i in 7" :key="i" :day=calendarDaysTest[7+i-1] />
    </tr>
    <tr>
        <CalendarDay v-for="i in 7" :key="i" :day=calendarDaysTest[2*7+i-1] />
    </tr>
    <tr>
        <CalendarDay v-for="i in 7" :key="i" :day=calendarDaysTest[3*7+i-1] />
    </tr>
    <tr>
        <CalendarDay v-for="i in 7" :key="i" :day=calendarDaysTest[4*7+i-1] />
    </tr>
  </table>
</template>
<script>
import CalendarDay from "./CalendarDay.vue";
export default {
  props: ["calendarDays"],
  components: { CalendarDay },
  data() {
    return {
      totalHours: 0,
      rowsCount: [1, 2, 3, 4, 5],
      calendarDaysTest:[],
      
      calendarRows: [],
    };
  },
  beforeMount() {
      for(let i=0;i<35;i++)
      this.calendarDaysTest.push({
        hours: 5,
        date: `2021-05-${i}`, 
        },)
  },
  mounted() {
      
      console.log(this.calendarDaysTest.length);
  },
  methods: {
      
  },

};
</script>
<style lang=""></style>
