<template>
<div class="profile-box">
    <div class="info">
    <div style="display:inline; float:left; justify-content:center; margin-left:40px; margin-top:30px;">
    <div><div>Adventure Name: </div><input type="text" name="" id="advName"></div>
    <div><div>Address: </div><input type="text" name="" id="advAddr" ></div>
    <div><div>Ratings:</div><input type="text" name="" id="advRat"></div>
    <div><div>Fishing equipment:</div><input type="text" name="" id="equip"></div>
    </div>
    <div class="promo"><div>Promo:</div><textarea name="" id="advPromo" cols="30" rows="6"/></div>        
    </div>
    
    <div class="map" ref="map" id="map-box">

    </div>
    <div>
        <button v-if="loggedIn" class="buttons" @click="subscripe()">Subscripe</button>
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
    props:[
        'id'
    ],
    data(){
        return {
            map:{},
            layer:{},
            adventure:{},
            address: '',
            get loggedIn(){
                return localStorage.getItem('logedIn')
            },
            set loggedIn(val){
                this.loggedIn=val;
            } 
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
            //getCoordinates(this.item.address)
            this.getCoordinates(this.adventure.address.street + ' '+this.adventure.address.country)
        },
        showMapEvent(){
            document.getElementById("map-box").classList.toggle("map-show")
            this.createMap();
        },
        populateData(response){
            this.adventure=response.data
            document.getElementById('advName').setAttribute('value',this.adventure.name)
            document.getElementById('advAddr').setAttribute('value',this.adventure.address.street + ' '+this.adventure.address.country )
            document.getElementById('advRat').setAttribute('value','10')
            document.getElementById('equip').setAttribute('value',this.adventure.fishingEquipment)
            document.getElementById('advPromo').append(this.adventure.description)
        },
        back(){
            this.$router.push('/')
        },
        subscripe(){
            axios.post('http://localhost:8080/subscripeTutorService',this.adventure).then(response=>alert(response.data))
        }  
    },
    mounted(){ 
        if(this.id){      
            axios.get('http://localhost:8080/api/users/tutors/services/'+ this.id).then(response=>this.populateData(response)
            )           
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

</style>