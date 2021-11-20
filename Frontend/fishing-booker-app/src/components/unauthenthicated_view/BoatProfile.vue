<template>
<div class="profile-box">
    <div class="info">
    <div style="display:inline; float:left; justify-content:center; margin-left:40px; margin-top:30px;">
    <div><div>Name: </div><input type="text" name="" id=""></div>
    <div><div>Address: </div><input type="text" name="" id=""></div>
    <div><div>Ratings:</div><input type="text" name="" id=""></div>
    <div><div>Engine power:</div><input type="text" name="" id=""></div>
    </div>
    <div class="promo"><div>Promo:</div><textarea name="" id="" cols="30" rows="6"/></div>
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
            <tbody class="tbl-content">
                <tr><td>sdafdsg</td><td>sdfsr</td></tr>
            </tbody>
        </table>
    </div>
    <div class="prices">
        <table>
            <thead>
                <tr><th>Free time period:</th></tr>
            </thead>
            <tbody>
                <tr><td>asasa</td></tr>
            </tbody>
        </table>
    </div>
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