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
      <template #title> Resort </template>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Name</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="resortLocal.name" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Description</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="resortLocal.description" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Number of rooms:</p>
        </div>
        <div class="xs6 pa1">
          <input type="number" v-model="resortLocal.numOfRooms" />
        </div>
      </w-flex>

      
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Number of beds per room:</p>
        </div>
        <div class="xs6 pa1">
          <input type="number" v-model="resortLocal.numOfBeds" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Rules</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="resortLocal.rules" />
        </div>
      </w-flex>

      <!--Polja za adresu-->
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Street</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="resortLocal.resortAddress.street" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Country</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="resortLocal.resortAddress.country" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>City</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="resortLocal.resortAddress.city" />
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
import config from "../../../configuration/config";
export default {
  props: ["show", "idResortOwner"],
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
      resortLocal: {
        name: "",
        description: "",
        numOfRooms: "",
        numOfBeds: "",
        rules: "",
        resortAddress: {
          street: "",
          country: "",
          city: "",
        },
      },
    };
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
          config.apiStart + `/api/resorts`,
          this.resortLocal,
          config.requestHeader
        )
        .then((resp) => {
          this.success = true;
          this.hideDialog();
          console.log(resp);
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
<style></style>
