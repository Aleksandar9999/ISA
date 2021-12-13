<template lang="">
  <w-card shadow style="margin-top: 15px; background-color: white">
    <w-flex wrap class="text-center">
      <div class="xs6 pa1">
        <p>{{ price_local.numberOfDays }} dana</p>
      </div>
      <div class="xs6 pa1">
        <p>
          <b>{{ price_local.price }} RSD </b>
        </p>
      </div>
    </w-flex>
  </w-card>
</template>

<script>
export default {
  props: ["price", "showReserveButton"],
  data() {
    return {
      price_local: {},
    };
  },
  watch: {
    price: {
      immediate: true,
      handler(propsItem) {
        if (propsItem)
          this.price_local = {
            ...this.price_local,
            ...propsItem,
          };
      },
    },
  },
};
</script>
<style>
.action-label {
  color: red;
  font-size: 13px;
}
</style>
