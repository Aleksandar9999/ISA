<template lang="">
  <tr>
    <td>{{ report.id }}</td>
    <td>{{ report.comment }}</td>
    <td>{{ report.type }}</td>
    <td v-if="report.status == 'ADMIN_CONFIRMED' || report.status == 'REJECTED'">{{ report.status }}</td>
    <td v-if="report.status == 'PENDING'">
    <input type="text" v-if="status=='reject'" v-model="adminResponse" />
      <select name="status" id="status" v-model="status" >
        <option value="accept">CONFIRM</option>
        <option value="reject">REJECT</option>
      </select>
    </td>
    <td>
      <w-button style="margin: 0px 0px 0px 15px;" bg-color="info" @click="moreInfo">More info</w-button>
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
      status:'',
      report: {},
      adminResponse:'',
    };
  },
  methods: {
      moreInfo(){
        alert(`Admin responded: ${this.report.adminResponded.name} ${this.report.adminResponded.surname}\nResponse: ${this.report.response}`)
      },
      save(){
          this.$axios.put(`${config.apiStart}/api/appointment-report/bad-comment/${this.report.id}/${this.status}`,{reason:this.adminResponse}).then(()=>{
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
        console.log(itemFromProps)
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
