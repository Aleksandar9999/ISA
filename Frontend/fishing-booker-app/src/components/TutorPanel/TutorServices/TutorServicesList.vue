<template>
  <div>
    <table id="searchtabe" border="0" cellspacing="0">
      <tr>
        <td>
          <div class="field">
            <input type="text" placeholder="Name" name="name" v-model=search.name />
          </div>
        </td>
        <td>
          <div class="field">
            <input type="number" placeholder="Max number of perosons" name="username" v-model=search.maxPerson />
          </div>
        </td>
      </tr>
    </table>
    <w-flex justify-end class="pa3" style="padding-right: 20%">
      <w-button @click="showModalDialog">Add new</w-button>
    </w-flex>
    <CustomTable
      :dataList=searchList
      :headerList="headers"
      :itemRow="itemRow"
      @tutorServiceDeleted="deletedService"
    />
    <TutorServiceDialog
      :show="showDialog"
      @hideDialog="hideDialogFromDialog"
      :idTutor="this.$route.params.idtutor"
    />
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
      search:{
        name:'',
        maxPerson:''
      },
      headers: [
        "ID",
        "Service",
        "Max number of persons",
        "Rules",
        "Status",
        "More info",
        "",
      ],
    };
  },
  mounted() {
    this.fetchData();
  },
  computed:{
    searchList() {
			if (this.data == null) return;
			return this.data.filter(item => {
				return item.name.toLowerCase().includes(this.search.name.toLowerCase())
					&& item.maxPerson >= this.search.maxPerson
			})
		},
  },
  methods: {
    deletedService() {
      this.fetchData();
      this.$forceUpdate();
    },
    fetchData() {
      axios
        .get(
          config.apiStart +
            `/api/users/tutors/${this.$route.params.idtutor}/services`,
          config.requestHeader
        )
        .then((resp) => {
          this.data = resp.data;
        });
    },
    showModalDialog() {
      this.showDialog = true;
    },
    hideDialogFromDialog(value) {
      console.log(value);
      this.showDialog = value.dialog;
      if (value.success) {
        this.fetchData();
      }
    },
  },
};
</script>
<style>
#searchtabe{
  margin: 1% 19%;
}
w-button {
  margin-right: 25px;
}
</style>
