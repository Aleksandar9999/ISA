<template>
  <tr>
    <td>{{ item.id }}</td>
    <td>{{ item.name }}</td>
    <td>{{ item.maxPerson }}</td>
    <td>{{ item.rules }}</td>
    <td>{{ item.status }}</td>
    <td>
      <a :href="showBoat">Show</a>
    </td>
    <td style="padding: 0px 15px">
      <w-button class="mr2" @click="deleteBoat" bg-color="error">
        Delete
      </w-button>
    </td>
  </tr>
</template>
<script>
import axios from "axios";
import config from "../../configuration/config";
export default {
  props: ["item"],
  data() {
    return {
      item_local: {},
      showBoat: "",
    };
  },
  methods: {
    deleteService() {
      if (confirm("Do you really want to delete your boat?")) {
        axios
          .delete(
            config.apiStart + "/api/boatowner-boats/" + this.item_local.id,
            config.requestHeader
          )
          .then(() => {
            alert(this.item_local.name + " deleted");
            this.$emit("boatDeleted", true);
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
          this.showBoat =
            "/boatowners/" +
            this.item_local.boatownerId +
            "/boats/" +
            this.item_local.id;
        }
      },
    },
  },
};
</script>
<style scoped>
td {
  padding: 10px;
}
</style>
