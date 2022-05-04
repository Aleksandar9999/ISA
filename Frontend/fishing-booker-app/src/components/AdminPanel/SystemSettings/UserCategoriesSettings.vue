<template>
  <div id="main-form" style="margin: 0px 20% 0px 20%">
    <w-form>
      <w-flex justify-space-between class="pa3">
        <div class="box">
          <p style="color: black">User category</p>
        </div>
      </w-flex>
      <table>
        <tr>
          <td><p style="color: black">Name</p></td>
          <td style="width: 25%">
            <p style="color: black; margin: 0px 0px 0px -40%">
              Client discount procentage
            </p>
          </td>
          <td style="width: 25%">
            <p style="color: black; margin: 0px 0px 0px -40%">
              User revenue procentage
            </p>
          </td>
          <td style="width: 25%">
            <p style="color: black; margin: 0px 0px 0px -40%">Points margine</p>
          </td>
        </tr>
        <tr v-for="item in items" :key="item.id">
          <td style="width: 50%">
            <p style="color: black">{{ item.name }}</p>
          </td>
          <td style="width: 25%">
            <w-input style="color: black" v-model="item.discountProcentage" />
          </td>
          <td style="width: 25%">
            <w-input style="color: black" v-model="item.revenueProcentage" />
          </td>
          <td style="width: 25%">
            <w-input style="color: black" v-model="item.pointsMargine" />
          </td>
          <td><w-button type="submit" @click="save(item)"> Save </w-button></td>
        </tr>
      </table>
    </w-form>
  </div>
</template>
<script>
import config from "../../../configuration/config";
export default {
  data() {
    return {
      items: [],
    };
  },
  mounted() {
    this.$axios
      .get(
        config.apiStart + "/api/system-data/user-categories",
        config.requestHeader
      )
      .then((resp) => {
        this.items = resp.data.sort(function (a, b) {
          return a.id - b.id;
        });
        console.log(this.items);
      });
  },
  methods: {
    save(item) {
      console.log(item);
      this.$axios
        .put(
          config.apiStart + "/api/system-data/user-categories",
          item,
          config.requestHeader
        )
        .then((resp) => {
          console.log(resp.data);
          alert("Done");
        });
    },
  },
};
</script>
<style scoped></style>
