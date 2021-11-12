<template>
    <div class="searchBox">
      <input type="text" placeholder="Type your search here">
      <button @click="search()">Search</button>
      <div>
        <button class="advanced-src" @click="advancedSrc()">Advanced search</button>
      </div>      
    </div>
    <div class="advanced-drop">
    <div id="dropMenu" class="advanced-drop-data">
            
        </div>
    </div>
    <div>
        <div class="tab">
             <button class="tablinks" v-on:click="changeTab('resorts')">Resorts</button>
             <button class="tablinks" v-on:click="changeTab('boats')">Boats</button>
             <button class="tablinks" v-on:click="changeTab('tutors')">Tutors</button>
         </div>
                  
    </div>

    <div class="grid-container" id="tabela">
      <div>        
      <table  class="r-table" cellspacing="0" cellpadding="0" border="0">
          <thead>
              <th v-for="header in headerList" :key="header">
                  <td>{{header}}</td>                
              </th>
          </thead>       
          <tbody class="tbl-content">
                
          </tbody>                   
      </table>
      </div>     
      </div>
     
</template>

<script>
export default {
    name: 'FrontPage',   
    data(){
      return{
        tabType: 'resort',
        resortHeader:['ID','Name','Location','Price'],
        boatsHeader:['ID','Manufacturer','Engine','HorsePowers'],
        tutorsHeader:['ID','Name','Surname'],
        headerList: this.resortHeader,
        dataList: []
    }
    } ,
    methods:{
      changeTab(type){
        this.tabType=type;
        if(type==='boats'){
          this.headerList=this.boatsHeader
        } else if(type==='tutors'){
          this.headerList=this.tutorsHeader
        } else {
          this.headerList=this.resortHeader
        }
        
      },
      loadBoats(){

      },
      loadResorts(){

      },
      loadTutors(){

      },
      search(){

      },
      advancedSrc(){
        document.getElementById("dropMenu").classList.toggle("show-drop")
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
    table-layout: auto;
    margin-left: 20%;
    margin-right: 20%;
    margin-top: 20px;
    background-color: white;
    margin-bottom: 150px;
    border: 1px solid rgba(0, 0, 0, 0.1);
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
  
  .r-table td img {
    
    
  }
  
  .r-table td img:hover {
    transform: scale(1.1);
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
    height: 100px;
    border: solid 3px black;
    background-color: white;
    border-radius: 5px;
  }

  .show-drop{
    display: block;
  }

</style>