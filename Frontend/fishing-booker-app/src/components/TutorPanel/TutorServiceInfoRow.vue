<template>
  <tr>
    <td>{{ item.id }}</td>
    <td>{{ item.name }}</td>
    <td>{{ item.maxPerson }}</td>
    <td>{{ item.rules }}</td>
    <td>{{ item.status }}</td>
    <td>
      <router-link :to="{ name: profileName, params: { id: item.id } }"
        >Show</router-link>
    </td>
    <td style="padding: 0px 15px;">
    <w-button class="mr2" @click="deleteService" bg-color="error">
          Delete
        </w-button>
    </td>
  </tr>
</template>
<script>
import axios from 'axios';
import config from '../../configuration/config';
export default {
  props: ["item"],
  data() {
    return {
      item_local: {},
    };
  },
  methods: {
      deleteService(){
          if(confirm('Do you really want to delete your service?')){
                 axios.delete(config.apiStart+'/api/services/'+this.item_local.id).then(response=>{alert(response)})
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
          this.status = itemFromProps.status;
        }
      },
    },
  },
};
</script>
<style>
td{

    padding:10px
}
</style>
