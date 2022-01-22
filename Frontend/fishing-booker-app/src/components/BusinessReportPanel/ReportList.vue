<template lang="">
  <div style="padding-top: 5%">
    <w-flex v-if="showReport" wrap class="text-center" style="padding: 0 22%">
      <div class="xs3 pa1">
        <div class="white py3">
          <p>
            Total revenue: <b>{{ report.revenue }}</b>
          </p>
        </div>
      </div>

      <div class="xs3 pa1">
        <div class="white py3">
          <p>
            Total appointments: <b>{{ report.numberOfAppointments }}</b>
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
      <w-input type="date" v-model="period.endDate"> </w-input>
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
import ReportItemRow from "./ReportItemRow.vue";
import CustomTable from "../CustomTable.vue";
import config from "../../configuration/config";
export default {
  components: {
    CustomTable,
    ReportItemRow,
  },
  data() {
    return {
      headers: [
        "DATE",
        "DURATION",
        "PRICE",
        "CLIENT",
        "SERVICE",
        "STATUS",
        "REVENUE",""
      ],
      data: [],
      row: ReportItemRow,
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
    if (localStorage.roles.includes("ROLE_ADMIN"))
    this.headers = [
            "DATE",
            "DURATION",
            "PRICE",
            "CLIENT",
            "SERVICE",
            "STATUS",
            "TYPE",""
          ]
  },
  methods: {
    getReport() {
      if (localStorage.roles) {
        if (localStorage.roles.includes("ROLE_TUTOR")) {
          this.$axios
            .get(
              `${config.apiStart}/api/business-report/tutor/appointments/me?startDate=${this.period.startDate}&endDate=${this.period.endDate}`
            )
            .then((resp) => {
              this.data = resp.data.appointments;
              this.report = { ...this.report, ...resp.data };
              this.showReport = true;
            });
        } else if(localStorage.roles.includes("ROLE_ADMIN")) {
          
            this.$axios
              .get(
                `${config.apiStart}/api/business-report/appointments?startDate=${this.period.startDate}&endDate=${this.period.endDate}`
              )
              .then((resp) => {
                this.data = resp.data.appointments;
                this.report = { ...this.report, ...resp.data };
                this.showReport = true;
              });
        }
      } else {
        alert("NO ACCESS");
      }
    },
  },
};
</script>
