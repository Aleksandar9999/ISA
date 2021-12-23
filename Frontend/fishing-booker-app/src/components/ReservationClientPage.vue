<template>
    <div class="radios">     
        <input type="radio" name="entityType" id="" value="resort">
        <label for="">Resort</label> 
        <input type="radio" name="entityType" id="" value="boat">
        <label for="">Boat</label> 
        <input type="radio" name="entityType" id="" value="adventure">
        <label for="">Adventure</label>
    </div> 
    <div class="searchBox"> 
        <label for="">Date and time: </label>
        <input type="datetime" name="" id="dateID" v-model="dateAndTime">

        <label for="">Days to stay: </label>
        <input type="text" name="" id="numdays" v-model="numDays">

        <label for="">Number of guests: </label>
        <input type="text" name="" id="numguests" v-model="numPersons">

        <button @click="search()">Search</button>
    </div>
    <div v-if="showSrc">
        <select name="" id="">
            <option @click="sort('rateAsc')" value="rateAsc">Sort by rate ascending</option>
            <option @click="sort('rateDesc')" value="rateDesc">Sort by rate descending</option>
            <option @click="sort('priceAsc')" value="priceAsc">Sort by price ascending</option>
            <option @click="sort('priceDesc')" value="priceDesc">Sort by price descending</option>
        </select>
    </div>
    <div v-if="showAdditionalInfo">
      <input type="checkbox" name="" id=""> <p></p> <br>
      <button @click="makeReservation(item.id)">Make Reservation</button>
    </div>
    <div class="grid-container" id="tabela" v-if="showSrc"> 
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
              <tr><td>{{item.start}}</td><td>{{item.address.city}} {{item.address.country}}</td><td>{{item.maxPerson}}</td><td>{{item.price}}</td><td>{{item.rate}}</td><td><button @click="showAditionalInf()">Reserve</button></td></tr>
            </tbody>

        </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    data() {
        return{
            resorts:[],
            boats:[],
            adventures:[],
            entitiesData:[],
            filteredData:[],
            dateAndTime: Date(),
            numDays: 0,
            numPersons: 0,
            showSrc:false,
            showAdditionalInfo:false,
            tableHeader:['Date and time','Address','Max persons','Price','Rate', 'Make Reservation']
        }
    },
    methods: {
        setRatesResort(list){
          for(let i=0; i<list.length; i++){
              axios.get('http://localhost:8080/api/revision/resortAppointmentRate/'+list[i].id).then(response =>
            list[i].rate=response.data
          );
          }          
        },
        setRatesBoat(list){
          for(let i=0; i<list.length; i++){
              axios.get('http://localhost:8080/api/revision/boatAppointmentRate/'+list[i].id).then(response =>
            list[i].rate=response.data
          );
          }          
        },
        setRatesAdventures(list){
          for(let i=0; i<list.length; i++){
              axios.get('http://localhost:8080/api/revision/tutorServiceAppointmentRate/'+list[i].id).then(response =>
            list[i].rate=response.data
          );
          }          
        },
        search(){
            let entityType = document.getElementsByName('entityType')
            if(entityType==='resort'){
                this.entitiesData=this.resorts;
                this.setRatesResort(this.entitiesData)
                for(let i = 0; i<this.entitiesData.length; i++){
                  if(!this.entitiesData[i].start>=this.dateAndTime && !this.entitiesData[i].start+this.entitiesData[i].duration*24*60<=this.dateAndTime){
                    this.entitiesData.splice(i,1);
                    i--
                  } 
                }
            } else if(entityType==='boat') {
                this.entitiesData=this.boats; 
                this.setRatesBoat(this.entitiesData)
                for(let i = 0; i<this.entitiesData.length; i++){
                  if(!this.entitiesData[i].start>=this.dateAndTime && !this.entitiesData[i].start+this.entitiesData[i].duration*24*60<=this.dateAndTime){
                    this.entitiesData.splice(i,1);
                    i--
                  } 
                }    
            } else {
                this.entitiesData=this.adventures
                this.setRatesAdventures(this.entitiesData)
                for(let i = 0; i<this.entitiesData.length; i++){
                  if(!this.entitiesData[i].start>=this.dateAndTime && !this.entitiesData[i].start+this.entitiesData[i].duration*24*60<=this.dateAndTime){
                    this.entitiesData.splice(i,1);
                    i--
                  } 
                }
            }

            this.showSrc=true;
        },
        sort(sortType){
            if(sortType==='rateAsc'){
                this.showSrc=false
            } else 
            if(sortType==='rateDesc'){
                this.showSrc=false
            } else 
            if(sortType==='priceAsc'){
                this.showSrc=false
            } else 
            if(sortType==='priceDesc'){
                this.showSrc=false
            } 
        },
        showAditionalInf(){
          this.showAdditionalInfo=true
        },
        makeReservation(id){
          if (confirm("Do you want to reserve this appointment?")) {
            axios.post('http://localhost:8080/', {id}).then((response) => console.log(response.data));
        }
        }
    },
    mounted() {
      axios.get('http://localhost:8080/boatAppointments').then(response =>
          this.boats=response.data
          );
      axios.get('http://localhost:8080/resortAppointments').then(response =>
          this.resorts=response.data
          );
      axios.get('http://localhost:8080/api/tutor-service/appointments').then(response =>
          this.adventures=response.data
          )
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
    width: 60%;
    margin-left: 20%;
    margin-right: 20%;
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