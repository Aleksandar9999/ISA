<template>
  <tr>
    <td>{{ item.id }}</td>
    <td>{{ item.name }}</td>
    <td>{{ item.maxPerson }}</td>
    <td>{{ item.rules }}</td>
    <td>{{ item.status }}</td>
    <td>{{ rate }}</td>
    <td>
      <a :href="showService">Show</a>
    </td>
    <td style="padding: 10px 0px" v-if="showAdminButtons">
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
  props: ["item", "showAdminButton"],
  data() {
    return {
      item_local: {},
      showService: "",
      showAdminButtons:false,
      rate:''
    };
  },
  mounted() {
    this.showDeleteButton();
    this.$axios.get(`${config.apiStart}/api/revision/tutor-service/${this.item.id}/rate`).then((resp)=>
      this.rate=resp.data
    );
  },
  methods: {
    showDeleteButton() {
      if (!localStorage.roles) return;
      if (!localStorage.roles.includes("ROLE_TUTOR")) return;
      if (localStorage.id == this.$route.params.idtutor) this.showAdminButtons = true;
    },
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
          })
          .catch((err)=>{
            alert(err.response.data.message)
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
