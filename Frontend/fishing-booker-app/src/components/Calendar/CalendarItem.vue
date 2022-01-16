<template lang="">
  <td :class="getTdClassName()">
    <div class="progress">
      <div class="date">
        <a :href="getHref()" > <span
          ><p>{{ item.date }}</p></span
        ></a>
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
  props: ["item", "tdClassName","href"],
  methods: {
    getHref() {
      if(this.href) return this.href;      
      return `../../../week/${this.item.date}`;
    },
    getTdClassName(){
      console.log(this.item)
      if (this.tdClassName == "reserved") 
        return 'reserved'
      if(this.item.reservedHours) return "reserved"
      //if(this.item.duration.split(" ")[1] > 0  || this.item.duration.split(" ")[0] > 0) return 'reserved'
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
