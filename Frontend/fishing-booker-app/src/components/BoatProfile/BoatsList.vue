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
      @boatDeleted="deletedService"
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
import CustomTable from "../CustomTable.vue";
import BoatInfoRow from "./BoatInfoRow.vue";
import config from "../../configuration/config";
import BoatDialog from "./BoatDialog.vue";
export default {
  components: {
    CustomTable,
    BoatInfoRow,
    BoatDialog,
  },
  data() {
    return {
      showDialog: false,
      itemRow: BoatInfoRow,
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
<style scoped>
#searchtabe{
  margin: 1% 19%;
}
w-button {
  margin-right: 25px;
}
</style>
