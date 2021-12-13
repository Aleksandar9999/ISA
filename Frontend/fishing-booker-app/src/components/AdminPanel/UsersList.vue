<template lang="">
  <div>
    <CustomTable
      :dataList="dataList"
      :headerList="headerList"
      :itemRow="itemRowComponent"
    />
  </div>
</template>
<script>
import UserInfoRow from "./UserInfoRow.vue";
import CustomTable from "../CustomTable.vue";
import config from '../../configuration/config';
import axios from "axios";
export default {
  components: { UserInfoRow, CustomTable },
  data() {
    return {
      itemRowComponent: UserInfoRow,
      headerList: ["ID", "Name", "Surname", "Status","See profile"],
      dataList: [],
    };
  },
  mounted() {
    axios.get(config.apiStart+"/api/users",config.requestHeader).then(resp=> {this.dataList=resp.data;
    console.log(this.data)
    })
  },
};
</script>
<style scoped></style>
