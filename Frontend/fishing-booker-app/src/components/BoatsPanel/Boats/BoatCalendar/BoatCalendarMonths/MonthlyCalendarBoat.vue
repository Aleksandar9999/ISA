<template lang="">
  <div class="wrapper">
    <section class="content" style="margin: 3% 0px 0px 0px">
      <h2><i class="ico timesheet"></i>Calendar</h2>
      <div class="grey-box-wrap">
        <div class="top">
          <a @click="subtractMonth" class="prev"
            ><i class="zmdi zmdi-chevron-left"></i>previous month</a
          >
          <span class="center">{{ headingText }}</span>
          <a @click="addMonth" class="next"
            >next month<i class="zmdi zmdi-chevron-right"></i
          ></a>
        </div>
        <div className="bottom"></div>
      </div>
      <Calendar :calendarDays="calendarDays" :discountDays=calendarDaysDiscountOffers :standardPeriods=calendarDaysStandardPeriods />
    </section>
  </div>
</template>
<script>
import moment from "moment";
import Calendar from "./Calendar.vue";
import "../../../../../assets/styles/style.css";
import config from "../../../../../configuration/config";
export default {
  components: {
    Calendar,
  },
  data() {
    return {
      calendarDays: [],
      calendarDaysDiscountOffers:[],
      calendarDaysStandardPeriods:[],
      startDate: "",
      firstInMonth: "",
      headingText: "",
    };
  },
  mounted() {
    let first = `${this.$route.params.year}-${this.$route.params.month}-01`
    this.firstInMonth = moment(first);
    let daysBeforeFirst = this.firstInMonth.format("d") - 1;
    if (this.firstInMonth.format("d") == 0) daysBeforeFirst = 6;
    this.startDate = this.firstInMonth.clone().subtract(daysBeforeFirst, "d");
    this.headingText = `${this.firstInMonth.format(
      "MMMM"
    )}, ${this.firstInMonth.format("yyyy")}`;
    this.createRequest();
  },
  methods: {
    createRequest() {
      console.log("KREIRAJ ZAHTIJEV");
      console.log(this.startDate.format("YYYY-MM-DD"));
      let role = "boat";
      let boats="boats";
      let startDateQuery=this.startDate.format("YYYY-MM-DD");
      let endDateQuery=this.startDate.add(35, "d").format("YYYY-MM-DD");
      this.$axios.get(`${config.apiStart}/api/appointments/${role}/${this.$route.params.idboat}/calendar/month?startDate=${startDateQuery}&endDate=${endDateQuery}`).then(resp=>{
          this.calendarDays=resp.data
        });

      this.$axios.get(`${config.apiStart}/api/${boats}/discount-offers?startDate=${startDateQuery}&endDate=${endDateQuery}`).then(resp=>{
          this.calendarDaysDiscountOffers=resp.data;
        });
        this.$axios.get(`${config.apiStart}/api/${boats}/standard-periods/me?startDate=${startDateQuery}&endDate=${endDateQuery}`).then(resp=>{
          this.calendarDaysStandardPeriods=resp.data;
        });
        
    },
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
      if (this.firstInMonth.format("d") == 0) daysBeforeFirst = 6;
      this.startDate = this.firstInMonth.clone().subtract(daysBeforeFirst, "d");
      this.headingText =
        this.firstInMonth.format("MMMM") +
        ", " +
        this.firstInMonth.format("yyyy");
      this.createRequest()
    },
  },
};
</script>
