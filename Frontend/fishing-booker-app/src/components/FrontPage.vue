<template>
    <div class="searchBox">
      <input type="text" placeholder="Type your search here">
      <button @click="search()">Search</button>
      <div>
        <button class="advanced-src" @click="advancedSrcToggle()">Advanced search</button>
      </div>      
    </div>
    <div class="advanced-drop">
    <div id="dropMenu" class="advanced-drop-data">
          <div class="adv-div" v-if="boatsAdv">
              <label for="">Minimal number of boat horsepowers:</label><input type="text">
              <label for="">Name of engine trademark:</label><input style="width:100px" type="text"><br/>
              <br/>
              <label for="">Select rate of boat:</label>
              <select name="" id="rateSelect">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </select> <br/>
              <button class="advanced-btn" v-on:click="advancedSearch()">Search</button>
          </div>
          <div class="adv-div" v-if="tutorsAdv">            
            <label for="">Name of adventure:</label><input style="width:150px" type="text"><br/>
            <label for="">Maximal number of persons on adventure:</label><input type="text">
            <label for="">Maximal price of adventure:</label><input type="text">
            <br/>
              <label for="">Select tutors rate:</label>
              <select name="" id="rateSelect">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </select> <br/>
              <button class="advanced-btn" v-on:click="advancedSearch()">Search</button>
          </div>
          <div class="adv-div" v-if="resortsAdv">
            <label for="">Minimal number of rooms:</label><input type="text">
            <label for="">Maximal number of rooms:</label><input type="text"><br/>

            <label for="">Minimal number of beds:</label><input type="text">
            <label for="">Maximal number of beds:</label><input type="text">
            <br/>
              <label for="">Select rate of resort:</label>
              <select name="" id="rateSelect">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </select> <br/>
              <button class="advanced-btn" v-on:click="advancedSearch()">Search</button>
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
                <tr><td>{{item.id}}</td><td>{{item.name}}</td><td>{{item.resortAddress.street + ' ' + item.resortAddress.country }}</td><td>{{item.numOfRooms}}</td><td><router-link :to="{name:profileName, params:{item} }">Page</router-link></td></tr>
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
                <tr><td>{{item.id}}</td><td>{{item.name}}</td><td>{{item.maxPerson}}</td><td>{{item.navigationEquipment}}</td><td><router-link :to="{name:profileName, params:{item} }">Page</router-link></td></tr>
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
                <tr><td>{{item.id}}</td><td>{{item.tutor.name}}</td><td>{{item.tutor.surname}}</td><td>{{item.name}}</td><td><router-link :to="{name:profileName, params:{item} }">Page</router-link></td></tr>
          </tbody>                   
      </table>
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
        resortHeader:['ID','Name','Location','Number of rooms', 'Page'],
        boatsHeader:['ID','Name','Capacity','Navigation', 'Page'],
        tutorsHeader:['ID','Name','Surname','Adventure', 'Page'],
        headerList: this.resortHeader,
        dataList: [],
        tutorsAdv: false,
        boatsAdv:false,
        resortsAdv:true,
        item: 'probaNeka',
        profileName:'ResortProfile'
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

      },
      advancedSrcToggle(){
        document.getElementById("dropMenu").classList.toggle("show-drop")
      },
      
      advancedSearch(){

      },
      getData(){
        if(this.tabType==='boats'){
          this.dataList=[];
          axios.get('http://localhost:8080/boats').then(response =>
          this.dataList=response.data
          )
        } else if(this.tabType==='tutors'){
          this.dataList=[];
          axios.get('http://localhost:8080/api/users/tutors/services').then(response =>
          this.dataList=response.data
          )
          
        } else{
          this.dataList=[]
          axios.get('http://localhost:8080/resorts').then(response =>
          this.dataList=response.data
          )
        }
      }

    },
    mounted(){
      this.changeTab('resort');
      this.getData();
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