<template>
  <div>
    <custom-table :dataList=data :headerList=headers :itemRow=row ></custom-table>
  </div>
</template>
<script>
import config from '../../../configuration/config';
import CustomTable from "../../CustomTable.vue";
import RevisionInfoRow from "./RevisionInfoRow.vue";
export default {
  components: {
    CustomTable,
    RevisionInfoRow,
  },
  data() {
    return {
      headers: ["ID", "RATE", "Creator name and surname","owner email","COMMENT", "CLASS", "STATUS", ""],
      data: [],
      row: RevisionInfoRow
    };
  },
  mounted() {
      this.$axios.get(`${config.apiStart}/api/revision`).then((resp)=>this.data=resp.data);
  },
};
</script>
<style></style>
