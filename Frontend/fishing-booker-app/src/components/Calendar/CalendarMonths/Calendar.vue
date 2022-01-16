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
        <CalendarDay v-for="day in calendarDays.slice(0,7)" :key="day.date" :item=day :tdClassName=day.status />
    </tr>
    <tr>
        <CalendarDay v-for="day in calendarDaysLocal.slice(7,14)" :key="day.date" :item=day :tdClassName=day.status />
    </tr>
    <tr>
        <CalendarDay v-for="day in calendarDaysLocal.slice(14,21)" :key="day.date" :item=day :tdClassName=day.status />
    </tr>
    <tr>
        <CalendarDay v-for="day in calendarDaysLocal.slice(21,28)" :key="day.date" :item=day :tdClassName=day.status />
    </tr>
    <tr>
        <CalendarDay v-for="day in calendarDaysLocal.slice(28,35)" :key="day.date" :item=day :tdClassName=day.status />
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
      calendarDaysLocal:[]
    };
  },
  watch:{
    calendarDays:{
      immediate:true,
      handler(prop){
        console.log("IMA NEKIH VALJDA")
        console.log(prop)

        if(prop)
          this.calendarDaysLocal=prop
      }
    }
  }
};
</script>
