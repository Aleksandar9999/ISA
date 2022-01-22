<template lang="">
  <tr>
    <td>{{ complaint_local.id }}</td>
    <td>
      {{ complaint_local.appointment.user.name }}
      {{ complaint_local.appointment.user.surname }}
    </td>
    <td>{{ complaint_local.objection }}</td>
    <td v-if="!complaint_local.adminResponded">
      <input type="text" v-model="complaint_local.response" />
    </td>
    <td v-if="complaint_local.adminResponded">
      <p>{{ complaint_local.response }}</p>
    </td>

    <td v-if="!complaint_local.adminResponded">
      <input type="button" value="SAVE" @click="save" />
    </td>
  </tr>
</template>
<script>
import config from "../../../configuration/config";
export default {
  props: ["item"],
  data() {
    return {
      complaint_local: {},
      appellant: {},
      appelle: {},
      adminResponse: {},
    };
  },
  methods: {
    save() {
      this.$axios
        .put(`${config.apiStart}/api/objections/${this.complaint_local.id}`, {
          id: this.complaint_local.id,
          appointmentId: this.complaint_local.appointment.id,
          userEmail: this.complaint_local.userEmail,
          response: this.complaint_local.response,
        })
        .then(() => {
          alert("Done");
        });
    },
  },
  mounted() {
    /*this.$axios
      .get(`${config.apiStart}/api/users/${this.complaint_local.appellantId}`)
      .then((resp) => {
        this.appellant = resp.data;
      });
    this.$axios
      .get(`${config.apiStart}/api/users/${this.complaint_local.appelleeId}`)
      .then((resp) => {
        this.appelle = resp.data;
      });*/
  },
  watch: {
    item: {
      immediate: true,
      handler(itemFromProps) {
        if (itemFromProps) {
          console.log(itemFromProps);
          this.complaint_local = {
            ...itemFromProps,
          };
          this.adminResponse = itemFromProps.adminResponse;
        }
      },
    },
  },
};
</script>
<style lang=""></style>
