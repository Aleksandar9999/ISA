<template>
  <div style="margin-top: 3%">
    <w-flex justify-end class="pa3" style="padding-right: 20%; padding-left: 20%" >
      <span style="padding-right: 5%">
          <w-input type="text" placeholder="Name" name="name" v-model=search.name />
      </span>
      <span style="padding-right: 5%">
          <w-input type="number" placeholder="Min number of rooms" name="username" v-model=search.numOfRooms />
      </span>    
      <w-button @click="showModalDialog"  v-if="showResortOwnerButtonsFunc()" >Add new</w-button> 
    </w-flex>
    
    <ResortsTable
      :dataList=searchList
      :headerList="headers"
      :itemRow="itemRow"
      @resortDeleted="deletedResort"
    />
    <ResortDialog
      :show="showDialog"
      @hideDialog="hideDialogFromDialog"
      :idResortOwner="this.$route.params.idresortowner"
    />
  </div>
</template>
<script>
import axios from "axios";
import ResortsTable from "../../ResortsTable.vue";
import ResortInfo from "./ResortInfo.vue";
import config from "../../../configuration/config";
import ResortDialog from "./ResortDialog.vue";
export default {
  components: {
    ResortsTable,
    ResortInfo,
    ResortDialog,
  },
  data() {
    return {
      showDialog: false,
      itemRow: ResortInfo,
      data: [],
      search:{
        name:'',
        numOfRooms:''
      },
      headers: [
        "ID",
        "Resort",
        "Number of rooms",
        "Rules",
        "Status",
        "RATE",
        "More info",
        "Periods",
        "Appointments",
        "Calendar",
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
					&& item.numOfRooms >= this.search.numOfRooms
			})
		},
  },
  methods: {
    deletedResort() {
      this.fetchData();
      this.$forceUpdate();
    },
    fetchData() {
      axios
        .get(
          config.apiStart +
            `/api/users/resortowners/${this.$route.params.idresortowner}/resorts`,
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
    showResortOwnerButtonsFunc(){
      if (localStorage.roles)
        if (localStorage.roles.includes("ROLE_RESORTOWNER") && localStorage.id == this.$route.params.idresortowner) {
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