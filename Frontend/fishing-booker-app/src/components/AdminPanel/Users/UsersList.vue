<template lang="">
  <div style="padding-top: 5%">
    <w-flex justify-end class="pa3" style="padding: 0% 20%">
      <w-checkbox v-model="showPending">Show panding</w-checkbox>
      <w-button @click="navigatToRequests" style="margin:0% 0% 0% 5%">Delete requests</w-button>
    </w-flex>
    <CustomTable
      :dataList="pandingUsersList"
      :headerList="headerList"
      :itemRow="itemRowComponent"
    />
  </div>
</template>
<script>
import UserInfoRow from "./UserInfoRow.vue";
import CustomTable from "../../CustomTable.vue";
import config from '../../../configuration/config';
export default {
  components: { UserInfoRow, CustomTable },
  data() {
    return {
      showPending:false,
      itemRowComponent: UserInfoRow,
      headerList: ["ID", "Name", "Surname", "Status","","See profile",""],
      dataList: [],
    };
  },
  computed:{
    pandingUsersList(){
      console.log("PENDING USERS LIST")
      if(!this.dataList) return [];
      if(this.showPending)
        return this.dataList.filter(item=> {return (item.shouldApprove)})
      else{
        return this.dataList;
      }
    }
  },
  methods: {
    navigatToRequests(){
      this.$router.push("/users/delete-requests");
    }

  },
  mounted() {
    this.$axios.get(config.apiStart+"/api/users",config.requestHeader).then(resp=> {this.dataList=resp.data;
    console.log(this.data)
    })
  },
};
</script>
<style scoped></style>
