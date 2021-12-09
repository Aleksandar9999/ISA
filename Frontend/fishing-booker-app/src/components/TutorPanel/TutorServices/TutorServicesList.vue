<template>
  <div>
    <w-flex justify-end class="pa3" style="padding-right: 20%">
      <w-button @click="showModalDialog">Add new</w-button>
    </w-flex>
    <CustomTable :dataList=data :headerList="headers" :itemRow="itemRow" @tutorServiceDeleted=deletedService />
    <TutorServiceDialog :show="showDialog" @hideDialog=hideDialogFromDialog :idTutor=this.$route.params.idtutor />
  </div>
</template>
<script>
import axios from "axios";
import CustomTable from "../../CustomTable.vue";
import TutorSericeInfoRow from "./TutorServiceInfoRow.vue";
import config from "../../../configuration/config";
import TutorServiceDialog from "./TutorServiceDialog.vue";
export default {
  components: {
    CustomTable,
    TutorSericeInfoRow,
    TutorServiceDialog,
  },
  data() {
    return {
      showDialog: false,
      itemRow: TutorSericeInfoRow,
      data: [],
      headers: ["ID", "Service", "Max number of persons", "Rules", "Status","More info",""],
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    deletedService(){
      this.fetchData()
      this.$forceUpdate();
    },
    fetchData() {
      axios
        .get(config.apiStart + `/api/users/tutors/${this.$route.params.idtutor}/services`,config.requestHeader)
        .then((resp) => {
          this.data = resp.data;
        });
    },
    showModalDialog() {
      this.showDialog = true;
    },
    hideDialogFromDialog(value) {
      console.log(value)
      this.showDialog = value.dialog;
      if (value.success) { 
        this.fetchData();
      }
    },
  },
};
</script>
<style>
w-button {
  margin-right: 25px;
}
</style>
