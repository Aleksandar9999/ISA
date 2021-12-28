<template lang="">
  <div class="wrapper">
    <section class="content">
      <h2><i class="ico timesheet"></i>TimeSheet</h2>
      <div class="grey-box-wrap">
        <div class="top">
          <a @click="subtractMonth" class="prev"
            ><i class="zmdi zmdi-chevron-left"></i>previous year</a
          >
          <span class="center">{{ headingText }}</span>
          <a @click="addMonth" class="next"
            >next year<i class="zmdi zmdi-chevron-right"></i
          ></a>
        </div>
        <div className="bottom"></div>
      </div>
      <Calendar />
    </section>
  </div>
</template>
<script>
import moment from "moment";
import Calendar from './Calendar.vue'
import "../../../assets/styles/style.css";
export default {
  components: {
    Calendar,
  },
  data() {
    return {
      calendarDays: [],
      startDate: "",
      firstInMonth: "",
      headingText: "",
    };
  },
  mounted() {
    this.firstInMonth = moment().subtract(moment().format("D") - 1, "d");
    this.startDate = this.firstInMonth.clone().subtract(this.firstInMonth.format("d") - 1, "d");
    this.headingText =  this.firstInMonth.format("yyyy");
  },
  methods: {
    addMonth() {
      this.firstInMonth = this.firstInMonth.add(1, "M");
      this.dateCalculation();
    },
    subtractMonth() {
      this.firstInMonth = this.firstInMonth.subtract(1, "M");
      this.dateCalculation();
    },
    dateCalculation() {
      let daysBeforeFirst = this.firstInMonth.format("d") - 1;
      if (this.firstInMonth.format("d") == 0)
        // Ako je prvi nedelja
        daysBeforeFirst = 6;
      this.startDate = this.firstInMonth.clone().subtract(daysBeforeFirst, "d");
      this.headingText =
        this.firstInMonth.format("MMMM") +
        ", " +
        this.firstInMonth.format("yyyy");
    },
  },
};
</script>
