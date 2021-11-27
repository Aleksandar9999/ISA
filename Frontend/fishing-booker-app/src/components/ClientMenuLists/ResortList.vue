<template>
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
                <tr><td>{{item.id}}</td><td>{{item.name}}</td><td>{{item.resortAddress.street + ', '+ item.resortAddress.city + ', ' + item.resortAddress.country }}</td><td>{{item.numOfRooms}}</td><td><router-link :to="{name:profileName, params:{item} }">Page</router-link></td></tr>
          </tbody>                   
      </table>
      </div>     
      </div>
</template>

<script>
import axios from 'axios'

export default {
    data(){
        return{
            headerList:['ID','Name','Location','Number of rooms', 'Page'],
            dataList:[]
        }
    },
    methods:{

    },
    mounted(){
        this.dataList=[]
        axios.get('http://localhost:8080/resorts').then(response =>
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
</style>