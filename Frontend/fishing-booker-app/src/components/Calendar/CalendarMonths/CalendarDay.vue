<template lang="">
  <td :class="tdClassName">
    <div class="date">
      <span>{{ day.date }}</span>
    </div>
    <div class="hours">
      <a :href="getHref()">
        Hours: <span>{{ day.hours }}</span>
      </a>
    </div>
  </td>
</template>
<script>
export default {
  props: ["day", "tdClassName"],
  mounted() {
    console.log("nESO")
    console.log(this.day)
  },
  methods: {
    getHref() {
      if (this.tdClassName !== "disable")
        return `/calendar/days/${this.day.date}`;
      return "";
    },
  },
};
</script>
<style lang=""></style>
