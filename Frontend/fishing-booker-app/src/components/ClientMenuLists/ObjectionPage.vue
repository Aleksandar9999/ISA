<template>
<div v-if="pickEntity">
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
                        <tr><td>{{item.id}}</td><td>{{item.start}}</td><td>{{item.address.street + ' ' + item.address.country }}</td><td><button @click="makeObjection(item)">Make objection</button></td></tr>
                </tbody>                   
            </table>
        </div>     
    </div> 
  </div>


<div v-if="showObjectionField">
  <div>
    <label for="">Text of my objection:</label><br/>
    <input v-model="text" name="" id="objc" size="100"/><br/>
  </div>
  <div style="margin-top:30px;">
    <button @click="sendObjection()">Send objection</button>
  </div>
</div>  
 
</template>

<script>
import axios from 'axios'
export default {
    data(){
        return {
            pickEntity:true,
            showObjectionField:false,
            objection:{},
            text:'',
            entityType:'',
            entityId:-1,
            objectionEntity:{},
            headerList:['ID','Date','Location','Make objection'],
            dataList: [],
            reserveList: []
        }
    },
    methods:{
        pickEnt(){
            this.showObjectionField=true;
        },
        collectData(){
            this.objection.objection=this.text;
            this.objection.appointment=this.objectionEntity;
            this.objection.userEmail=localStorage.email;
        },
        makeObjection(item){
            this.objectionEntity=item;
            this.showObjectionField=true;
            this.pickEntity=false;
        },
        sendObjection(){
            this.collectData()
            axios.post('http://localhost:8080/sendObjection',this.objection).then(response=>console.log(response.data))
            this.showObjectionField=false;
            this.pickEntity=true;
        },
        changeTab(type){
        this.tabType=type;
        if(type==='boats'){
          this.headerList=this.boatsHeader
          this.getData()
        } else if(type==='tutors'){
          this.headerList=this.tutorsHeader
          this.getData()
        } else {
          this.headerList=this.resortHeader
          this.getData()
        }        
      },
      populateData(){
        for(let i =0; i<this.dataList.length; i++){
          if(this.dataList[i].user===null){
            this.dataList.splice(i,1);
            i--
          }
        }
        if(this.dataList.length>0){
        for(let i =0; i<this.dataList.length; i++){
          if(!this.dataList[i].user.email===localStorage.email){
            this.dataList.splice(i,1);
            i--
          }
        }
        }
        if(this.dataList.length>0){
          let date=Date.parse(new Date());  
          for(let i =0; i<this.dataList.length; i++){
            var startDate=this.formatDate(this.dataList[i].start);
              if(startDate>=date){
                this.dataList.splice(i,1);
                i--
              }
          }
        }

      },
      fillDataLists(response){
        this.dataList=response.data
        this.reserveList=response.data
        this.populateData()
      },
      formatDate(javaDate){
          let splitDate=javaDate.split("T")[0]
          let date= Date.parse(splitDate)
          return date
        },
    },
    mounted(){
      axios.get('http://localhost:8080/appointments').then(response =>
            this.fillDataLists(response))
    }
}
</script>

<style scoped>
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

</style>