<template lang="">
  <table class="month-table" ref="calendar-table">
    <tr>
        <CalendarItemMonth v-for="month in calendarMonthTest.slice(0,4)" :key="month" :item=month />
    </tr>
    <tr>
        <CalendarItemMonth v-for="month in calendarMonthTest.slice(4,8)" :key="month" :item=month />
    </tr>
    <tr>
        <CalendarItemMonth v-for="month in calendarMonthTest.slice(8,12)" :key="month" :item=month />
    </tr>
  </table>
</template>
<script>
import CalendarItemMonth from "../CalendarItem.vue"
import moment from 'moment'
export default {
  props: ["calendarMonth"],
  components: { CalendarItemMonth },
  data() {
    return {
      totalHours: 0,
      calendarMonthTest:[]
    };
  },
  beforeMount() {
      for(let i=1;i<=12;i++)
        this.calendarMonthTest.push({duration: "Days: " + 5, date: moment(`2021-${i}-01`).format('MMM')},)
       
  }
};
</script>
