<template>
  <div class="app">
     <div
      class="card mx-auto"
      style="
        width: 400px;
        margin-top: 20px;
        margin-right: 20px;
        margin-left: 40px;
        margin-bottom: 20px;
      "
    >
    <apexcharts  type="bar" :options="chartOptions" :series="series"></apexcharts>
    <div>
      <button @click="updateChart">Update!</button>
      <div>
        <button @click="appChartYearly()" mat-button color="primary">
          Yearly
        </button>
        <button @click="appChartMonthly()" mat-button color="primary">
          Monthly
        </button>
        <button @click="appChartWeekly()" mat-button color="primary">
          Weekly
        </button>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import VueApexCharts from 'vue3-apexcharts'

export default {
  name: 'Chart',
  components: {
    apexcharts: VueApexCharts,
  },
  data: function() {
      return {
        
    financeBox :false,
    reservationsBox: true,
    yearBox: true,
    monthBox: false,
    weekBox: false,
    user: {},
    reservations: [],
    startDate : new Date(),
    selectedMonth : "January",
    selectedYear : "2022",
    dataMonth:'',
    dataYear: '',
    dataWeek: '',
    months:["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"] ,
    years:["2021", "2022", "2023"],
    boats: undefined,
    startCountPeriod :new Date(),
    endCountPeriod : new Date(),

        chartOptions: {
          chart: {
            id: 'vuechart-example',
          },
          xaxis: {
            categories: [1,2,3,4,5] ,
          },
        },
        series: [{
          name: 'series-1',
          data: [30, 40, 45, 50, 49, 60, 70, 81]
        }]
      }
    },
    methods: {
      updateChart() {
        const max = 90;
        const min = 20;
        const newData = this.series[0].data.map(() => {
          return Math.floor(Math.random() * (max - min + 1)) + min
        })
        
        // In the same way, update the series option
        this.series = [{
          data: newData
        }]
      }
    }
}
</script>
<style scoped>
  button {
    background: #26E6A4;
    border: 0;
    font-size: 16px;
    color: '#fff';
    padding: 10px;
    margin-left: 28px;
  }
</style>
