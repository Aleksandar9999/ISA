<template>
    <tr>
    <td>{{ item_local.startDate }}</td>
    <td>{{ item_local.duration }}</td>
    <td>{{ item_local.maxPerson }}</td>
    <td>{{ item_local.price }}</td>
    <td> 
        <w-button>User info</w-button>
    </td>
  </tr>
</template>
<script>
import moment from 'moment'
export default {
    props:['item'],
    data() {
        return {
            item_local:{
                startDate:'',
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
       }
      },
    }}
}
</script>
<style>
    
</style>