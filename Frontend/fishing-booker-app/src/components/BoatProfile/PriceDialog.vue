<template>
  <div>
    <w-dialog
      v-model="dialog.show"
      :fullscreen="dialog.fullscreen"
      :width="dialog.width"
      :persistent="dialog.persistent"
      :persistent-no-animation="dialog.persistentNoAnimation"
      title-class="backgorund-color: green;"
    >
      <template #title> Price </template>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Number of days:</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="number" v-model="price.numberOfDays"> </w-input>
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Price</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="number" v-model="price.price" />
        </div>
      </w-flex>

      <template #actions>
        <div class="spacer" />
        <w-button class="mr2" @click="hideDialog" bg-color="error">
          Discard
        </w-button>
        <w-button @click="save" bg-color="success"> Save </w-button>
      </template>
    </w-dialog>
  </div>
</template>
<script>
import axios from "axios";
import config from "../../configuration/config";
export default {
  props: ["show", "idboat"],
  data() {
    return {
      dialog: {
        show: false,
        fullscreen: false,
        persistent: true,
        persistentNoAnimation: false,
        width: 400,
      },
      success: false,
      price:{
          numberOfDays:0,
          price:0,
      }
    };
  },
  mounted() {
  },
  methods: {
    hideDialog() {
      this.dialog.show = false;
      this.$emit("hideDialog", {
        dialog: this.dialog.show,
        success: this.success,
      });
    },
    save() {
      axios
        .post(
          config.apiStart + "/api/boatowner-boats/"+this.idboat+"/prices",
          this.price,
          config.requestHeader
        )
        .then(()=>{
          this.success = true;
          this.hideDialog();
        });
    },
  },
  watch: {
    show: {
      immediate: true,
      handler(fromProp) {
        if (fromProp) this.dialog.show = fromProp;
      },
    },
  },
};
</script>
<style scoped>
p{
  color: black;
}
</style>
