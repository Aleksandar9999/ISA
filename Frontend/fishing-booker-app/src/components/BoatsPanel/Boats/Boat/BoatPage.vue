<template>
  <div>
    <BoatHeader
      :boat_info="boat_info"
      :showNewAppointmentDialog="showNewAppointmentDialog"
      :showAdminButton="showAdminButtons"
    />
    <Gallery
      :photos="gallery"
      :deleteFunction="deleteImage"
      @showDialog="this.photoDialog.show = true"
      :showAddButton="showAdminButtons"
    />
    <!--<ExtrasServices /> -->
    <BoatFastAppointments
      @showDiscountOfferDialog="discountOfferDialog.show = true"
      :idboat="idboat"
      :showAdminButton="showAdminButtons"
    />
    <boat-discount-offer-modal-dialog
      :show="discountOfferDialog.show"
      :idboat="idboat"
      @hideDialog="hideDiscountOfferDialog"
    />
    <boat-prices-list
      :idboat="idboat"
      @showDialog="this.priceDialog.show = true"
      :fetch="this.priceDialog.success"
      :showAddButton="showAdminButtons"
    />
    <boat-price-modal-dialog
      :idboat="idboat"
      :show="this.priceDialog.show"
      @hideDialog="checkForUpdatePrices"
    />
    <boat-photo-dialog
      :api="api"
      :show="photoDialog.show"
      @hideDialog="hidePhotoDialog"
    />
    <NewReservationDialog
      :idBoatOwner="idboatowner"
      :userId="userId"
      :selectBoat="idboat"
      :show="appointmentDialog.show"
      @hideDialog="hideNewAppointmentDialog"
    />

   <div class="map" ref="map" id="map-box"></div>
      <div>
        <button class="buttons" @click="showMapEvent()">See address on map</button>
    </div>
  </div>

  
</template>

<script>
import BoatHeader from "./BoatHeader.vue";
import Gallery from "../../../TutorPanel/TutorServices/TutorService/Gallery.vue";
import BoatExtrasServices from "./BoatExtrasServices.vue";
import BoatFastAppointments from "./BoatFastAppointments.vue";
import config from "../../../../configuration/config";
import BoatDiscountOfferModalDialog from "./BoatDiscountOfferModalDialog.vue";
import BoatPricesList from "./BoatPricesList.vue";
import BoatPriceModalDialog from "./BoatPriceModalDialog.vue";
import BoatPhotoDialog from "./BoatPhotoDialog.vue";
import NewReservationDialog from "../Reservations/NewReservationDialog.vue";
import Map from 'ol/Map'
import Tile from 'ol/layer/Tile'
import OSM from 'ol/source/OSM'
import View from 'ol/View'
import {fromLonLat} from 'ol/proj'
import Vector from 'ol/layer/Vector'
import Vectorr from 'ol/source/Vector'
import Point from 'ol/geom/Point'
import Feature from 'ol/Feature'
import "firebase/storage";
import axios from 'axios'

export default {
  components: {
    BoatHeader,
    Gallery,
    NewReservationDialog,
    BoatExtrasServices,
    BoatFastAppointments,
    BoatDiscountOfferModalDialog,
    BoatPricesList,
    BoatPriceModalDialog,
    BoatPhotoDialog,
  },
  data() {
    return {
      map:{},
      layer:{},
      discountOfferDialog: {
        show: false,
      },
      appointmentDialog: {
        show: false,
        success: false,
      },
      priceDialog: {
        show: false,
        success: false,
      },
      photoDialog: {
        show: false,
        success: false,
      },
      gallery: [],
      boat_info: {
        boatAddress: {
          country: "",
          street: "",
        },
      },
      idboat: this.$route.params.idboat,
      idboatowner: this.$route.params.idboatowner,
      userId: localStorage.id,
      extra_services: [],
      fast_appointments: [],
      showAdminButtons: false,
      api: `${config.apiStart}/api/boats/${this.$route.params.idboat}`,
    };
  },

  methods: {
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
                zoom: 13
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
        getCoordinates(boatAddress){
            axios.get('http://api.positionstack.com/v1/forward', { params:{access_key:'45de5b2b92f8621fa59de13c67664029',query:boatAddress}}).then(
            response=>this.drawMap(response.data.data[0].latitude,response.data.data[0].longitude)                           
            ).catch(error => console.log(error))
        },
        createMap(){
            this.getCoordinates(this.boat_info.boatAddress.street + ' '+ this.boat_info.boatAddress.city + ' '+this.boat_info.boatAddress.country )
        },
        showMapEvent(){
            document.getElementById("map-box").classList.toggle("map-show")
            this.createMap();
        },
    deleteImage(photo) {
      this.$axios.delete(`${this.api}/photos/${photo.id}`).then(() => {
        alert(`Deleted ${photo.title}`);
        this.fetchPhotos();
      });
    },
    hideNewAppointmentDialog(value) {
      this.appointmentDialog.show = false;
      if (value.success) {
        this.appointmentDialog.success = false;
      }
    },
    showNewAppointmentDialog() {
      this.appointmentDialog.show = true;
    },
    hidePhotoDialog(value) {
      this.photoDialog.show = false;
      if (value.success) {
        this.fetchPhotos();
        this.photoDialog.success = false;
      }
    },
    checkForUpdatePrices(value) {
      this.priceDialog.show = value.dialog;
      if (value.success) this.priceDialog.success = !this.priceDialog.success;
    },
    showAdminButtonsFunc() {
      if (!localStorage.roles) return;
      if (!localStorage.roles.includes("ROLE_BOATOWNER")) return;
      if (localStorage.id == this.idboatowner) this.showAdminButtons = true;
    },
    fetchPhotos() {
      this.$axios.get(`${this.api}/photos`).then((resp) => {
        this.gallery = resp.data;
        this.gallery.forEach((element) => {
          this.$firebaseStorage
            .ref(element.url)
            .getDownloadURL()
            .then((img) => {
              element.url = img;
            });
        });
      });
    },
    fetchData() {
      this.$axios.get(`${this.api}`).then((resp) => {
        this.boat_info = resp.data;
      });
      this.fetchPhotos();
    },
  },
  mounted() {
    this.fetchData();
    this.showAdminButtonsFunc();
  },
};
</script>
<style scoped>

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

</style>
