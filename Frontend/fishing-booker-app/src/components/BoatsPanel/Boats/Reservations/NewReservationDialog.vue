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
      <template #title> Reservation </template>
      <w-flex justify-space-between class="pa3">
        <div class="box">
          <p>Start</p>
        </div>
        <div class="box" style="margin: 0px 5% 0px 5%">
          <w-input
            type="date"
            v-model="appointmentLocal.start.date"
            :min="minDate"
          />
        </div>
        <div class="box">
          <w-input type="text" v-model="appointmentLocal.start.time" />
        </div>
      </w-flex>
      <w-flex justify-space-between class="pa3">
        <div class="box">
          <p>End</p>
        </div>
        <div class="box" style="margin: 0px 5% 0px 5%">
          <w-input
            type="date"
            v-model="appointmentLocal.end.date"
            :min="appointmentLocal.start.date"
          />
        </div>
        <div class="box">
          <w-input type="text" v-model="appointmentLocal.end.time" />
        </div>
      </w-flex>

      <w-flex justify-space-between class="pa3">
        <div class="box">
          <p>Max num of persons:</p>
        </div>
        <div class="box">
          <w-input type="number" v-model="appointmentLocal.maxPerson" />
        </div>
      </w-flex>

      <w-flex justify-space-between class="pa3">
        <div class="box">
          <p>Additional services</p>
        </div>
        <div class="box">
          <w-input type="text" v-model="appointmentLocal.additionalServices" />
        </div>
      </w-flex>


      <w-flex wrap class="text-center" v-if="!userId">
        <div class="box">
          <p>Clients:</p>
        </div>
        <div class="box">
          <select name="clients" id="clients" v-model="appointmentLocal.userId">
            <option value=""></option>
            <option
              v-for="client in clients"
              :key="client.id"
              :value="client.id"
            >
              {{ client.name }} {{ client.surname }}
            </option>
          </select>
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
import config from "../../../../configuration/config";
import moment from "moment";
export default {
  props: ["show", "idBoatOwner", "selectBoat", "userId"],
  data() {
    return {
      dialog: {
        show: false,
        fullscreen: false,
        persistent: true,
        persistentNoAnimation: false,
        width: 400,
      },
      minDate: moment().format("YYYY-MM-DD"),
      success: false,
      clients: [],
      boats: [],
      appointmentLocal: {
        start: { date: "", time: "" },
        end: { date: "", time: "" },
        duration: "",
        maxPerson: "",
        additionalServices: "",
        price: "",
        boatId: this.$route.params.idboat,
        userId: "",
        validateUser: true,
      },
    };
  },
  mounted() {
  
    axios
      .get(config.apiStart + "/api/users/clients", config.requestHeader)
      .then((resp) => (this.clients = resp.data));

    if (this.selectBoat)
      this.appointmentLocal.boatId = this.selectBoat;
    if (this.userId) this.appointmentLocal.userId = this.userId;
  },
  methods: {
    mounted() {
      console.log(this.userId);
    },
    hideDialog() {
      this.dialog.show = false;
      this.$emit("hideDialog", {
        dialog: this.dialog.show,
        success: this.success,
      });
    },
    save() {
      let appointmentToSave = { ...this.appointmentLocal };
      appointmentToSave.start =
        this.appointmentLocal.start.date +
        " " +
        this.appointmentLocal.start.time +
        ":00";
      appointmentToSave.end =
        this.appointmentLocal.end.date +
        " " +
        this.appointmentLocal.end.time +
        ":00";

      console.log(appointmentToSave);
      let api = "/api/appointments/boat";
      axios
        .post(config.apiStart + api, appointmentToSave, config.requestHeader)
        .then((resp) => {
          this.success = true;
          this.hideDialog();
          console.log(resp);
        })
        .catch((error) => {
          alert(
            `${error.response.data.error}\n\n${error.response.data.message}`
          );
        });
    },
  },
  watch: {
    show: {
      immediate: true,
      handler(fromProp) {
        console.log(fromProp);
        if (fromProp) this.dialog.show = fromProp;
      },
    },
  },
};
</script>
<style>
p {
  color: black;
}
.box {
  background-color: #ffffff;
}
</style>
