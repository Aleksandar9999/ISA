<template>
    <div  >
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
      <canvas id="appChart"></canvas>
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
</template>

<script>

import Chart from 'chart.js/auto';



export default{
   components: { 
     
   },
   data() {
    return {
      boatOwnerId: this.$route.params.idboatowner,
      appChart: Chart,
      myChart: Chart,
      currentTime: new Date(),
      yearLabels: [
    ((new Date()).getFullYear()- 2).toString(),
    ((new Date()).getFullYear() - 1).toString(),
    ((new Date()).getFullYear()).toString(),
    ((new Date()).getFullYear() + 1).toString(),
      ],

    monthsLabels: [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December',
  ],
    daysLabels:  [],
    labels:  [],
  yearAppSum: [0, 0, 0],
  monthsAppSum: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  daysAppSum: [0, 0, 0, 0],
  data5: [
    10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170,
    180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 300, 310,
  ],
  data1: [
    10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170,
    180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 300, 310,
  ],
  data2:[
    10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170,
    180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 300, 310,
  ],
   };
  },
  methods: {
    loadChartAppointment(){
  },
   addDaysLabels(){
    let labelsDays = [];
    var month = this.currentTime.getMonth();
    var d = new Date(this.currentTime.getFullYear(), month, 0);
    var lastDay = d.getDate();
    console.log(lastDay.toString());
    for (let i = 1; i <= 3; i++) {
      labelsDays.push(String(i * 7));
      console.log(i);
    }
    labelsDays.push(String(lastDay));
    return labelsDays;
  },
  appChartYearly() {
      console.log(this.myChart.data.datasets[0]);
    //this.myChart.data.datasets[0].data = [1,2];
  
    this.myChart.data.datasets[0].labels = ['1','2'];
    
    // this._doctorStatisticsService
    //   .getAppointmentsCountYearly(this.doctorId)
    //   .subscribe((data) => {
    //     this.appChart.data.datasets[0].data = data.yearlySum;
    //     this.appChart.update();
    //   });
    // this.appChart.config.type = 'pie' as ChartType;
    this.myChart.update();
  },
  appChartMonthly() {
    this.myChart.data.labels = this.monthsLabels;
    // this.appChart.config.type = 'bar' as ChartType;
    // this._doctorStatisticsService
    //   .getAppointmentsCountMonthly(this.doctorId)
    //   .subscribe((data) => {
    //     this.appChart.data.datasets[0].data = data.monthlySum;
    //     this.appChart.update();
    //   });
    this.myChart.update();
  },
    appChartWeekly() {
    this.myChart.data.labels = this.daysLabels;
    // this.appChart.config.type = 'bar' as ChartType;
    // this._doctorStatisticsService
    //   .getAppointmentsCountWeekly(this.doctorId)
    //   .subscribe((data) => {
    //     this.appChart.data.datasets[0].data = data.weeklySum;
    //     this.appChart.update();
    //   });
    this.myChart.update();
  },
  

  },
  
  mounted(){
var appChartItem = document.getElementById('appChart').getContext('2d');
 this.myChart = new Chart(appChartItem, {
    type: 'bar',
    data: {
        labels: ['Januar', 'Februar', 'Mart', 'April', 'Maj', 'Jun','Jul','Septembar','Oktobar','Novembar','Decembar'],
        datasets: [{
            label: '# of Votes',
            data: this.data5,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
  });
  this.myChart;
  // this.appChartItem = document.getElementById('appChart');
   //  this.loadChartAppointment();
     console.log(this.data5);
      console.log(this.yearLabels);

     this.daysLabels = this.addDaysLabels();
     this.myChart.data.labels = this.monthsLabels;
     this.myChart.update();
     console.log(this.daysLabels);
     console.log(this.myChart.data);
    }
  
}



</script>


<style>

</style>