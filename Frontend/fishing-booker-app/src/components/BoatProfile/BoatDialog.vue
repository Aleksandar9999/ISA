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
          <input type="text" v-model="BoatLocal.name" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Boat type</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="BoatLocal.typeOfBoat" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Boat length</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="BoatLocal.length" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Boat engine</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="BoatLocal.engineId" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Max speed</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="BoatLocal.maxSpeed" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Navigation Equipment</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="BoatLocal.navigationEquipment" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Description</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="BoatLocal.description" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Max num of persons:</p>
        </div>
        <div class="xs6 pa1">
          <input type="number" v-model="BoatLocal.maxPerson" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Rules</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="BoatLocal.rules" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Fishing equipment</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="BoatLocal.fishingEquipment" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Cancel percentage</p>
        </div>
        <div class="xs6 pa1">
          <input type="number" v-model="BoatLocal.cancelPercentage" />
        </div>
      </w-flex>

      <!--Polja za adresu-->
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Street</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="BoatLocal.boatAddress.street" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Country</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="BoatLocal.boatAddress.country" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>City</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="BoatLocal.boatAddress.city" />
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
      BoatLocal: {
        name: "",
        typeOfBoat: "",
        length: "",
        engineId: "",
        maxSpeed: "",
        navigationEquipment: "",
        description: "",
        maxPerson: "",
        rules: "",
        fishingEquipment: "",
        cancelProcentage: "",
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
          config.apiStart + `/api/users/boatowners/${this.idBoatOwner}/boats`,
          this.BoatLocal,
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
