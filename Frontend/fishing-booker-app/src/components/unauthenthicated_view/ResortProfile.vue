<template>
<div class="profile-box">
    <div class="info">
    <div style="display:inline; float:left; justify-content:center; margin-left:40px;">
    <div><div>Name: </div><input type="text" name="" id=""></div>
    <div><div>Address: </div><input type="text" name="" id=""></div>
    <div><div>Ratings:</div><input type="text" name="" id=""></div>
    </div>
    <div class="promo"><div>Promo:</div><textarea name="" id="" cols="30" rows="6"></textarea></div>   
    </div>
    <div class="map" ref="map" id="map-box">

    </div>
    <div>
        <button class="buttons">Back</button>
        <button class="buttons">See address on map</button>
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
        'item'
    ],
    data(){
        return {
            map:{},
            layer:{}
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
            this.getCoordinates('Zivojina Misica Ljubovija')
        },
        showMapEvent(){
            document.getElementById("map-box").classList.toggle("map-show")
            this.createMap();
        }   
        },
    mounted(){
    }
}
</script>

<style scoped>
.profile-box{
    background:linear-gradient(to right,#1c610d,rgb(34, 202, 12));
    border-radius: 20px;
    align-items: left;
    justify-content: left;
    display: inline-block;
    width: 40%;
    height: auto;
    padding: 20px;
    margin: 5%;
}

.info{
    margin-bottom: 30px;
    border: solid 4px black;
    border-radius: 9px;
    height: 180px;
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