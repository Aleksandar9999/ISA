<template lang="">
    <div class="total">
            <span>{{!title?'Total hours:':title}} <em>{{value}}</em></span>
        </div>
</template>
<script>
export default {
    props:['title','value']
}
</script>
<style lang="">
    
</style>
