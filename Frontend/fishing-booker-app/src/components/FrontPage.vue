<template><div>
    <div class="searchBox">
      <input type="text" placeholder="Type your search here" v-model="searchWord">
      <button @click="search()">Search</button>
      <div>
        <button class="advanced-src" @click="advancedSrcToggle()">Advanced search</button>
      </div>      
    </div>
    <div class="advanced-drop">
    <div id="dropMenu" class="advanced-drop-data">
          <div class="adv-div" v-if="boatsAdv">
              <label for="">Maximal boat speed:</label><input type="text" v-model="maxSpeed">
              <label for="">Navigation type:</label><input style="width:100px" type="text" v-model="navigationType"><br/>
              <label for="">Maximal number of persons:</label><input type="text" v-model="maxPersons">
              <br/>
              <label for="">Select rate of boat:</label>
              <select name="" id="rateSelect" v-model="rate">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </select> <br/>
              <button class="advanced-btn" v-on:click="advancedSearchBoats()">Search</button>
          </div>
          <div class="adv-div" v-if="tutorsAdv">            
            <label for="">Name of adventure:</label><input style="width:150px" type="text" v-model="advName"><br/>
            <label for="">Maximal number of persons on adventure:</label><input type="text" v-model="maxPersons">
            <br/>
              <label for="">Select tutors rate:</label>
              <select name="" id="rateSelect" v-model="rate">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </select> <br/>
              <button class="advanced-btn" v-on:click="advancedSearchAdventures()">Search</button>
          </div>
          <div class="adv-div" v-if="resortsAdv">
            <label for="">Minimal number of rooms:</label><input type="text" v-model="minRooms">
            <label for="">Maximal number of rooms:</label><input type="text" v-model="maxRooms"><br/>

            <label for="">Minimal number of beds:</label><input type="text" v-model="minBeds">
            <label for="">Maximal number of beds:</label><input type="text" v-model="maxBeds">
            <br/>
              <label for="">Select rate of resort:</label>
              <select name="" id="rateSelect" v-model="rate">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </select> <br/>
              <button class="advanced-btn" v-on:click="advancedSearchResorts()">Search</button>
          </div>     
    </div>
    </div>
    <div>
        <div class="tab">
             <button class="tablinks" v-on:click="changeTab('resort')">Resorts</button>
             <button class="tablinks" v-on:click="changeTab('boats')">Boats</button>
             <button class="tablinks" v-on:click="changeTab('tutors')">Tutors</button>
         </div>
                  
    </div>

    <div class="grid-container" id="tabela" v-if="tabType==='resort'">
      <div>        
      <table  class="r-table" cellspacing="0" cellpadding="0" border="0">
          <thead>
            <tr >
              <th v-for="header in headerList" :key="header">
                  {{header}}                
              </th>
            </tr>           
          </thead>       
          <tbody class="tbl-content" v-for="item in dataList" :key="item">
                <tr><td>{{item.id}}</td><td>{{item.name}}</td><td>{{item.resortAddress.street + ' ' + item.resortAddress.country }}</td><td>{{item.numOfRooms}}</td><td>{{item.numOfBeds}}</td><td><router-link :to="{name:profileName, params:{id:item.id} }">Page</router-link></td></tr>
          </tbody>                   
      </table>
      </div>     
      </div>
      <div class="grid-container" id="tabela" v-if="tabType==='boats'">
      <div>        
      <table  class="r-table" cellspacing="0" cellpadding="0" border="0">
          <thead>
              <tr >
              <th v-for="header in headerList" :key="header">
                  {{header}}                
              </th>
            </tr>  
          </thead>       
          <tbody class="tbl-content" v-for="item in dataList" :key="item">
                <tr><td>{{item.id}}</td><td>{{item.name}}</td><td>{{item.maxPerson}}</td><td>{{item.navigationEquipment}}</td><td><router-link :to="{name:profileName, params: {id:item.id}}">Page </router-link></td></tr>
          </tbody>                   
      </table>
      </div>     
      </div>
      <div class="grid-container" id="tabela" v-if="tabType==='tutors'">
      <div>        
      <table  class="r-table" cellspacing="0" cellpadding="0" border="0">
          <thead>
              <tr >
              <th v-for="header in headerList" :key="header">
                  {{header}}                
              </th>
            </tr>  
          </thead>       
          <tbody class="tbl-content" v-for="item in dataList" :key="item">
                <tr><td>{{item.id}}</td><td>{{item.name}}</td><td>{{item.maxPerson}}</td><td>{{item.rules}}</td><td><router-link :to="{name:profileName, params: {id:item.id} }">Page</router-link></td></tr>
          </tbody>                   
      </table>
      </div>     
      </div>
     </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'FrontPage',   
    data(){
      return{
        tabType: 'resort',
        resortHeader:['ID','Name','Location','Number of rooms','Number of beds', 'Page'],
        boatsHeader:['ID','Name','Capacity','Navigation', 'Page'],
        tutorsHeader:['ID','Adventure','Max number of persons','Rules', 'Page'],
        headerList: this.resortHeader,
        reserveList:[],
        dataList: [],
        searchData:[],
        tutorsAdv: false,
        boatsAdv:false,
        resortsAdv:true,
        proba: 'probaNeka',
        profileName:'ResortProfile',
        searchWord:'',
        maxSpeed:-1,
        rate:0,
        advName:'',
        maxPersons:0,
        navigationType:'',
        minRooms:0,
        maxRooms:0,
        minBeds:0,
        maxBeds:0

    }
    } ,
    methods:{
      changeTab(type){
        this.tabType=type;
        if(type==='boats'){
          this.headerList=this.boatsHeader
          this.boatsAdv=true;
          this.resortsAdv=false;
          this.tutorsAdv=false;
          this.profileName='BoatProfile'
          this.getData()
        } else if(type==='tutors'){
          this.headerList=this.tutorsHeader
          this.boatsAdv=false;
          this.resortsAdv=false;
          this.tutorsAdv=true;
          this.profileName='AdventureProfile'
          this.getData()
        } else {
          this.headerList=this.resortHeader
          this.boatsAdv=false;
          this.resortsAdv=true;
          this.tutorsAdv=false;
          this.profileName='ResortProfile'
          this.getData()
        }
        
      },      
      search(){
        this.resetLists()
        if(this.tabType!='tutors'){
        let criteria = this.searchWord        
        if(criteria){
            for(let i = 0; i<this.searchData.length;i++){
              if(!this.searchData[i].name.toLowerCase().includes(criteria)){
                this.searchData.splice(i,1);
                i--
            }
          }
          this.dataList=this.searchData
          return    
        }       
        this.getData()
        } else {
          let criteria = this.searchWord        
          if(criteria){
          for(let i = 0; i<this.searchData.length;i++){
          if(!this.searchData[i].adventure.toLowerCase().includes(criteria)){
            this.searchData.splice(i,1);
            i--
          }
        }
        this.dataList=this.searchData
        return    
        }       
        this.getData()
        }  
      },
      advancedSrcToggle(){
        document.getElementById("dropMenu").classList.toggle("show-drop")
      },
      
      advancedSearchResorts(){
         this.resetLists() 
         if(this.minRooms>0 || this.maxRooms>0 || this.minBeds>0 || this.maxBeds>0 || this.rate>0){   
           
          for(let i = 0; i<this.searchData.length;i++){
          if(this.minRooms>0 & this.maxRooms>0 & this.maxRooms>this.minRooms){
            if(this.searchData[i].numOfRooms<this.minRooms || this.searchData[i].numOfRooms>this.maxRooms){
                this.searchData.splice(i,1);
                i--
            }            
          }
          if(this.minBeds>0 & this.maxBeds>0 & this.maxBeds>this.minBeds){
            if(this.searchData[i].numOfBeds<this.minBeds || this.searchData[i].numOfBeds>this.maxBeds){
                this.searchData.splice(i,1);
                i--
            }            
          }
          if(this.rate>0){
            if(this.searchData[i].rate!=this.rate){
              this.searchData.splice(i,1);
              i--
            }
            this.rate=-1            
          }
        }         
        this.dataList=this.searchData
        return    
        }       
        this.getData()
      },
      advancedSearchBoats(){
         this.resetLists()  
          if(this.maxSpeed>0 || this.navigationType || this.maxPersons>0 || this.rate>0){
          for(let i = 0; i<this.searchData.length;i++){
          if(this.maxSpeed>0){
          if(this.searchData[i].maxSpeed>this.maxSpeed){
            this.searchData.splice(i,1);
            i--
          }          
          }
          if(this.maxPersons>0){
            if(this.searchData[i].maxPerson>this.maxPersons){
              this.searchData.splice(i,1);
              i--
            }            
          }
          if(this.navigationType){
            if(!this.searchData[i].navigationEquipment.toLowerCase().includes(this.navigationType)){
              this.searchData.splice(i,1);
              i--
            }            
          }
          if(this.rate>0){
            if(this.searchData[i].rate!=this.rate){
              this.searchData.splice(i,1);
              i--
            }
                      
          }
        }         
        this.dataList=this.searchData
        this.rate=-1  
        return    
        }
        this.getData()
      },
      advancedSearchAdventures(){
        this.resetLists()
          let criteria = this.advName      
          if(criteria){
          for(let i = 0; i<this.searchData.length;i++){
          if(!this.searchData[i].name.toLowerCase().includes(criteria)){
            this.searchData.splice(i,1);
            i--
          }
          if(this.maxPersons>0){
            if(this.searchData[i].maxPerson>this.maxPersons){
              this.searchData.splice(i,1);
              i--
            }            
          }
          if(this.rate>0){
            if(this.searchData[i].rate!=this.rate){
              this.searchData.splice(i,1);
              i--
            }                   
          }
        }         
        this.dataList=this.searchData
        this.rate=-1     
        return    
        } else if( this.maxPersons>0 || this.rate>0){
          for(let i = 0; i<this.searchData.length;i++){
          if(this.maxPersons>0){
            if(this.searchData[i].maxPerson>this.maxPersons){
              this.searchData.splice(i,1);
              i--
            }            
          }
          if(this.rate>0){
            if(this.searchData[i].rate!=this.rate){
              this.searchData.splice(i,1);
              i--
            }          
          }
          }
          this.dataList=this.searchData
          this.rate=-1  
          return           
        }       
        this.getData()
      },
      fillDataLists(response){
        this.dataList=response.data
        this.reserveList=response.data
      },
      getData(){
        if(this.tabType==='boats'){
          this.dataList=[];
          axios.get('http://localhost:8080/boats').then(response =>
            this.fillDataLists(response)
          )
        } else if(this.tabType==='tutors'){
          this.dataList=[];
          axios.get('http://localhost:8080/api/users/tutors/services/valid').then(response =>
          this.fillDataLists(response)
          )
          
        } else{
          this.dataList=[]
          axios.get('http://localhost:8080/resorts').then(response =>
          this.fillDataLists(response)
          )
        }
      },
      resetLists(){
        if(this.tabType==='boats'){
          axios.get('http://localhost:8080/boats').then(response =>
            this.searchData=response.data
          )
        } else if(this.tabType==='tutors'){
          axios.get('http://localhost:8080/api/users/tutors/services').then(response =>
          this.searchData=response.data
          )
          
        } else{
          axios.get('http://localhost:8080/resorts').then(response =>
          this.searchData=response.data
          )
        }
      }

    },
    mounted(){
      this.changeTab('resort');
      this.getData();    
      if(!localStorage.initialFlag){
        localStorage.logedIn=false;
      }
    }

}
</script>

<style scoped>

    .arrangment{
    margin:2%;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-column-gap: 50px;
    grid-row-gap:50px;
  }

    .tab {
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
  }
  
  /* Style the buttons inside the tab */
  .tab button {
    background-color: inherit;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
    transition: 0.3s;
    font-size: 17px;
  }

  .tab button:hover {
    background-color: #ddd;
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

  .advanced-drop{
    display: flex;
    justify-content: center;
  }

  .advanced-drop-data{
    display: none;
    width: 60%;
    height: 160px;
    border: solid 3px black;
    background-color: white;
    border-radius: 5px;
  }

  .show-drop{
    display: block;
  }

  .advanced-btn{
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

  .advanced-btn:hover{
    background-color: rgb(10, 226, 28);
  }

  .adv-div {
    margin: 0.5%;
    height: 100px;
    
  }
  
  .adv-div input{
    justify-content: left;
    display: inline-block;
    align-items: left;
    padding: 5px;
    margin: 5px;
    width:40px;
  }

  .adv-div select{
    margin: 6px;
  }
  
</style>