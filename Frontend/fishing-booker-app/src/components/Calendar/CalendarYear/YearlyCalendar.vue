<template lang="">
  <div class="wrapper" style="padding: 3% 0px 0px 0px">
    <section class="content">
      <h2><i class="ico timesheet"></i>Year calendar</h2>
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
      <Calendar :months="calendarMonths" />
    </section>
  </div>
</template>
<script>
import moment from "moment";
import Calendar from "./Calendar.vue";
import "../../../assets/styles/style.css";

import config from "../../../configuration/config";
export default {
  components: {
    Calendar,
  },
  data() {
    return {
      calendarMonths: [],
      startDate: "",
      firstInYear: "",
      headingText: "",
    };
  },
  mounted() {
    this.fetchMonths();
  },
  methods: {
    fetchMonths() {
      let firstDate = `${this.$route.params.year}-01-01`;
      this.firstInYear = moment(firstDate);
      this.headingText = this.firstInYear.format("yyyy");
      this.createRequest(this.$route.params.year);
    },
    createRequest(year) {
      let role = "tutor";
      let firstDate = `${year}-01-01`;
      let lastDate = `${year}-12-31`;
      this.$axios
        .get(
          `${
            config.apiStart
          }/api/appointments/${role}/calendar/year?startDate=${firstDate}&endDate=${lastDate}`
        )
        .then((resp) => {
          this.calendarMonths = resp.data;
        });
    },
    addMonth() {
      this.firstInYear = this.firstInYear.add(1, "Y");
      this.dateCalculation();
    },
    subtractMonth() {
      this.firstInYear = this.firstInYear.subtract(1, "Y");
      this.dateCalculation();
    },
    dateCalculation() {
      this.headingText = this.firstInYear.format("YYYY");
      this.$router.push(`./${this.headingText}`)
      this.createRequest(this.headingText)
    },
  },
};
</script>
<style scoped>
.wrapper .content{
  box-shadow: 10px 10px 8px 10px #888888;
}
</style>