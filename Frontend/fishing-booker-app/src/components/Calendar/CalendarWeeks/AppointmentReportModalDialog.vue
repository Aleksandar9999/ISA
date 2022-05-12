<template>
  <div>
    <w-dialog
      v-model="dialog.show"
      :fullscreen="dialog.fullscreen"
      :width="dialog.width"
      :persistent="dialog.persistent"
      :persistent-no-animation="dialog.persistentNoAnimation"
      title-class="backgorund-color: green;"
    >
      <template #title> Appointment report </template>
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Report type:</p>
        </div>
        <div class="xs6 pa1">
          <select name="type" id="type" v-model="type">
              <option value=""></option>
              <option value="bad-comment">Bad comment</option>
              <option value="not-show-up">Client not show up</option>
              <option value="ok-comment">OK comment</option>
          </select>
        </div>
      </w-flex>
      
      <w-flex wrap class="text-center" v-if="type=='bad-comment'">
        <div class="xs6 pa1">
          <p>Comment:</p>
        </div>
        <div class="xs6 pa1">
          <w-input name="comment" type="text" v-model="report.comment"> </w-input>
        </div>
      </w-flex>

     
      <template #actions>
        <div class="spacer" />
        <w-button class="mr2" @click="hideDialog(false)" bg-color="error">
          Discard
        </w-button>
        <w-button @click="save" bg-color="success"> Save </w-button>
      </template>
    </w-dialog>
  </div>
</template>
<script>
import config from '../../../configuration/config';
export default {
  props: ["show", "idAppointment"],
  data() {
    return {
      dialog: {
        show: false,
        fullscreen: false,
        persistent: true,
        persistentNoAnimation: false,
        width: 400,
      },
      success: false,
      clients: [],
      services: [],
      type:'',
      report:{
        comment:'',
        appointmentId: this.idAppointment
      }
    };
  },
  mounted() {
   
  },
  methods: {
    hideDialog(succ) {
      this.dialog.show = false;
      this.success=succ;
      this.$emit("hideDialog", {
        dialog: this.dialog.show,
        success: this.success,
      });
    },
    save() {
      this.report.appointmentId=this.idAppointment;
      this.$axios.post(`${config.apiStart}/api/appointment-report/${this.type}`,this.report).then(()=>{
        alert("Done!");
        this.hideDialog(true)
      })
    },
  },
  watch: {
    show: {
      immediate: true,
      handler(fromProp) {
        console.log(fromProp);
        if (fromProp) this.dialog.show = fromProp;
      },
    },
  },
};
</script>
<style>
p{
  color: black;
}
</style>
