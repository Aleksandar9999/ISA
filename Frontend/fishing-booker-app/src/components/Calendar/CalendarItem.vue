<template lang="">
  <td :class="getTdClassName()">
    <div class="progress">
      <div class="date">
        <span
          ><p>{{ item.date }}</p></span
        >
      </div>
      <div class="hours">
        <a :href="getHref()">
          <span
            ><p>{{ item.duration }}</p></span>
        </a>
      </div>
    </div>
  </td>
</template>
<script>
export default {
  props: ["item", "tdClassName"],
  methods: {
    getHref() {
      if (this.tdClassName == "reserved")
        return `/calendar/days/${this.item.date}`;
      return "";
    },
    getTdClassName(){
      console.log(this.item.duration)
      if (this.tdClassName == "reserved" || this.item.duration.split(" ")[1] > 0)
        return 'reserved'
      return ''
    }
  },
};
</script>
<style scoped>
p {
  text-align: center;
}
.reserved {
  background-color: brown;
}
.reserved p {
  color: white;
  text-align: center;
}

</style>
