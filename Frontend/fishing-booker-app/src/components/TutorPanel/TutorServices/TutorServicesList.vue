<template>
  <div>
    <w-flex justify-end class="pa3" style="padding-right: 20%">
      <w-button @click="showModalDialog">Add new</w-button>
    </w-flex>
    <CustomTable :dataList="data" :headerList="headers" :itemRow="itemRow" />
    <TutorServiceDialog :show="showDialog" @hideDialog="hideDialogFromDialog" />
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
      token:"eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzcHJpbmctc2VjdXJpdHktZXhhbXBsZSIsInN1YiI6ImRyYWdvT3Jhc2FuaW5AZ21haWwuY29tIiwiYXVkIjoid2ViIiwiaWF0IjoxNjM4OTE5NDI5LCJleHAiOjE2Mzg5MjEyMjl9._sgOGos3QS0LAzrvQxUD4Zth3A2dFI1N2kToZO_IbSwfQiIlMBU8ZqPv4bvZduasf5jR7hY3P5JrQSgzSr29WA"
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      axios
        .get(config.apiStart + "/api/users/tutors/"+this.$route.params.idtutor+"/services/",{headers:{
          Authorization: "Bearer " + this.token
        }})
        .then((resp) => {
          this.data = resp.data;
        });
    },
    showModalDialog() {
      this.showDialog = true;
    },
    hideDialogFromDialog(value) {
      this.showDialog = value.dialog;
      if (value.success) { this.fetchData();
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
