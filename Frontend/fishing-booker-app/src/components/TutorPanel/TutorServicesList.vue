<template>
  <div>
    <CustomTable
      :dataList=data
      :headerList=headers
      :itemRow=itemRow
    />
  </div>
</template>
<script>
import axios from "axios";
import CustomTable from "../CustomTable.vue";
import TutorSericeInfoRow from "./TutorServiceInfoRow.vue";
import config from "../../configuration/config";
export default {
  components: {
    CustomTable,
    TutorSericeInfoRow,
  },
  data() {
    return {
      itemRow: TutorSericeInfoRow,
      data: [],
      headers: ["ID", "Service", "Max number of persons", "Rules", "Page"],
    };
  },
  mounted() {
    axios.get(config.apiStart + "/api/users/tutors/4/services").then((resp) => {
      this.data = resp.data;
      console.log(this.data);
    });
  },
};
</script>
<style lang=""></style>
