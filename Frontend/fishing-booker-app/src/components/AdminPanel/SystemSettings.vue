<template>
  <div id="main-form">
    <w-form>
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p style="color: black;">Procenat</p>
        </div>
        <div class="xs6 pa1">
          <input type="number" v-model=systemData.procentage />
        </div>
      </w-flex>
      <div class="text-right mt6">
        <w-button type="submit" @click="save"> Save </w-button>
      </div>
    </w-form>
  </div>
</template>
<script>
import axios from "axios";
import config from '../../configuration/config';
export default {
    data() {
        return {
            systemData:{}
        }
    },
    mounted() {
        axios.get(config.apiStart+"/api/system-data",config.requestHeader).then(resp=>
        {
            this.systemData=resp.data[0]
            console.log(resp.data)
        })
    },
    methods: {
        save(){
            axios.put(config.apiStart+"/api/system-data",this.systemData,config.requestHeader).then(console.log("UPDATED"))
        }
    },
};
</script>
<style scoped>
#main-form {
  margin: 10% 20%;
  padding: 10px;
  border-radius: 10px;
  color: white;
  background-color: white;
}
input {
  text-align: start;
}
</style>
