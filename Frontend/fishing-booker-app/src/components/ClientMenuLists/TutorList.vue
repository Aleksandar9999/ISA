<template>
<div>
<div class="searchBox">
  <select name="sort" id="sort">
    <!--<option value="name_asc">Sort by name ascending</option>
    <option value="name">Sort by name descending</option>-->
    <option value="advname_asc">Sort by adventure name ascending</option>
    <option value="advname">Sort by adventure name descending</option>
    <option value="location_asc">Sort by location ascending</option>
    <option value="location">Sort by location descending</option>
    <option value="rate_asc">Sort by rate ascending</option>
    <option value="rate">Sort by rate descending</option>
  </select>
  <button @click="sort()">Sort</button>
</div>
<div class="grid-container" id="tabela">
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
                <tr><td>{{item.id}}</td>
                    <td>{{item.name}}</td>
                    <td>{{item.maxPerson}}</td>
                    <td>{{item.address.city}}</td>
                    <td>{{item.rate}}</td>
                </tr>
          </tbody>                   
      </table>
      </div>     
      </div></div>
</template>

<script>
import axios from 'axios'

export default {
    data(){
        return{
            headerList:['ID','Adventure Name','Max Persons','City','Rate'],
            dataList:[],
            jwtToken:''
        }
    },
    methods:{
        sort(){
            let criteria = document.getElementById('sort');
            criteria=criteria.options[criteria.selectedIndex].value
            if(!criteria){
                alert('Pick sort type first!');
                return;
            }
            /*if (criteria === 'name_asc'){
                this.dataList.sort((a,b)=> (a.tutor.name>b.tutor.name) ? 1 :(b.tutor.name>a.tutor.name) ? -1 :0);
            } else
            if(criteria === 'name'){
                this.dataList.sort((a,b)=> (a.tutor.name<b.tutor.name) ? 1 :(b.tutor.name<a.tutor.name) ? -1 :0);
            } else*/
            if (criteria === 'advname_asc'){
                this.dataList.sort((a,b)=> (a.name>b.name) ? 1 :(b.name>a.name) ? -1 :0);
            } else
            if(criteria === 'advname'){
                this.dataList.sort((a,b)=> (a.name<b.name) ? 1 :(b.name<a.name) ? -1 :0);
            } else
            if (criteria === 'location_asc'){
                this.dataList.sort((a,b)=> (a.address.city>b.address.city) ? 1 :(b.address.city>a.address.city) ? -1 :0);
            } else
            if(criteria === 'location'){
                this.dataList.sort((a,b)=> (a.address.city<b.address.city) ? 1 :(b.address.city<a.address.city) ? -1 :0);
            }else
            if (criteria === 'rate_asc'){
                this.dataList.sort((a,b)=> (a.rate>b.rate) ? 1 :(b.rate>a.rate) ? -1 :0);
            } else
            if(criteria === 'rate'){
                this.dataList.sort((a,b)=> (a.rate<b.rate) ? 1 :(b.rate<a.rate) ? -1 :0);
            }
        }
    },
    mounted(){
        this.dataList=[]
        this.jwtToken=localStorage.jwtToken
        axios.get('http://localhost:8080/api/users/tutors/servicesforList',{headers:{'Authorization':'Bearer '+ this.jwtToken}}).then(response =>
        this.dataList=response.data)
    }
}
</script>

<style scoped>
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
    height:  auto;
    transition: 0.8s;
  }
  .searchBox button:hover{
    background-color: rgb(10, 226, 28);
  }
</style>