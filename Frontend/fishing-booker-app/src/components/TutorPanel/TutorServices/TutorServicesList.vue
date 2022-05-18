<template>
  <div style="margin-top: 3%">
    <w-flex justify-end class="pa3" style="padding-right: 20%; padding-left: 20%" >
      <span style="padding-right: 5%">
          <w-input type="text" placeholder="Name" name="name" v-model=search.name />
      </span>
      <span style="padding-right: 5%">
          <w-input type="number" placeholder="Max number of perosons" name="username" v-model=search.maxPerson />
      </span>    
      <w-button @click="showModalDialog"  v-if="showTutorButtonsFunc()" >Add new</w-button> 
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
        "RATE",
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
          console.log(resp.data)
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
    showTutorButtonsFunc(){
      if (localStorage.roles)
        if (localStorage.roles.includes("ROLE_TUTOR") && localStorage.id == this.$route.params.idtutor) {
          return true;
        }return false;
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
