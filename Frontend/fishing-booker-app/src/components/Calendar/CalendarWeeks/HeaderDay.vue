<template lang="">
    <li :class=className>
            <a :href="getHref()" >
                <b>{{dayDate.format("MMM DD")}}</b>
                <span>{{dayDate.format('dddd')}}</span>
            </a>
        </li>
</template>
<script>
export default {
    props:['dayDate','className'],
    methods: {
        getHref(){
            return `/calendar/days/${this.dayDate.format("YYYY-MM-DD")}`
        }
    },
}
</script>
