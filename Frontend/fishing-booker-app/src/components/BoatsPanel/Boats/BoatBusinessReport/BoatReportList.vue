<template lang="">
  <div style="padding-top: 5%">
    <w-flex v-if="showReport" wrap class="text-center" style="padding: 0 22%">
      <div class="xs3 pa1">
        <div class="white py3">
          <p>
            Total revenue: <b v-if="showAdminReport">{{ report.totalSystemRevenue }}</b>
             <b v-if="!showAdminReport">{{ report.totalOwnerRevenue }}</b>
          </p>
        </div>
      </div>
      
      <div class="xs3 pa1">
        <div class="white py3">
          <p>
            Average appointment price: <b>{{ Math.round(report.avgPriceOfAppointment * 100) / 100 }}</b>
          </p>
        </div>
      </div>

      <div class="xs3 pa1">
        <div class="white py3">
          <p>
            Total number of appointments: <b>{{ report.totalNumberOfAppointments }}</b>
          </p>
        </div>
      </div>

    <div class="xs3 pa1">
        <div class="white py3">
          <p>
            Total number of completed appointments: <b>{{ report.completedAppointments.length }}</b>
          </p>
        </div>
      </div>
      <div class="xs3 pa1">
        <div class="white py3">
          <p>
            Appointments cancelled:
            <b>{{ report.numberOfCancelledAppointments }}</b>
          </p>
        </div>
      </div>

      <div class="xs3 pa1">
        <div class="white py3">
          <p>
            Appointments successful:
            <b>{{ report.numberOfSuccessfulAppointments }}</b>
          </p>
        </div>
      </div>
    </w-flex>

    <w-flex justify-end class="pa3" style="padding: 0% 20%">
      <w-input type="date" v-model="period.startDate"> </w-input>
      <w-input type="date" v-model="period.endDate" :min="period.startDate" > </w-input>
      <w-button @click="getReport">CREATE REPORT</w-button>
    </w-flex>
    <custom-table
      :dataList="data"
      :headerList="headers"
      :itemRow="row"
    ></custom-table>
  </div>
</template>
<script>
import BoatReportItem from "./BoatReportItem.vue";
import BoatsTable from "../../../BoatsTable.vue";
import config from "../../../../configuration/config";
export default {
  components: {
    BoatsTable,
    BoatReportItem,
  },
  data() {
    return {
      headers: [
       "DATE",
            "DURATION",
            "PRICE",
            "CLIENT",
            "BOAT",
            "STATUS",
            "SERVICE OWNER REVENUE",
            "SYSTEM REVENUE",
      ],
      data: [],
      row: BoatReportItem,
      period: {
        startDate: "",
        endDate: "",
      },
      showAdminReport: false,
      showReport: false,
      report: {
        sumPrice: 0,
        numberOfCancelledAppointments: 0,
        numberOfSuccessfulAppointments: 0,
        numberOfAppointments: 0,

      },
    };
  },
  mounted() {
    if (localStorage.roles.includes("ROLE_ADMIN")){
      this.headers.push("Owner")
      this.showAdminReport=true
    }
  },
  methods: {
    getReport() {
      if (!localStorage.roles) {alert("NO ACCESS");return;}
      let requestApi=''
      if (localStorage.roles.includes("ROLE_BOATOWNER")) requestApi="api/business-report/boats/me" 
      if (localStorage.roles.includes("ROLE_ADMIN")) requestApi="api/business-report" 
      this.$axios
            .get(
              `${config.apiStart}/${requestApi}?startDate=${this.period.startDate}&endDate=${this.period.endDate}`
            )
            .then((resp) => {
              console.log(resp.data);
              this.data = resp.data.completedAppointments;
              this.report = { ...this.report, ...resp.data };
              this.showReport = true;
            });  
      
    
  }},
};
</script>
