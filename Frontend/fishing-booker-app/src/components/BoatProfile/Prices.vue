<template>
  <w-card class="main-card" title="Price list" no-border>
    <w-flex justify-end class="pa3">
      <w-button @click="showDialog">Add new</w-button>
    </w-flex>
    <price-card
      v-for="(price, index) in prices"
      :key="index"
      :price="price"
      :showReserveButton="false"
    />
  </w-card>
</template>
<script>
import axios from "axios";
import PriceCard from "../TutorPanel/TutorServices/TutorService/PriceCard.vue";
import config from "../../configuration/config";
export default {
  props: ["idboat", "fetch"],
  data() {
    return {
      prices: [],
    };
  },
  methods: {
    showDialog() {
      this.$emit("showDialog", true);
    },
    fetchData() {
      axios
        .get(
          config.apiStart + "/api/boatowner-boats/" + this.idboat + "/prices",
          config.requestHeader
        )
        .then((resp) => {
          this.prices = resp.data;
        });
    },
  },
  mounted() {
    this.fetchData();
  },
  components: {
    PriceCard,
  },
  watch: {
    fetch: {
      immediate: true,
      handler(prop) {
        if (prop) this.fetchData();
      },
    },
  },
};
</script>
<style scoped>
.main-card {
  margin: 0% 10%;
  padding: 3%;
}
</style>
