<template>
  <div class="images-container">
    <w-card class="main-card" title="Gallery" no-border style="padding: 0px 3% 0px 3%">
      <w-flex justify-end class="pa3" v-if="showAddButton">
        <w-button @click="showDialog">Add new</w-button>
      </w-flex>
       </w-card>
    <div class="grow mx1" style="margin: 0px; padding: 0px">
      <w-flex column align-center
        justify-space-between
        class="wrapper"
        v-for="(photo, i) in photos_local"
        :key="i"
      >
        <img :src="photo.url" :alt="photo.title" style="margin: 0px; padding: 0px" />
        <w-button v-if="showAddButton"
          type="submit"
          bg-color="red"
          color="white"
          @click="deleteImage(photo)"
          >Obrisi</w-button
        >
      </w-flex>
    </div>
  </div>
</template>

<script>
export default {
  props: ["photos","deleteFunction","showAddButton"],
  data() {
    return {
      index: 0,
      photos_local: [],
    };
  },
  methods: {
    deleteImage(photo) {
      this.deleteFunction(photo)
    },
    showDialog() {
      this.$emit("showDialog", true);
    },
  },
  watch: {
    photos: {
      immediate: true,
      handler(photosFromProps) {
        if (photosFromProps) {
          this.photos_local = {
            ...photosFromProps,
          };
        }
      },
    },
  },
};
</script>
<style>
.images-container {
  margin-top: -2%;
}
img {
  width: 200px;
  height: 200px;
  background-size: cover;
  margin: 5px;
  border-radius: 3px;
  border: 1px solid lightgray;
  object-fit: contain;
}
</style>
