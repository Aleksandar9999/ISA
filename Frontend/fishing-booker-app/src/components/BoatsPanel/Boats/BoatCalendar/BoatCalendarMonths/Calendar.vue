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
      <CalendarDay
        v-for="(day, index) in calendarDays.slice(0, 7)"
        :key="day.date"
        :item="day"
        :tdClassName="day.status"
        :discountOfferItem="calendarDiscountDaysLocal[index]"
        :standardPeriodItem=calendarStandardPeriodsDaysLocal[index]
      />
    </tr>
    <tr>
      <CalendarDay
        v-for="(day, index) in calendarDaysLocal.slice(7, 14)"
        :key="day.date"
        :item="day"
        :tdClassName="day.status"
        :discountOfferItem="calendarDiscountDaysLocal[7 + index]"
        :standardPeriodItem=calendarStandardPeriodsDaysLocal[7+index]
      />
    </tr>
    <tr>
      <CalendarDay
        v-for="(day, index) in calendarDaysLocal.slice(14, 21)"
        :key="day.date"
        :item="day"
        :tdClassName="day.status"
        :discountOfferItem="calendarDiscountDaysLocal[14 + index]"
        
        :standardPeriodItem=calendarStandardPeriodsDaysLocal[14+index]
      />
    </tr>
    <tr>
      <CalendarDay
        v-for="(day, index) in calendarDaysLocal.slice(21, 28)"
        :key="day.date"
        :item="day"
        :tdClassName="day.status"
        :discountOfferItem="calendarDiscountDaysLocal[21 + index]"
        
        :standardPeriodItem=calendarStandardPeriodsDaysLocal[21+index]
      />
    </tr>
    <tr>
      <CalendarDay
        v-for="(day, index) in calendarDaysLocal.slice(28, 35)"
        :key="day.date"
        :item="day"
        :tdClassName="day.status"
        :discountOfferItem="calendarDiscountDaysLocal[28 + index]"
        
        :standardPeriodItem=calendarStandardPeriodsDaysLocal[28+index]
      />
    </tr>
  </table>
</template>
<script>
import CalendarDay from "../BoatCalendarItem.vue";
export default {
  props: ["calendarDays", "discountDays", "standardPeriods"],
  components: { CalendarDay },
  data() {
    return {
      totalHours: 0,
      calendarDaysLocal: [],
      calendarDiscountDaysLocal: [],
      calendarStandardPeriodsDaysLocal: [],
    };
  },
  methods: {
  },
  watch: {
    calendarDays: {
      immediate: true,
      handler(prop) {
        if (prop) this.calendarDaysLocal = prop;
      },
    },
    discountDays: {
      immediate: true,
      handler(prop) {
        if (prop) {
          this.calendarDiscountDaysLocal = prop;
        }
      },
    },
    standardPeriods: {
      immediate: true,
      handler(prop) {
        if (prop) {
          this.calendarStandardPeriodsDaysLocal = prop;
        }
      },
    },
  },
};
</script>
