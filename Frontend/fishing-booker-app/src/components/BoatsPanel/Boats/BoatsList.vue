<template>
  <div style="margin-top: 3%">
    <w-flex justify-end class="pa3" style="padding-right: 20%; padding-left: 20%" >
      <span style="padding-right: 5%">
          <w-input type="text" placeholder="Name" name="name" v-model=search.name />
      </span>
      <span style="padding-right: 5%">
          <w-input type="number" placeholder="Max number of perosons" name="username" v-model=search.maxPerson />
      </span>    
      <w-button @click="showModalDialog"  v-if="showBoatOwnerButtonsFunc()" >Add new</w-button> 
    </w-flex>
    
    <BoatsTable
      :dataList=searchList
      :headerList="headers"
      :itemRow="itemRow"
      @boatDeleted="deletedBoat"
    />
    <BoatDialog
      :show="showDialog"
      @hideDialog="hideDialogFromDialog"
      :idBoatOwner="this.$route.params.idboatowner"
    />
  </div>
</template>
<script>
import axios from "axios";
import BoatsTable from "../../BoatsTable.vue";
import BoatInfo from "./BoatInfo.vue";
import config from "../../../configuration/config";
import BoatDialog from "./BoatDialog.vue";
export default {
  components: {
    BoatsTable,
    BoatInfo,
    BoatDialog,
  },
  data() {
    return {
      showDialog: false,
      itemRow: BoatInfo,
      data: [],
      search:{
        name:'',
        maxPerson:''
      },
      headers: [
        "ID",
        "Boat",
        "Max number of persons",
        "Rules",
        "Status",
        "Average grade",
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
					&& item.maxPerson >= this.search.maxPerson
			})
		},
  },
  methods: {
    deletedBoat() {
      this.fetchData();
      this.$forceUpdate();
    },
    fetchData() {
      axios
        .get(
          config.apiStart +
            `/api/users/boatowners/${this.$route.params.idboatowner}/boats`,
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
    showBoatOwnerButtonsFunc(){
      if (localStorage.roles)
        if (localStorage.roles.includes("ROLE_BOATOWNER") && localStorage.id == this.$route.params.idboatowner) {
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