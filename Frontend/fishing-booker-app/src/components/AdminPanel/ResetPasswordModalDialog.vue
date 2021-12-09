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
      <template #title> Reset password </template>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>New password:</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="text" v-model="admin.password"> </w-input>
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>New password repeat:</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="text" v-model="newPasswordRepeat"></w-input>
        </div>
      </w-flex>
      <template #actions>
        <div class="spacer" />
        <w-button @click="save" bg-color="success"> Save </w-button>
      </template>
    </w-dialog>
  </div>
</template>
<script>
import axios from 'axios';
import config from '../../configuration/config';
export default {
  props: ["show", "idTutor"],
  data() {
    return {
      dialog: {
        show: true,
        fullscreen: true,
        persistent: true,
        persistentNoAnimation: false,
        width: 400,
      },
      admin:{},
      newPasswordRepeat:''
    };
  },
  mounted() {
      axios.get(`${config.apiStart}/api/admins/reset-password`,config.requestHeader).then(response=>{
                if(response.data)
                  this.$router.push("/profile")});
    axios
      .get(config.apiStart + "/api/users/me",config.requestHeader)
      .then((resp) => (this.admin = resp.data));
    
  },
  methods: {
    save() {
      axios
        .post(
          config.apiStart + `/api/admins/${this.admin.id}/reset-password`,
          this.admin,
          config.requestHeader
        )
        .then((resp) => {
            alert("Done")
          console.log(resp);
        });
    },
  },
};
</script>
<style>
p{
  color: black;
}
</style>
