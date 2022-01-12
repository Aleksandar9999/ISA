<template>
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
            <tbody class="tbl-content" v-for="item in entitiesData" :key="item">
             <tr > <td>{{item.start}}</td><td>{{item.address.city}} {{item.address.country}}</td><td>{{item.price}}</td><td>{{item.newPrice}}</td><td>{{item.discount}}</td><td><button @click="makeReservation(item.appointmentId)">Reserve</button></td></tr>
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
            <tbody class="tbl-content" v-for="item in entitiesData" :key="item">
            <tr >  <td>{{item.start}}</td><td>{{item.address.city}} {{item.address.country}}</td><td>{{item.price}}</td><td>{{item.newPrice}}</td><td>{{item.discount}}</td><td><button @click="makeReservation(item.appointmentId)">Reserve</button></td></tr>
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
            <tbody class="tbl-content" v-for="item in entitiesData" :key="item">
             <tr > <td>{{item.start}}</td><td>{{item.address.city}} {{item.address.country}}</td><td>{{item.price}}</td><td>{{item.newPrice}}</td><td>{{item.discount}} </td><td><button @click="makeReservation(item.appointmentId)">Reserve</button></td></tr>
            </tbody>
        </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    props:['type'],
    data(){
        return{
            entityType:'',
            entitiesData:[],
            tableHeader:['Date and time','Address','Price','New Price','Discount','Make Reservation'],
        }
    },
    methods:{
        makeReservation(id){
          if (confirm("Do you want to reserve this appointment?")) {
              axios.post('http://localhost:8080/makeQuickReservation/'+ id).then((response) => console.log(response.data));            
        }
        },
        getData(){
            if(this.entityType==='boat'){
                axios.get('http://localhost:8080/boatDiscounts').then(response =>
                this.entitiesData=response.data
            );
            } else 
            if(this.entityType==='resort'){
                axios.get('http://localhost:8080/resortDiscounts').then(response =>
                this.entitiesData=response.data
            );
            } else
            {
                axios.get('http://localhost:8080/tutorDiscounts').then(response =>
                this.adventures=response.data
                )
            }
        }
    },
    mounted(){
        this.entityType=this.type   
        this.getData();
    }
}
</script>

<style scoped>
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
</style>