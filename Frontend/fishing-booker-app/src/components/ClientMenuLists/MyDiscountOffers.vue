<template>
<div></div>
<div class="radios">     
        <input type="radio" name="entityType" id="resortRadio" value="resort" v-model="entityType">
        <label for="">Resort</label> 
        <input type="radio" name="entityType" id="boatRadio" value="boat" v-model="entityType">
        <label for="">Boat</label> 
        <input type="radio" name="entityType" id="adventureRadio" value="adventure" v-model="entityType">
        <label for="">Adventure</label>
    </div>     
<div class="grid-container" id="tabela" v-if="entityType==='boat'"> 
        <div>
        <table class="r-table" cellspacing="0" cellpadding="0" border="0">
            <thead>
              <tr >
              <th v-for="header in tableHeader" :key="header">
                  {{header}}                
              </th>
            </tr>  
            </thead>
            <tbody class="tbl-content" v-for="item in boats" :key="item">
              <tr><td>{{item.name}}</td><td>{{item.address.city}} {{item.address.country}}</td><td><button @click="cancelSubscriptionBoat(item)">Cancel subscription</button></td></tr>
            </tbody>
        </table>
        </div>
    </div>
    <div class="grid-container" id="tabela" v-if="entityType==='resort'"> 
        <div>
        <table class="r-table" cellspacing="0" cellpadding="0" border="0">
            <thead>
              <tr >
              <th v-for="header in tableHeader" :key="header">
                  {{header}}                
              </th>
            </tr>  
            </thead>
            <tbody class="tbl-content" v-for="item in resorts" :key="item">
              <tr><td>{{item.name}}</td><td>{{item.resortAddress.city}} {{item.resortAddress.country}}</td><td><button @click="cancelSubscriptionResort(item)">Cancel subscription</button></td></tr>
            </tbody>
        </table>
        </div>
    </div>
    <div class="grid-container" id="tabela" v-if="entityType==='adventure'"> 
        <div>
        <table class="r-table" cellspacing="0" cellpadding="0" border="0">
            <thead>
              <tr >
              <th v-for="header in tableHeader" :key="header">
                  {{header}}                
              </th>
            </tr>  
            </thead>
            <tbody class="tbl-content" v-for="item in tutorServices" :key="item">
              <tr><td>{{item.name}}</td><td>{{item.address.city}} {{item.address.country}}</td><td><button @click="cancelSubscriptionAdventure(item)">Cancel subscription</button></td></tr>
            </tbody>
        </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import config from '../../configuration/config';
export default {
    data(){
        return{
            entityType:'resort',
            tableHeader:['Name','Address','Cancel subscription'],
            entitiesData:{},
            resorts:[],
            boats:[],
            tutorServices:[]
        }
    },
    methods:{
        cancelSubscriptionAdventure(item){
            axios.post('http://localhost:8080/cancelAdventureSubscription',item).then(response=>console.log(response.data))
        },
        cancelSubscriptionBoat(item){
            axios.post('http://localhost:8080/cancelBoatSubscription',item).then(response=>console.log(response.data))
        },
        cancelSubscriptionResort(item){
            axios.post('http://localhost:8080/cancelResortSubscription',item).then(response=>console.log(response.data))
        },
        menageData(subscription){
            this.enitiesData=subscription
            this.resorts=subscription.resorts
            this.boats=subscription.boats
            this.tutorServices=subscription.tutorServices
        },
        getData(){
            axios.get('http://localhost:8080/getSubscriptions',config.requestHeader).then(response=>this.menageData(response.data))
        }
    },
    mounted(){
        this.getData()
    }
}
</script>

<style scoped>
.radios{
  display: flex;
    justify-content: center;
    margin: 5px;
    padding: 1%;
    border: solid 4px rgb(5, 100, 76);
    border-radius: 25px;
    background-color: green;
}

.radios input{
  margin-left: 25px;
  margin-right: 5px;
}

.r-table {
    width: 80%;
    margin-left: 10%;
    margin-right: 10%;
    margin-top: 20px;
    background-color: white;
    margin-bottom: 150px;
    border: 1px solid rgba(0, 0, 0, 0.1);
  }

  .r-table thead{
    display: table-header-group;
  }
  
  .tbl-content {
    min-height: auto;
    max-height: 500px;
    overflow-x: auto;
    margin-top: 0px;
    border: 1px solid rgba(0, 0, 0, 0.1);
  }
  
  .r-table th {
    padding: 10px 5px;
    text-align: center;
    font-weight: bold;
    font-size: 13px;
    color: #000;
    text-transform: uppercase;
    background:rgba(0, 0, 0, 0.1);
  }
  
  .r-table td {
    padding: 13px;
    text-align: center;
    vertical-align: middle;
    font-weight: 300;
    font-size: 18px;
    color: #000;
    border-bottom: solid 1px rgba(0, 0, 0, 0.1);
    word-wrap: break-word;
  }
  
  .r-table .td-center {
    text-align: center;
    padding-right: 20px;
  }
  
  .r-table tbody tr {
    height: 75px;
  }

  .searchBox{
    display: flex;
    justify-content: center;
    margin: 5px;
    padding: 1%;
    border: solid 4px rgb(5, 100, 76);
    border-radius: 25px;
    background-color: green;
  }

  .searchBox input{
    width: 190px;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
    font-size: 16px;
    border-radius: 7px;
    margin-left: 5px;
    margin-right: 25px;
  }

  .searchBox button {
    font-size: 18px;
    border: 3px black;
    padding: 4px 5px;
    color: whitesmoke;
    background-color: rgb(15, 71, 10); 
    margin-left: 10px;
    border-radius: 15px;
    width: 160px;
    height:  30px;
    transition: 0.8s;
  }
  .searchBox button:hover{
    background-color: rgb(10, 226, 28);
  }
</style>