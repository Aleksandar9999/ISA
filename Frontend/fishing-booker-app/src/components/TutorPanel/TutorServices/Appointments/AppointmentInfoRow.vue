<template>
  <tr>
    <td>{{ item_local.startDate }}</td>
    <td>{{ item_local.duration }}</td>
    <td>{{ item_local.maxPerson }}</td>
    <td>{{ item_local.price }}</td>
    <td>
      <w-button @click="showProfile">User info</w-button>
    </td>
  </tr>
</template>
<script>
import moment from "moment";
import axios from "axios";
import config from "../../../../configuration/config";
export default {
  props: ["item"],
  component: {},
  data() {
    return {
      item_local: {
        startDate: "",
      },
      user: null,
    };
  },
  methods: {
    showProfile() {
      if (this.user) this.showDialog();
      else {
        if (this.item_local.userId)
          axios
            .get(config.apiStart + "/api/users/" + this.item_local.userId)
            .then((resp) => {
              this.user = resp.data;
              this.showDialog();
            });
      }
    },
    showDialog() {
      alert(
        this.user.name +
          " " +
          this.user.surname +
          "\nEmail: " +
          this.user.email +
          "\nPhone: " +
          this.user.phoneNumber +
          "\nAddress: " +
          this.user.address.street +
          ", " +
          this.user.address.city +
          ", " +
          this.user.address.country
      );
    },
  },
  watch: {
    item: {
      immediate: true,
      handler(itemFromProps) {
        if (itemFromProps) {
          console.log(itemFromProps);
          this.item_local = {
            ...this.item_local,
            ...itemFromProps,
          };
          this.item_local.startDate = moment(itemFromProps.startDate).format(
            "DD-MM-YYYY"
          );
        }
      },
    },
  },
};
</script>
<style></style>
