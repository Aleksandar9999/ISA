<template><div>
<div v-if="revisionBox">
<button @click="makeRevision()">Send Revision</button>
</div>

<div class="searchBox">
  <select name="sort" id="sort">
    <option value="date_asc">Sort by date ascending</option>
    <option value="date">Sort by date descending</option>
    <option value="duration_asc">Sort by duration ascending</option>
    <option value="duration">Sort by duration descending</option>
    <option value="price_asc">Sort by price ascending</option>
    <option value="price">Sort by price descending</option>
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
                <tr><td>{{item.id}}</td><td>{{item.start}}</td><td>{{item.price}}</td><td>{{item.duration}}</td><td><button @click="giveRate(item)">Rate</button></td></tr>
          </tbody>                   
      </table>
      </div>     
      </div>
          </div>
</template>

<script>
import axios from 'axios'

export default {
    data(){
        return{
            headerList:['ID','Date','Price','Duration','Give a rate'],
            dataList:[],
            revision:{},
            revisionBox: false,
            appointmentForRevision:{},
            revisionText:'',
            rate:0
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
            if (criteria === 'date_asc'){
                this.dataList.sort((a,b)=> (a.name>b.name) ? 1 :(b.name>a.name) ? -1 :0);
            } else
            if(criteria === 'date'){
                this.dataList.sort((a,b)=> (a.name<b.name) ? 1 :(b.name<a.name) ? -1 :0);
            } else
            if (criteria === 'duration_asc'){
                this.dataList.sort((a,b)=> (a.boatAddress.city>b.boatAddress.city) ? 1 :(b.boatAddress.city>a.boatAddress.city) ? -1 :0);
            } else
            if(criteria === 'duration'){
                this.dataList.sort((a,b)=> (a.boatAddress.city<b.boatAddress.city) ? 1 :(b.boatAddress.city<a.boatAddress.city) ? -1 :0);
            }else
            if (criteria === 'price_asc'){
                this.dataList.sort((a,b)=> (a.rate>b.rate) ? 1 :(b.rate>a.rate) ? -1 :0);
            } else
            if(criteria === 'price'){
                this.dataList.sort((a,b)=> (a.rate<b.rate) ? 1 :(b.rate<a.rate) ? -1 :0);
            }
        },
        arrangeData(response){
          this.dataList=[]
          let brojac =0;
          for(let i = 0; i<response.length; i++){
            if(response.appointType==='RESORT'){
              this.dataList[brojac]=response[i]
              brojac++
            }
          }
        },
        giveRate(appointment){
          this.appointmentForRevision=appointment;
          this.revisionBox=true;
        },
        collectData(){
          if(this.validate()){
            this.revision.comment=this.revisionText;
            this.revision.rate=this.rate;
            this.revision.resortAppointment=this.appointmentForRevision;
            this.revision.status='PENDING'
          }
          else return;
        },
        makeRevision(){
          this.collectData();
          axios.post('http://localhost:8080/api/revision/makeResortRevision',this.revision).then(response=>this.foo(response) )
        },
        foo(response){
          console.log(response.data)
          this.revisionBox=false;
        }
    },
    mounted(){
        axios.get('http://localhost:8080/getAppointmentHistory').then(response =>
        this.arrangeData(response.data))
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