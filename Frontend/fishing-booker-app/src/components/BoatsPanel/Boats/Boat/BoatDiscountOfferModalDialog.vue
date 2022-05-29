<template>
  <div>
    <w-dialog
      v-model="dialog.show"
      :fullscreen="dialog.fullscreen"
      :width="dialog.width"
      :persistent="dialog.persistent"
      :persistent-no-animation="dialog.persistentNoAnimation"
      title-class="backgorund-color: green;"
    >
      <template #title> Discount Offer </template>
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Validity period</p>
        </div>
      </w-flex>
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Start date:</p>
        </div>
        <div class="xs6 pa1">
          <w-flex wrap class="text-center">
            <div class="xs6 pa1">
              <w-input type="date" v-model="discountOfferLocal.validityPeriod.startDate" :min="minDate"/>
            </div>
            <div class="xs6 pa1">
              <w-input type="text" v-model="discountOfferLocalTime.validityPeriod.startTime" placeholder="12:00" />
            </div>
          </w-flex>
        </div>
      </w-flex>
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>End date:</p>
        </div>
        <div class="xs6 pa1">
          <w-flex wrap class="text-center">
            <div class="xs6 pa1">
              <w-input type="date" v-model="discountOfferLocal.validityPeriod.endDate"  :min="discountOfferLocal.validityPeriod.startDate"/>
            </div>
            <div class="xs6 pa1">
              <w-input type="text" v-model="discountOfferLocalTime.validityPeriod.endTime" placeholder="12:00" />
            </div>
          </w-flex>
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Reservation period</p>
        </div>
      </w-flex>
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Start date:</p>
        </div>
        <div class="xs6 pa1">
          
          <w-flex wrap class="text-center">
            <div class="xs6 pa1">
              <w-input type="date" v-model="discountOfferLocal.reservationPeriod.startDate"  :min="discountOfferLocal.validityPeriod.endDate"/>
            </div>
            <div class="xs6 pa1">
              <w-input type="text" v-model="discountOfferLocalTime.reservationPeriod.startTime" placeholder="12:00" />
            </div>
          </w-flex>
        </div>
      </w-flex>
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>End date:</p>
        </div>
        <div class="xs6 pa1">
          
          <w-flex wrap class="text-center">
            <div class="xs6 pa1">
              <w-input type="date" v-model="discountOfferLocal.reservationPeriod.endDate"  :min="discountOfferLocal.reservationPeriod.startDate"/>
            </div>
            <div class="xs6 pa1">
              <w-input type="text" v-model="discountOfferLocalTime.reservationPeriod.endTime" placeholder="12:00" />
            </div>
          </w-flex>
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Max num of persons:</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="number" v-model="discountOfferLocal.maxPerson" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Additional services</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="text" v-model="discountOfferLocal.additionalServices" />
        </div>
      </w-flex>

      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <p>Price:</p>
        </div>
        <div class="xs6 pa1">
          <w-input type="number" v-model="discountOfferLocal.price" />
        </div>
      </w-flex>

      <template #actions>
        <div class="spacer" />
        <w-button class="mr2" @click="hideDialog" bg-color="error">
          Discard
        </w-button>
        <w-button @click="save"  bg-color="success"> Save </w-button>
      </template>
    </w-dialog>
  </div>
</template>
<script>
import axios from "axios";
import config from "../../../../configuration/config";
import moment from 'moment';
export default {
  props: ["show", "idboat"],
  data() {
    return {
      dialog: {
        show: false,
        fullscreen: false,
        persistent: true,
        persistentNoAnimation: false,
        width: 800,
      },
      minDate: moment().format("YYYY-MM-DD"),
      success: false,
      clients: [],
      boats: [],
      discountOfferLocalTime:{
        validityPeriod: {
          startTime: "",
          endTime: "",
        },
        reservationPeriod: {
          startTime: "",
          endTime: "",
        },
        
      },
      discountOfferLocal: {
        validityPeriod: {
          startDate: "",
          endDate: "",
        },
        reservationPeriod: {
          startDate: "",
          endDate: "",
        },
        maxPerson: "",
        additionalServices: "",
        price: "",
      },
    };
  },
  mounted() {},
  methods: {
    hideDialog() {
      this.dialog.show = false;
      this.$emit("hideDialog", {
        dialog: this.dialog.show,
        success: this.success,
      });
    },
    save() {
      this.discountOfferLocal.validityPeriod.startDate+=` ${this.discountOfferLocalTime.validityPeriod.startTime}:00`
      this.discountOfferLocal.validityPeriod.endDate+=` ${this.discountOfferLocalTime.validityPeriod.endTime}:00`
      this.discountOfferLocal.reservationPeriod.startDate+=` ${this.discountOfferLocalTime.reservationPeriod.startTime}:00`
      this.discountOfferLocal.reservationPeriod.endDate+=` ${this.discountOfferLocalTime.reservationPeriod.endTime}:00`
      axios
        .post(
          config.apiStart +
            "/api/boats/" +
            this.idboat +
            "/discount-offers",
          this.discountOfferLocal,
          config.requestHeader
        )
        .then(()=>alert("done"));
      console.log(this.discountOfferLocal);
       window.location.reload();
    },
  },
  watch: {
    show: {
      immediate: true,
      handler(fromProp) {
        if (fromProp) this.dialog.show = fromProp;
      },
    },
  },
};
</script>
<style>
p {
  color: black;
}
</style>
