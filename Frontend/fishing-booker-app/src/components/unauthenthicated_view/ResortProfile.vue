<template>
<div class="profile-box">
    <div class="info">
    <div style="display:inline; float:left; justify-content:center; margin-left:40px;">
    <div><div>Name</div><input type="text" name="" id=""></div>
    <div><div>Address</div><input type="text" name="" id=""></div>
    <div><div>Ratings:</div><input type="text" name="" id=""></div>
    </div>
    <div class="promo"><div>Promo:</div><input type="text" name="" id=""></div>   
    </div>
    <div class="map">

    </div>
    <div>
        <button class="buttons">Back</button>
        <button class="buttons">See address on map</button>
    </div>
    <div class="map"></div>
</div>
</template>

<script>
export default {
    props:[
        'item'
    ],
    data(){
        return {
            
        }
    },
    methods:{

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
    width: 60%;
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
    display: none;
}

.map-show{
    display: inherit;
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