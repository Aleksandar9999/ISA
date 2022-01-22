<template lang="">
  <div style="padding-top: 5%">
    <w-flex justify-end class="pa3" style="padding: 0% 20%">
      <w-checkbox v-model="showPending">Show panding</w-checkbox>
    </w-flex>
    <CustomTable
      :dataList="pandingUsersList"
      :headerList="headerList"
      :itemRow="itemRowComponent"
    />
  </div>
</template>
<script>
import DeleteRequestRow from "./DeleteRequestRow.vue";
import CustomTable from "../../../CustomTable.vue";
import config from '../../../../configuration/config';
export default {
  components: { DeleteRequestRow, CustomTable },
  data() {
    return {
      showPending:false,
      itemRowComponent: DeleteRequestRow,
      headerList: ["ID", "REASON", "SHOW USER INFO", "STATUS","",""],
      dataList: [],
    };
  },
  computed:{
    pandingUsersList(){
      if(!this.dataList) return [];
      if(this.showPending)
        return this.dataList.filter(item=> {return (item.requestStatus=='PENDING')})
      else{
        return this.dataList;
      }
    }
  },
  mounted() {
    this.$axios.get(`${config.apiStart}/api/delete-request`).then(resp=> {this.dataList=resp.data;
    console.log(this.data)
    })
  },
};
</script>
<style scoped></style>
