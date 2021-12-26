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
             <tr > <td>{{item.start}}</td><td>{{item.address.city}} {{item.address.country}}</td><td>{{item.price}}</td><td>{{item.newPrice}}</td><td>{{item.discount}}</td><td><button @click="makeReservation(item.appointmentId)">Reserve</button></td></tr>
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
              axios.post('http://localhost:8080/makeQuickReservation', id).then((response) => console.log(response.data));            
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
    }
}
</script>

<style scoped>

</style>