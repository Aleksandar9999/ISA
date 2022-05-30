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
      <template #title> Boat </template>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Name</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="boatLocal.name" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Description</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="boatLocal.description" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Max num of persons:</p>
        </div>
        <div class="xs6 pa1">
          <input type="number" v-model="boatLocal.maxPerson" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Rules</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="boatLocal.rules" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Fishing equipment</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="boatLocal.fishingEquipment" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Cancel percentage</p>
        </div>
        <div class="xs6 pa1">
          <input type="number" v-model="boatLocal.cancelPercentage" />
        </div>
      </w-flex>

      <!--Polja za adresu-->
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Street</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="boatLocal.boatAddress.street" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Country</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="boatLocal.boatAddress.country" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>City</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="boatLocal.boatAddress.city" />
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
  props: ["show", "idBoatOwner"],
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
      boatLocal: {
        name: "",
        description: "",
        maxPerson: "",
        rules: "",
        fishingEquipment: "",
        cancelPercentage: "",
        boatAddress: {
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
          config.apiStart + `/api/boats`,
          this.boatLocal,
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
