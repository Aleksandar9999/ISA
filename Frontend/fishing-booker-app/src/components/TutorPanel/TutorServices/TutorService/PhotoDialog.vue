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
      <template #title> Price </template>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Number of days:</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="text" v-model="title"> </w-input>
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Price</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="file" @change="selectedFile" />
        </div>
      </w-flex>

      <template #actions>
        <div class="spacer" />
        <w-button class="mr2" @click="hideDialog" bg-color="error">
          Discard
        </w-button>
        <w-button @click="save" bg-color="success"> Save </w-button>
      </template>
    </w-dialog>
  </div>
</template>
<script>
export default {
  props: ["show", "api"],
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
      title:'',
      file:''
    };
  },
  mounted() {
  },
  methods: {
      selectedFile(event){
          this.file=event.target.files[0]
      },
    hideDialog() {
      this.dialog.show = false;
      this.$emit("hideDialog", {
        dialog: this.dialog.show,
        success: this.success,
      });
    },
    save() {
        const formData=new FormData()
        formData.append("file",this.file);
        formData.append("title",this.title);
      this.$axios.post(`${this.api}/photos`,formData,{headers:{'content-type': 'multipart/form-data'}})
        .then(()=>{
          this.success = true;
          this.hideDialog();
        });
    },
  },
  watch: {
    show: {
      immediate: true,
      handler(fromProp) {
        if (fromProp) this.dialog.show = fromProp;
      },
    },
  },
};
</script>
<style>
    
</style>