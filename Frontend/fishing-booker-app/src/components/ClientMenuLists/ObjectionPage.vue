<template>
<div>
    <label for="">Text of my objection:</label><br/>
    <textarea v-model="text" name="" id="objc" cols="100" rows="25"></textarea><br/>
    <button>Send objection</button>
</div>    
</template>

<script>
import axios from 'axios'
export default {
    data(){
        return {
            objection:{},
            text:''
        }
    },
    methods:{
        collectData(){
            this.objection.text=this.text;
            this.objection.user_id='1';
        },
        sendObjection(){
            this.collectData()
            axios.post('http://localhost:8080/sendObjection',this.objection).then(response=>console.log(response.data))
        }
    }
}
</script>

<style scoped>

</style>