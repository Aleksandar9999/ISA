<template lang="">
    <div>
         <custom-table :dataList=data :headerList=headers :itemRow=row ></custom-table>
    </div>
</template>
<script>
import config from '../../../configuration/config';
import CustomTable from "../../CustomTable.vue";
import ComplaintInfoRow from "./ComplaintInfoRow.vue"
export default {
    components: {
    CustomTable,
    ComplaintInfoRow,
  },
  data() {
      return {
          headers:["ID","Appellant","Appelle","TEXT","ADMIN RESPONSE",""],
          data:[],
          row: ComplaintInfoRow
      }
  },
  mounted() {
      this.$axios.get(`${config.apiStart}/objections`).then(resp=>{
          this.data=resp.data
          console.log("ISPIS COMPLAINT");
          console.log(this.data)
      })
  },
}
</script>
<style lang="">
    
</style>