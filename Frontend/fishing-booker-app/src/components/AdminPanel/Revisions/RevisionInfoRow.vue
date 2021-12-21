<template lang="">
  <tr>
    <td>{{ item_local.id }}</td>
    <td>{{ item_local.rate }}</td>
    <td>
      {{ item_local.comment }}
    </td>
    <td>
      {{ item_local.className }}
    </td>
    <td v-if="item_local.status !== 'PENDING'">{{ item_local.status }}</td>
    <td
      v-if="item_local.status == 'PENDING' || item_local.status == 'CONFIRMED'"
    >
      <select name="status" id="status" v-model="status" @change="changeStatus">
        <option value="ADMIN_CONFIRMED">CONFIRM</option>
        <option value="PENDING">PENDING</option>
        <option value="REJECTED">REJECT</option>
      </select>
      <button @click="save">Save</button>
    </td>
  </tr>
</template>
<script>
import config from "../../../configuration/config";
export default {
  props: ["item"],
  data() {
    return {
      item_local: {},
      comment: "",
      status: "",
      statusChanged: false,
    };
  },
  methods: {
    showDialog() {},
    save() {
      this.item_local.status = this.status;
      this.$axios.put(`${config.apiStart}/api/revision/${this.item_local.id}`,this.item_local).then(()=>{
          alert("DONE")
      })
    },
  },
  watch: {
    item: {
      immediate: true,
      handler(itemFromProps) {
        if (itemFromProps) {
          this.item_local = {
            ...itemFromProps,
          };
          this.status = itemFromProps.status;
        }
      },
    },
  },
};
</script>
<style lang=""></style>
