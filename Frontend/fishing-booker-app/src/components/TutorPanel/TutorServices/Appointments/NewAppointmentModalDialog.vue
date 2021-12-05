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
      <template #title> Appointment </template>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Start date:</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="date" v-model="appointmentLocal.startDate"> </w-input>
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Duraition</p>
        </div>
        <div class="xs6 pa1">
          <input type="number" v-model="appointmentLocal.duration" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Max num of persons:</p>
        </div>
        <div class="xs6 pa1">
          <input type="number" v-model="appointmentLocal.maxPerson" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Additional services</p>
        </div>
        <div class="xs6 pa1">
          <input type="text" v-model="appointmentLocal.additionalServices" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Service:</p>
        </div>
        <div class="xs6 pa1">
          <select name="services" id="services" v-model="appointmentLocal.serviceId">
              <option value=""></option>
            <option
              v-for="service in services"
              :key="service.id"
              :value="service.id"
            >
              {{ service.name }}
            </option>
          </select>
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Clients:</p>
        </div>
        <div class="xs6 pa1">
          <select name="clients" id="clients" v-model="appointmentLocal.userId">
              <option value=""></option>
            <option
              v-for="client in clients"
              :key="client.id"
              :value="client.id"
            >
              {{ client.name }} {{client.surname}}
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
export default {
  props: ["show", "idTutor"],
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
      clients: [],
      services: [],
      appointmentLocal: {
        startDate: "",
        duration: "",
        maxPerson: "",
        additionalServices: "",
        price: "",
        serviceId: "",
        userId: "",
      },
    };
  },
  mounted() {
    axios
      .get(config.apiStart + "/api/users/tutors/4/services")
      .then((resp) => (this.services = resp.data));
    axios
      .get(config.apiStart + "/api/users/clients")
      .then((resp) => (this.clients = resp.data));
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
      //TODO: Fix hardcoded route
      axios
        .post(
          config.apiStart + "/api/appointments/tutor-service",
          this.appointmentLocal
        )
        .then((resp) => {
          this.success = true;
          this.hideDialog();
          console.log(resp);
        });
        console.log(this.appointmentLocal)
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
p{
  color: black;
}
</style>
