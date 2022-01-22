<template lang="">
  <tr>
    <td>{{ item_local.id }}</td>
    <td>{{ item_local.reason }}</td>
    
    <td>
      <w-button @click="showDialog">USER INFO</w-button>
    </td>
    <td v-if="item_local.requestStatus !== 'PENDING'">{{ item_local.requestStatus }}</td>
    <td v-if="item_local.requestStatus == 'PENDING'">
      <select name="status" id="status" v-model="status" @change="changeStatus">
        <option value="ADMIN_CONFIRMED">CONFIRM</option>
        <option value="PENDING">PENDING</option>
        <option value="REJECTED">REJECT</option>
      </select>
      <button @click="save">Save</button>
    </td>
    
    <td>
      <input v-model="comment" v-if="status == 'REJECTED' && statusChanged" />
    </td>
  </tr>
</template>
<script>
import config from '../../../../configuration/config';
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
    changeStatus() {
      this.statusChanged = true;
    },
    showDialog() {
      console.log(this.item_local);
      this.$axios
        .get(`${config.apiStart}/api/users/${this.item_local.userId}`)
        .then((resp) => {
          let user = resp.data;
          alert(
            user.name +
              " " +
              user.surname +
              "\nEmail: " +
              user.email +
              "\nPhone: " +
              user.phoneNumber +
              "\nAddress: " +
              user.address.street +
              ", " +
              user.address.city +
              ", " +
              user.address.country +
              "\nType: " +
              user.className.split(".").at(-1)
          );
        });
    },
    save() {
      this.item_local.status = this.status;
      console.log(this.item_local);
      this.$axios.put(`${config.apiStart}/api/delete-request`,{ id: this.item_local.id, reason: this.comment,requestStatus:this.item_local.status,userId:this.item_local.userId})
        .then((resp) => {
          this.item_local = resp.data;
        });
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
          this.status = itemFromProps.status;
        }
      },
    },
  },
};
</script>

