<template lang="">
  <tr>
    <td>{{ complaint_local.id }}</td>
    <td>{{ appellant.name }} {{ appellant.surname }}</td>
    <td>{{ appelle.name }} {{ appelle.surname }}</td>
    <td>
      {{ complaint_local.type }}
    </td>
    <td>{{ complaint_local.complaintText }}</td>
    <td v-if="!adminResponse">
      <input type="text" v-model="complaint_local.adminResponse" />
    </td>
    <td v-if="adminResponse">
      <p> {{complaint_local.adminResponse }}</p>
    </td>
    
    <td v-if="!adminResponse">
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
      adminResponse:{},
    };
  },
  methods: {
      save(){
          this.$axios.put(`${config.apiStart}/api/complaints/${this.complaint_local.type}/${this.complaint_local.id}`,this.complaint_local).then(()=>{
              alert("Done")
          })
      }
  },
  mounted() {
    this.$axios
      .get(`${config.apiStart}/api/users/${this.complaint_local.appellantId}`)
      .then((resp) => {
        this.appellant = resp.data;
      });
    this.$axios
      .get(`${config.apiStart}/api/users/${this.complaint_local.appelleeId}`)
      .then((resp) => {
        this.appelle = resp.data;
      });
  },
  watch: {
    item: {
      immediate: true,
      handler(itemFromProps) {
        if (itemFromProps) {
          this.complaint_local = {
            ...itemFromProps,
          };
            this.adminResponse=itemFromProps.adminResponse
        }
      },
    },
  },
};
</script>
<style lang=""></style>
