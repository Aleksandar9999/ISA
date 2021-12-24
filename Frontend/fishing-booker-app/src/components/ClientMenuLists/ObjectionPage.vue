<template>
<div v-if="pickEntity">

</div>
<div v-if="showObjectionField">
    <label for="">Text of my objection:</label><br/>
    <textarea v-model="text" name="" id="objc" cols="100" rows="25"></textarea><br/>
    <button @click="sendObjection()">Send objection</button>
</div>    
</template>

<script>
import axios from 'axios'
export default {
    data(){
        return {
            pickEntity:true,
            showObjectionField:false,
            objection:{},
            text:'',
            entityType:'',
            entityId:-1
        }
    },
    methods:{
        pickEnt(){
            this.showObjectionField=true;
        },
        collectData(){
            this.objection.text=this.text;
            this.objection.entityType=this.entityType;
            this.objection.entityId=this.entityId;
        },
        sendObjection(){
            this.collectData()
            axios.post('http://localhost:8080/sendObjection',this.objection).then(response=>console.log(response.data))
            this.showObjectionField=false;
            this.pickEntity=true;
        }
    }
}
</script>

<style scoped>

</style>