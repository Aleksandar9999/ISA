<template>
 <div class="columns ml-5" style="display:flex; justify-content: center;">
    <div class="col-5 m-5 box" style="width:500px; ">
      <p class="subtitle has-text-centered">Number of reservation per resort</p>
    <Bar
        :chart-options="chartOptions"
        :chart-data="chartData"
        :chart-id="chartId"
        :dataset-id-key="datasetIdKey"
        :plugins="plugins"
        :css-classes="cssClasses"
        :styles="styles"
        :width="width"
        :height="height"
    />
    </div>
    <div class="col-5 m-5 box" style="width:500px; margin-left:5%;">
      <p class="subtitle has-text-centered">Profit for resortowner</p>
      <Bar
          :chart-options="chartOptionsDoughnut"
          :chart-data="chartDataDoughnut"
          :chart-id="chartId"
          :dataset-id-key="datasetIdKey"
          :plugins="plugins"
          :css-classes="cssClasses"
          :styles="styles"
          :width="width"
          :height="height"
      />
        <div class="box" style="margin: 0px 5% 0px 5%">
          <Datepicker  
           placeholder="Click to select date..."
            v-model="dates"
            range
            @selected="getFinances()"></Datepicker>
            <button @click="getFinances()">Get finances</button>
        </div>
      <p class="subtitle has-text-centered mt-5" style="margin-top:5%;"> Total earnings in the period  <br> from: {{startTime.substring(0,10)}} to: {{endTime.substring(0,10)}} is <strong>{{totalEarnings}}€</strong></p>
    </div>
    </div>
</template>

<script>
import { Bar } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'
import axios from "axios";
import config from "../../../configuration/config";

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)


export default{
  name: 'BarChart',
  components: { Bar },
  props: {
    chartId: {
      type: String,
      default: 'bar-chart'
    },
    datasetIdKey: {
      type: String,
      default: 'label'
    },
    width: {
      type: Number,
      default: 400
    },
    height: {
      type: Number,
      default: 400
    },
    cssClasses: {
      default: '',
      type: String
    },
    styles: {
      type: Object,
      default: () => {}
    },
    plugins: {
      type: Object,
      default: () => {}
    }
  },
  data() {
     return {
      user: null,
      totalEarnings: 0,
      dates:[],
      startTime: '2022-01-01 00:00',
      endTime: '2023-01-01 00:00',
      chartData: {
        labels: [ 'Resort' ],
        datasets: [
            {
              label: 'Last Week',
              backgroundColor: '#CAD2C5',
              data: [0]
            },
            {
              label: 'Last Month',
              backgroundColor: '#52796F',
              data: [0]
            },
            {
              label: 'Last Year',
              backgroundColor: '#2F3E46',
              data: [0]
            },
        ]
      },
      chartDataDoughnut: {
        labels: ['Resort'],
        datasets: [
          {
            label: 'Earnings per appointment',
            backgroundColor: ['#52796F'],
            data: [0]
          }
        ]
      },
      chartOptions: {
        responsive: true,
        scales: {
          y: {
            suggestedMax: 15,
            ticks: {
              stepSize: 2,
              max: 1000
            },
          },
        },
      },
      chartOptionsDoughnut: {
        responsive: true,
        maintainAspectRatio: false
      }
    }
  },
  async mounted() {
    this.user = JSON.parse(localStorage.getItem('id'))
    console.log(this.user);
    await this.getNumOfReservations();
    await this.getInitialFinances();
  },

  methods:{
    async getNumOfReservations(){
      const response = await axios.get(`${config.apiStart}/api/getNumberOfReservations/resortowner/${this.user}`)
      if (response.data) {
        console.log(response.data)
        this.chartData.labels = response.data.map(data => data.id)
        this.chartData.datasets[0].data = response.data.map(data => data.reservationNumWeek)
        this.chartData.datasets[1].data = response.data.map(data => data.reservationNumMonth)
        this.chartData.datasets[2].data = response.data.map(data => data.reservationNumYear)
      }
    },
    async getFinances(){
      console.log("lalala");
      let sYear = this.dates[0].getFullYear()
      let sMonth = this.formatDateMonth(new Date(this.dates[0]));
      let sDay = this.formatDateDay(new Date(this.dates[0]));
      let eYear = this.dates[this.dates.length - 1].getFullYear()
      let eMonth = this.formatDateMonth(new Date(this.dates.slice(-1)[0]));
      let eDay = this.formatDateDay(new Date(this.dates.slice(-1)[0]));
      this.startTime =`${sYear}-${sMonth}-${sDay} 00:00`
      this.endTime= `${eYear}-${eMonth}-${eDay} 00:00`
      const response = await axios.post(`${config.apiStart}/api/finances/resortowner`, {
        id: this.user,
        startTime: this.startTime,
        endTime: this.endTime,
      })
      if(response.data){
        console.log(response.data)
        this.chartDataDoughnut.datasets[0].data = response.data.map(data => data.earning)
        this.chartDataDoughnut.labels = response.data.map(data => data.id)
        this.totalEarnings = response.data.reduce((acc, cur) => acc + cur.earning, 0)
      }
    },
    async getInitialFinances(){
      const response = await axios.post(`${config.apiStart}/api/finances/resortowner`, {
        id: this.user,
        startTime: this.startTime,
        endTime: this.endTime,
      })
      if(response.data){
        console.log(response.data)
        this.chartDataDoughnut.datasets[0].data = response.data.map(data => data.earning)
        this.chartDataDoughnut.labels = response.data.map(data => data.id)
        this.totalEarnings = response.data.reduce((acc, cur) => acc + cur.earning, 0)
      }
    },
    formatDateMonth(date) {
      if (date.getMonth() + 1 < 10)
        return `0${date.getMonth() + 1}`
      else
        return `${date.getMonth() + 1}`
    },
    formatDateDay(date) {
      if (date.getDate() < 10)
        return `0${date.getDate()}`
      else
        return `${date.getDate()}`
    },
  }
}
</script>


<style>

</style>