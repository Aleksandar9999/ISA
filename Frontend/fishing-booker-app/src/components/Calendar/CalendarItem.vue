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
  props: ["item", "tdClassName","href", "discountOfferItem","standardPeriodItem"],
  methods: {
    getHref() {
      if(this.href) return this.href;      
      return `../../../week/${this.item.date}`;
    },
    getTdClassName(){
      //console.log(this.discountOfferItem.reservedHours)
      if (this.tdClassName == "reserved" || this.item.reservedHours) return 'reserved'
      if(this.discountOfferItem && this.discountOfferItem.reservedHours) return "discount-offer"
      if(this.standardPeriodItem && this.standardPeriodItem.reservedHours) return "standard-period"
      
      //if(this.discountOfferItem.reservedHours) return "discount-offer"
      //if(this.item.duration.split(" ")[1] > 0  || this.item.duration.split(" ")[0] > 0) return 'reserved'
      return 'disable'
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

.discount-offer {
  background-color: rgb(0, 68, 255);
}
.discount-offer p {
  color: white;
  text-align: center;
}

.standard-period {
  background-color: rgb(0, 255, 55);
}
.standard-period p {
  color: white;
  text-align: center;
}
</style>
