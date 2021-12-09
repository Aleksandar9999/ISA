<template lang="">
  <tr>
    <td>{{ item_local.id }}</td>
    <td>{{ item_local.name }}</td>
    <td>
      {{ item_local.surname }}
    </td>
    <td v-if="item_local.status !== 'PENDING'">{{ item_local.status }}</td>
    <td v-if="item_local.status == 'PENDING'">
      <select name="status" id="status" v-model=status @change="changeStatus">
        <option value="CONFIRMED">CONFIRM</option>
        <option value="PENDING">PENDING</option>
        <option value="REJECTED">REJECT</option>
      </select>
      <button @click=save>Save</button>
    </td>
    <td>
      <w-button @click=showDialog>More info</w-button>
    </td>
  </tr>
</template>
<script>
import config from '../../configuration/config';
import axios from "axios";
export default {
  props: ["item"],
  data() {
    return {
      item_local:{},
      status:''
    }
  },
  methods: {
    showDialog() {
      alert(
        this.item_local.name +
          " " +
          this.item_local.surname +
          "\nEmail: " +
          this.item_local.email +
          "\nPhone: " +
          this.item_local.phoneNumber +
          "\nAddress: " +
          this.item_local.address.street +
          ", " +
          this.item_local.address.city +
          ", " +
          this.item_local.address.country
      );
      },
    save(){
      this.item_local.status=this.status;
      console.log(this.item_local)
      axios.put(config.apiStart+"/api/users/"+this.item_local.id,this.item_local,config.requestHeader).then(resp=>
      {
        this.item_local=resp.data;
      }
      );
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
          this.status=itemFromProps.status;
        }
      },
    },
  }
};
</script>
<style lang=""></style>
