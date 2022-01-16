<template lang="">
  <table class="month-table" ref="calendar-table">
    <tr>
        <CalendarItemMonth v-for="(month,index) in calendarMonthTest.slice(0,4)" :key="month" :item=month :href=getMonthHref(index) />
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

export default {
  props: ["months"],
  components: { CalendarItemMonth },
  data() {
    return {
      totalHours: 0,
      calendarMonthTest:[]
    };
  },
  methods: {
    getMonthHref(index){
      return `${this.$route.params.year}/month/${index+1}`
    }
  },
  watch:{
    months:{
      immediate:true,
      handler(prop){
        console.log("OCITAO SAM IZMIJENU");
        if(prop)
          this.calendarMonthTest=prop
      }
    }
  }
};
</script>
