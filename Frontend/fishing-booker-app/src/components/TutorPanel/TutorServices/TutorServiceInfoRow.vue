<template>
  <tr>
    <td>{{ item.id }}</td>
    <td>{{ item.name }}</td>
    <td>{{ item.maxPerson }}</td>
    <td>{{ item.rules }}</td>
    <td>{{ item.status }}</td>
    <td>
      <a :href="showService">Show</a>
    </td>
    <td style="padding: 0px 15px">
      <w-button class="mr2" @click="deleteService" bg-color="error">
        Delete
      </w-button>
    </td>
  </tr>
</template>
<script>
import axios from "axios";
import config from "../../../configuration/config";
export default {
  props: ["item"],
  data() {
    return {
      item_local: {},
      showService: "",
    };
  },
  methods: {
    deleteService() {
      if (confirm("Do you really want to delete your service?")) {
        axios
          .delete(
            config.apiStart + "/api/tutor-services/" + this.item_local.id,
            config.requestHeader
          )
          .then(() => {
            alert(this.item_local.name + " deleted");
            this.$emit("tutorServiceDeleted", true);
          });
      }
    },
  },
  watch: {
    item: {
      immediate: true,
      handler(itemFromProps) {
        if (itemFromProps) {
          this.item_local = {
            ...this.item_local,
            ...itemFromProps,
          };
          console.log(itemFromProps);
          this.status = itemFromProps.status;
          this.showService =
            "/tutors/" +
            this.item_local.tutorId +
            "/services/" +
            this.item_local.id;
        }
      },
    },
  },
};
</script>
<style>
td {
  padding: 10px;
}
</style>
