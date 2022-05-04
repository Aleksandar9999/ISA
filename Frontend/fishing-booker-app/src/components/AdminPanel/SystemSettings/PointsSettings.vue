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
          <p>Points for client</p>
        </div>
        <div class="box">
          <w-input type="number" v-model="pointsSettings.pointsForClients" />
        </div>
        <div class="box"></div>
      </w-flex>

      <w-flex justify-space-between class="pa3">
        <div class="box">
          <p>Points for user</p>
        </div>
        <div class="box">
          <w-input type="number" v-model="pointsSettings.pointsForUsers" />
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
  date() {
    return {
      pointsSettings: {},
    };
  },
  mounted() {
    this.$axios
      .get(config.apiStart + "/api/points-settings", config.requestHeader)
      .then((resp) => {
        resp.data.forEach((element) => {
          if (!element.valid.endDate) this.pointsSettings = element;
        });
        console.log(this.pointsSettings);
      });
  },
};
</script>
