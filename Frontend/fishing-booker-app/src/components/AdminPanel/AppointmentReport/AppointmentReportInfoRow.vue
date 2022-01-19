<template lang="">
  <tr>
    <td>{{ report.id }}</td>
    <td>{{ report.comment }}</td>
    <td>{{ report.type }}</td>
    <td v-if="report.status == 'ADMIN_CONFIRMED' || report.status == 'REJECTED'">{{ report.status }}</td>
    <td
      v-if="report.status == 'PENDING'"
    >
      <select name="status" id="status" v-model="status" @change="changeStatus">
        <option value="accept">CONFIRM</option>
        <option value="reject">REJECT</option>
      </select>
      <w-button style="margin: 0px 0px 0px 15px;" bg-color="success" @click="save">Save</w-button>
    </td>
  </tr>
</template>
<script>
import config from "../../../configuration/config";
export default {
    props: ["item"],
  data() {
    return {
      report: {},
      appellant: {},
      appelle: {},
      adminResponse:{},
    };
  },
  methods: {
      save(){
          this.$axios.put(`${config.apiStart}/api/appointment-report/bad-comment/${this.report.id}/${this.status}`).then(()=>{
              alert("Done");
              this.report.status=this.status;
          })
      }
  },
  mounted() {
  },
  watch: {
    item: {
      immediate: true,
      handler(itemFromProps) {
        if (itemFromProps) {
          this.report = {
            ...itemFromProps,
          };
        }
      },
    },
  },
};
</script>
<style lang=""></style>
