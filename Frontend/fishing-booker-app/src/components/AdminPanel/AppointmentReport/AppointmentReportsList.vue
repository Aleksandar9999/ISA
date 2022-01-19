<template lang="">
    <div>
         <custom-table :dataList=data :headerList=headers :itemRow=row ></custom-table>
    </div>
</template>
<script>
import config from '../../../configuration/config';
import CustomTable from "../../CustomTable.vue";
import AppointmentReportInfoRow from './AppointmentReportInfoRow.vue'
export default {
    components: {
    CustomTable,
    AppointmentReportInfoRow,
  },
  data() {
      return {
          headers:["ID","COMMENT","TYPE","STATUS"],
          data:[],
          row: AppointmentReportInfoRow
      }
  },
  mounted() {
      this.$axios.get(`${config.apiStart}/api/appointment-report`).then(resp=>{
          this.data=resp.data
          console.log(this.data);
      })
  },
}
</script>