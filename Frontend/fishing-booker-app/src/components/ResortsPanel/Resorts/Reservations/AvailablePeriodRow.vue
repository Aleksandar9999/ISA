<template lang="">
    <tr>
    <td>{{ item_local.startDate }}</td>
    <td>
      {{ item_local.endDate }}
    </td>
  </tr>
</template>
<script>
import moment from 'moment';
export default {
    props:['item'],
    data() {
        return {
            item_local:{
                startDate:'',
                endDate:''
            }
        }
    },
    watch: {
    item: {
      immediate: true,
      handler(itemFromProps) {
        if (itemFromProps) {
          this.item_local = {
            ...this.item_local,
            ...itemFromProps,
          };
          this.item_local.startDate=moment(itemFromProps.startDate).format('DD-MM-YYYY')
          this.item_local.endDate=moment(itemFromProps.endDate).format('DD-MM-YYYY')
        
       }
      },
    }}
}
</script>
<style>
    
</style>