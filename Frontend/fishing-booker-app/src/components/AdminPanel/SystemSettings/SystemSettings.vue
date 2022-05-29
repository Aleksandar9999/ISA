<template>
<div>
  <!-- <div id="main-form" style="margin: 5% 20% 5% 20%;">
    <w-form>
      <w-flex justify-space-between class="pa3">
        <div class="box">
          <p style="color: black">Active system procentage</p>
        </div>
        <div class="box">
          <p style="color: black">{{ startDateString }}</p>
        </div>
        <div class="box">
          <w-input type="number" v-model="systemData.procentage" />
        </div>
      </w-flex>
      <div class="text-right mt6">
        <w-button type="submit" @click="save"> Save </w-button>
      </div>
    </w-form>
  </div> -->
  <user-category-settings style="margin: 5% 20% 5% 20%;" />
  <points-settings />
</div>
</template>
<script>
import axios from "axios";
import config from "../../../configuration/config";
import moment from "moment";
import UserCategorySettings from './UserCategoriesSettings.vue'
import PointsSettings from './PointsSettings.vue'
export default {
  components:{
    UserCategorySettings,
    PointsSettings
  },
  data() {
    return {
      systemData: {},
      startDateString: "",
    };
  },
  mounted() {
    axios
      .get(config.apiStart + "/api/system-data/procentage", config.requestHeader)
      .then((resp) => {
        resp.data.forEach((element) => {
          if (!element.valid.endDate) this.systemData = element;
        });
        this.startDateString =
          "Start date: " +
          moment(this.systemData.valid.startDate)
            .format("DD-MM-YYYY")
            .toString();
      });
  },
  methods: {
    save() {
      axios
        .post(
          config.apiStart + "/api/system-data/procentage",
          this.systemData,
          config.requestHeader
        )
        .then((resp) => {
          this.startDateString =
            "Start date: " +
            moment(resp.data.valid.startDate).format("DD-MM-YYYY").toString();
          alert("Done");
        });
    },
  },
};
</script>
<style scoped>
#main-form {
  
  padding: 10px;
  border-radius: 10px;
  color: white;
  background-color: white;
}
input {
  text-align: start;
}
</style>
