<template>
    <div class="radios">     
        <input type="radio" name="entityType" id="resortRadio" value="resort" v-model="entityType">
        <label for="">Resort</label> 
        <input type="radio" name="entityType" id="boatRadio" value="boat" v-model="entityType">
        <label for="">Boat</label> 
        <input type="radio" name="entityType" id="adventureRadio" value="adventure" v-model="entityType">
        <label for="">Adventure</label>
    </div> 
    <div class="searchBox"> 
        <label for="">Date and time: </label>
        <input type="text" name="" id="dateID" placeholder="YYYY-MM-DD" v-model="dateAndTime">

        <label for="">Days to stay: </label>
        <input type="text" name="" id="numdays" v-model="numDays">

        <label for="">Number of guests: </label>
        <input type="text" name="" id="numguests" v-model="numPersons">

        <button @click="search()">Search</button>
    </div>
    <div v-if="showSrc" class="searchBox">
        <select name="" id="">
            <option @click="sort('rateAsc')" value="rateAsc">Sort by rate ascending</option>
            <option @click="sort('rateDesc')" value="rateDesc">Sort by rate descending</option>
            <option @click="sort('priceAsc')" value="priceAsc">Sort by price ascending</option>
            <option @click="sort('priceDesc')" value="priceDesc">Sort by price descending</option>
        </select>
        <label for="">Search location: </label>
        <input type="text" name="" id="numdays" v-model="location">

        <button @click="searchLocation()">Search location</button>
    </div>

    <div v-if="showAdditionalInfo">
      <table class="r-table" cellspacing="0" cellpadding="0" border="0">
        <thead>
              <tr >
              <th>Name: </th>  <th>Check:</th>
            </tr>  
            </thead>
            <tbody class="tbl-content" v-for="item in additionalServices" :key="item">
              <tr><td >{{item.name}}</td><td><input type="checkbox" name="" id="" @click="addExtra(item)"></td></tr>
            </tbody>
      </table>
      <button @click="makeReservation()">Make Reservation</button>
    </div>

    <div class="grid-container" id="tabela" v-if="showSrc && entityType==='boat'"> 
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
              <tr><td>{{item.boat.name}}</td>
              <td>{{item.start}}</td><td>{{item.address.city}} {{item.address.country}}</td><td>{{item.maxPerson}}</td><td>{{item.price}}</td><td>{{item.rate}}</td><td><button @click="showAditionalInfB(item)">Reserve</button></td></tr>
            </tbody>
        </table>
        </div>
    </div>
    <div class="grid-container" id="tabela" v-if="showSrc && entityType==='resort'"> 
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
              <tr><td>{{item.resort.name}}</td>
              <td>{{item.start}}</td><td>{{item.address.city}} {{item.address.country}}</td><td>{{item.maxPerson}}</td><td>{{item.price}}</td><td>{{item.rate}}</td><td><button @click="showAditionalInfR(item)">Reserve</button></td></tr>
            </tbody>
        </table>
        </div>
    </div>
    <div class="grid-container" id="tabela" v-if="showSrc && entityType==='adventure'"> 
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
              <tr><td >{{item.tutorService.name}}</td>
              <td>{{item.start}}</td><td>{{item.address.city}} {{item.address.country}}</td><td>{{item.maxPerson}}</td><td>{{item.price}}</td><td>{{item.rate}}</td><td><button @click="showAditionalInfA(item)">Reserve</button></td></tr>
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
            entityType:'boat',
            resorts:[],
            boats:[],
            adventures:[],
            entitiesData:[],
            filteredData:[],
            dateAndTime: "2022-01-21",
            numDays: 0,
            numPersons: 0,
            showSrc:false,
            showAdditionalInfo:false,
            tableHeader:['Name','Date and time','Address','Max persons','Price','Rate', 'Make Reservation'],
            appointment:{},
            additionalServices:[],
            extras:[],
            location:'',
            appointmentToSendDTO:{}
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
          if(this.numDays<1 || this.numPersons<1){
            alert('You must fill all fields before search.')
            return;
          }
          this.loadEntities()      
          this.searchDate()
          this.searchDays()
          this.searchGuests()
          this.showSrc=true;
        },
        searchLocation(){
          if(this.location!=''){
            this.search();
          for(let i = 0; i<this.entitiesData.length; i++){            
            let loc= this.entitiesData[i].address.city + this.entitiesData[i].address.country             
                  if(!loc.toLowerCase().includes(this.location)){
                    this.entitiesData.splice(i,1);
                    i--
                  }  
                }
          } else {
            this.search();
          }
        },
        searchDate(){   
            let date=new Date(this.dateAndTime)
            date=Date.parse(this.dateAndTime);         
            if(this.entityType==='resort'){  
              this.entitiesData=this.resorts;
              this.setRatesResort(this.entitiesData)              
              for(let i = 0; i<this.entitiesData.length; i++){
                  var startDate=this.formatDate(this.entitiesData[i].start);
                  if(startDate<=date){
                    this.entitiesData.splice(i,1);
                    i--
                  } 
                }
            } else if(this.entityType==='boat') {
                this.entitiesData=this.boats;
                this.setRatesBoat(this.entitiesData)
                for(let i = 0; i<this.entitiesData.length; i++){
                  var startDate1=this.formatDate(this.entitiesData[i].start);
                  if(startDate1<=date){
                    this.entitiesData.splice(i,1);
                    i--
                  }  
                }    
            } else {
                this.entitiesData=this.adventures;
                this.setRatesAdventures(this.entitiesData)
                for(let i = 0; i<this.entitiesData.length; i++){
                  var startDate2=this.formatDate(this.entitiesData[i].start);
                  if(startDate2<=date){
                    this.entitiesData.splice(i,1);
                    i--
                  }  
                }
            }
        },

        formatDate(javaDate){
          let splitDate=javaDate.split("T")[0]
          let date= Date.parse(splitDate)
          return date
        },

        searchDays(){
          for(let i = 0; i<this.entitiesData.length; i++){
            if(this.entitiesData[i].duration>this.numDays){
              this.entitiesData.splice(i,1);
                    i--
            }
          }
        },

        searchGuests(){
            for(let i = 0; i<this.entitiesData.length; i++){
            if(this.entitiesData[i].maxPerson>this.numPersons){
              this.entitiesData.splice(i,1);
                    i--
            }
          }
        },

        sort(sortType){
            if(sortType==='rateAsc'){
                this.entitiesData.sort((a,b)=> (a.rate>b.rate) ? 1 :(b.rate>a.rate) ? -1 :0);
            } else 
            if(sortType==='rateDesc'){
                this.entitiesData.sort((a,b)=> (a.rate<b.rate) ? 1 :(b.rate<a.rate) ? -1 :0);
            } else 
            if(sortType==='priceAsc'){
                this.entitiesData.sort((a,b)=> (a.price>b.price) ? 1 :(b.price>a.price) ? -1 :0);
            } else 
            if(sortType==='priceDesc'){
                this.entitiesData.sort((a,b)=> (a.price<b.price) ? 1 :(b.price<a.price) ? -1 :0);
            } 
        },

        showAditionalInfB(item){
          this.showAdditionalInfo=true
          this.additionalServices=item.boat.extrasServices
          this.appointment=item
        },
        showAditionalInfR(item){
          this.showAdditionalInfo=true
          this.additionalServices=item.resort.extrasServices
          this.appointment=item
        },
        showAditionalInfA(item){
          this.showAdditionalInfo=true
          this.additionalServices=item.tutorService.extrasServices
          this.appointment=item
        },

        addExtra(item){  
          this.appointment.additionalServices=''
          this.appointment.additionalServices+=item.service + ',';
        },

        makeReservation(){
          if (confirm("Do you want to reserve this appointment?")) {
            if(this.entityType==='boat'){     
              delete this.appointment.rate;    
              this.appointment.boat.boatOwner=null;     
              axios.post('http://localhost:8080/makeBoatReservation', this.appointment).then((response) => console.log(response.data));
            } else 
            if(this.entityType==='resort'){
              delete this.appointment.rate; 
              axios.post('http://localhost:8080/makeResortReservation', this.appointment).then((response) => console.log(response.data));
            } else
            {
              delete this.appointment.rate; 
              axios.post('http://localhost:8080/makeTutorServiceReservation', this.appointment).then((response) => console.log(response.data));
            }
            
        }
        this.showSrc=false
        },

        loadEntities(){
          axios.get('http://localhost:8080/boatAppointments').then(response =>
          this.boats=response.data
          );
      axios.get('http://localhost:8080/resortAppointments').then(response =>
          this.resorts=response.data
          );
      axios.get('http://localhost:8080/api/tutor-service/appointments').then(response =>
          this.adventures=response.data
          )
        },
        
    },
    mounted() {
      this.loadEntities();
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