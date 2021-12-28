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
        <CalendarDay v-for="day in calendarDaysTest.slice(0,7)" :key="day.date" :item=day :tdClassName=day.status />
    </tr>
    <tr>
        <CalendarDay v-for="day in calendarDaysTest.slice(7,14)" :key="day.date" :item=day :tdClassName=day.status />
    </tr>
    <tr>
        <CalendarDay v-for="day in calendarDaysTest.slice(14,21)" :key="day.date" :item=day :tdClassName=day.status />
    </tr>
    <tr>
        <CalendarDay v-for="day in calendarDaysTest.slice(21,28)" :key="day.date" :item=day :tdClassName=day.status />
    </tr>
    <tr>
        <CalendarDay v-for="day in calendarDaysTest.slice(28,35)" :key="day.date" :item=day :tdClassName=day.status />
    </tr>
    
  </table>
</template>
<script>
import CalendarDay from "../CalendarItem.vue";
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
      {this.calendarDaysTest.push({
        duration: "Hours: " + 5,
        date: `2021-05-${i}`, 
        },)
        if(i % 3){
          this.calendarDaysTest[i].status='reserved'
        }  
      }
  }
};
</script>
