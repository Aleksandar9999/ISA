<template>
<div class="profile-box">
    <div class="info">
    <div style="display:inline; float:left; justify-content:center; margin-left:40px; margin-top:30px;">
    <div><div>Name: </div><input type="text" name="" id="btName"></div>
    <div><div>Address: </div><input type="text" name="" id="btAddres"></div>
    <div><div>Ratings:</div><input type="text" name="" id="btRat"></div>
    <div><div>Engine power:</div><input type="text" name="" id="btEng"></div>
    </div>
    <div class="promo"><div>Promo:</div><textarea name="" id="btPromo" cols="30" rows="6"/></div>
    <div>
        <div class="promo"><div>Additional services:</div><textarea name="" id="" cols="30" rows="6"/></div> 
    </div>     
    </div>
    

    <div class="tables">
    <div class="prices">
        <table>
            <thead>
                <tr><th>Service</th><th>Price</th></tr>
            </thead>
            <tbody class="tbl-content" v-for="item in extras" :key="item">
                <tr><td>{{item.service}}</td><td>{{item.price}}</td></tr>
            </tbody>
        </table>
    </div>
    <div class="prices">
        <table>
            <thead>
                <tr><th>Free time period:</th></tr>
            </thead>
            <tbody>
                <tr><td>Not implemented yet</td></tr>
            </tbody>
        </table>
    </div>
    </div>
    <div class="map" ref="map" id="map-box">

    </div>
    <div>
        <button class="buttons" @click="back()">Back</button>
        <button class="buttons" @click="showMapEvent()">See address on map</button>
    </div>
</div>
</template>

<script>
import Map from 'ol/Map'
import Tile from 'ol/layer/Tile'
import OSM from 'ol/source/OSM'
import View from 'ol/View'
import {fromLonLat} from 'ol/proj'
import Vector from 'ol/layer/Vector'
import Vectorr from 'ol/source/Vector'
import Point from 'ol/geom/Point'
import Feature from 'ol/Feature'
import axios from 'axios'


export default {
    props:['id'],
    data(){
        return {
            map:{},
            layer:{},
            boat:{},
            extras:[],
            periods:[]
        }
    },
    methods:{
        drawMap(lat,lng){           
            this.map = new Map({
                target: this.$refs['map'],
                layers: [
                new Tile({
                    source: new OSM()
                })
                ],
                view: new View({
                center: fromLonLat([lng,lat]),
                zoom: 18
                })           
            });
            this.layer = new Vector({
            source: new Vectorr({
                features: [
                    new Feature({
                        geometry: new Point(fromLonLat([lng,lat]))
                    })
                ]
            })
            });
            this.map.addLayer(this.layer);
            },
        getCoordinates(address){
            axios.get('http://api.positionstack.com/v1/forward', { params:{access_key:'03cbb05a85b189850d248751c1e630a6',query:address}}).then(
            response=>this.drawMap(response.data.data[0].latitude,response.data.data[0].longitude)                           
            ).catch(error => console.log(error))
        },
        createMap(){
            this.getCoordinates(this.boat.boatAddress.street+ ', '+this.boat.boatAddress.city + ', '+this.boat.boatAddress.country)
        },
        showMapEvent(){
            document.getElementById("map-box").classList.toggle("map-show")
            this.createMap();
        },
        populateData(response){
            this.boat=response.data
            document.getElementById('btName').setAttribute('value',this.boat.name)
            document.getElementById('btAddres').setAttribute('value',this.boat.boatAddress.street + ', '+ this.boat.boatAddress.city + ', '+this.boat.boatAddress.country )
            document.getElementById('btRat').setAttribute('value','10')
            document.getElementById('btEng').setAttribute('value',this.boat.enginePower)
            document.getElementById('btPromo').append(this.boat.description)
        },
        getExtras(response){
            this.extras=response.data;
            for(let i=0; i<this.extras.length; i++){
                if(this.extras[i].boat.id!=this.id){                    
                    this.extras.splice(i,1)
                    i--
                }
            }
        },
        getPeriods(response){
            this.periods=response.data
            for(let i=0; i<this.periods.length; i++){
                if(this.periods[i].boat_id!=this.id){                    
                    this.periods.splice(i,1)
                    i--
                }
            }
        },
        back(){
            this.$router.push('/')
        }  
    },
    mounted(){
        if(this.id){      
            axios.get('http://localhost:8080/boats/'+ this.id).then(response=>this.populateData(response)
            )
            axios.get('http://localhost:8080/extras').then(response=>this.getExtras(response))  
            axios.get('http://localhost:8080/periods').then(response=>this.getPeriods(response))             
        }
    }
}
</script>

<style scoped>
.profile-box{
    background:linear-gradient(to right,#03ca35,rgb(6, 228, 135));
    border-radius: 20px;
    align-items: left;
    justify-content: left;
    display: inline-block;
    width: 60%;
    height: auto;
    padding: 20px;
    margin: 5%;
}

.info{
    margin-bottom: 30px;
    border: solid 4px black;
    border-radius: 9px;
    height: 290px;
}

.info label{
    float: left;
    margin-left: 150px;
}

.info input{
    margin:5px;
    align-items: left;
    justify-content: left;
}
.promo input{
    width: 300px;
    height: 110px;
}
.map{
    display: none;
    width: 50%;
    height: 250px;
    margin-left: 25%;
    margin-right: 10%;
    margin-bottom: 50px;
    background-color:white;
    margin-top: 50px;
}

.map-show{
    display: block;
}

.buttons {
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
  .buttons:hover{
    background-color: rgb(10, 226, 28);
  }

  .prices{
    width: 20%;
    background-color: white;
    table-layout: auto;
    margin-left: 20%;
    margin-right: 20%;
    margin-top: 5px;
    margin-bottom: 5px;
    border: 1px solid rgba(0, 0, 0, 0.1);
  }

  .prices th {
    text-align: center;
    font-weight: bold;
    width: 80px;
    font-size: 13px;
    color: #000;
    text-transform: uppercase;
    background:rgba(0, 0, 0, 0.1);
  }

  .prices td {
    padding: 13px;
    text-align: center;
    vertical-align: middle;
    font-weight: 300;
    font-size: 18px;
    color: #000;
    border-bottom: solid 1px rgba(0, 0, 0, 0.1);
    word-wrap: break-word;
  }

.tbl-content {
    min-height: auto;
    max-height: 150px;
    overflow-x: auto;
    margin-top: 0px;
    border: 1px solid rgba(0, 0, 0, 0.1);
  }

  .tables{
      display: flex;
      justify-content: space-evenly;
  }
</style>