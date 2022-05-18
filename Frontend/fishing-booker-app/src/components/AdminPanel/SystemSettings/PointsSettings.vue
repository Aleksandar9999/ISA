<template lang="">
  <div id="main-form" style="margin: 5% 20% 5% 20%">
    <w-form>
      <w-flex justify-space-between class="pa3">
        <div class="box">
          <p style="color: black">Points settings</p>
        </div>
      </w-flex>
      <w-flex justify-space-between class="pa3">
        <div class="box">
          <p>Points for owner</p>
        </div>
        <div class="box">
          <w-input type="number" v-model="pointsSettings.pointsForOwners" />
        </div>
        <div class="box"></div>
      </w-flex>

      <w-flex justify-space-between class="pa3">
        <div class="box">
          <p>Points for user</p>
        </div>
        <div class="box">
          <w-input type="number" v-model="pointsSettings.pointsForClients" />
        </div>
        <div class="box"></div>
      </w-flex>
       <w-flex justify-space-between class="pa3">
        <div class="box">
          <p>Penalty    </p>
        </div>
        <div class="box">
          <w-input type="number" v-model="pointsSettings.penalty" />
        </div>
        <div class="box"></div>
      </w-flex>
      <div class="text-right mt6">
        <w-button type="submit" @click="save"> Save </w-button>
      </div>
    </w-form>
  </div>
</template>
<script>
import config from "../../../configuration/config";
export default {
   data() {
    return {
      pointsSettings:{}
    };
  },
  methods:{
      save(){
    this.$axios
        .post(
          config.apiStart + "/api/points-settings",
          this.pointsSettings,
          config.requestHeader
        )
        .then((resp) => {
          console.log(resp.data)
          alert("Done")
        });
      }
  },
  mounted() {
    this.$axios
      .get(config.apiStart + "/api/points-settings", config.requestHeader)
      .then((resp) => {
          this.pointsSettings=resp.data[0]
          console.log(this.pointsSettings);
      });
  },
};
</script>
