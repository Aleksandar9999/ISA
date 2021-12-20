<template>
  <div class="images-container">
    <w-card class="main-card" title="Gallery" no-border> </w-card>
    <div class="grow mx1">
      <w-flex
        column
        align-center
        justify-space-between
        class="wrapper"
        v-for="(photo, i) in photos_local"
        :key="i"
      >
        <img :src="photo.url" :alt="photo.title" />
        <w-button
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
  props: ["photos","deleteFunction"],
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
  },
  watch: {
    photos: {
      immediate: true,
      handler(photosFromProps) {
        console.log(photosFromProps);
        if (photosFromProps) {
          this.photos_local = {
            ...this.photos_local,
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
