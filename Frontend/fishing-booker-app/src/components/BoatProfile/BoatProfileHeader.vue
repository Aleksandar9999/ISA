<template>
  <div>
    <w-card class="info-card" no-border>
      <w-form no-keyup-validation no-blur-validation>
        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Boat name</p>
          </div>
          <div class="xs6 pa1">
            <w-input
              class="mb3"
              style="color: black"
              v-model="boat_form.name"
              bg-color="white"
              border
              placeholder="Boat name"
              outline
            >
            </w-input>
          </div>
        </w-flex>

        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Boat type</p>
          </div>
          <div class="xs6 pa1">
            <w-input
              class="mb3"
              style="color: black"
              v-model="boat_form.typeOfBoat"
              bg-color="white"
              border
              placeholder="Boat type"
              outline
            >
            </w-input>
          </div>
        </w-flex>

        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Boat length</p>
          </div>
          <div class="xs6 pa1">
            <w-input
              class="mb3"
              style="color: black"
              v-model="boat_form.length"
              bg-color="white"
              border
              placeholder="Boat length"
              outline
            >
            </w-input>
          </div>
        </w-flex>

        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Boat engine</p>
          </div>
          <div class="xs6 pa1">
            <w-input
              class="mb3"
              style="color: black"
              v-model="boat_form.engineId"
              bg-color="white"
              border
              placeholder="Boat engine"
              outline
            >
            </w-input>
          </div>
        </w-flex>

        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Max speed</p>
          </div>
          <div class="xs6 pa1">
            <w-input
              class="mb3"
              style="color: black"
              v-model="boat_form.maxSpeed"
              bg-color="white"
              border
              placeholder="Max speed"
              outline
            >
            </w-input>
          </div>
        </w-flex>

        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Navigation equipment</p>
          </div>
          <div class="xs6 pa1">
            <w-input
              class="mb3"
              style="color: black"
              v-model="boat_form.navigationEquipment"
              bg-color="white"
              border
              placeholder="Nav equipment"
              outline
            >
            </w-input>
          </div>
        </w-flex>

        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Max people</p>
          </div>
          <div class="xs6 pa1">
            <w-input
              type="number"
              class="mb3"
              style="color: black"
              v-model="boat_form.maxPerson"
              bg-color="white"
              border
              placeholder="Max people"
              outline
            >
            </w-input>
          </div>
        </w-flex>

        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Cancel percentage</p>
          </div>
          <div class="xs6 pa1">
              <w-input
              type="number"
              class="mb3"
              style="color: black"
              v-model="boat_form.cancelPercentage"
              bg-color="white"
              border
              placeholder="Percentage"
              outline
            />
          </div>
        </w-flex>
        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p>Adress</p>
          </div>
          <div class="xs6 pa1">
              <w-input
              
              class="mb3"
              style="color: black"
              v-model="boat_form.boatAddress.country"
              bg-color="white"
              border
              placeholder="State"
              outline
            />
              <w-input
             
              class="mb3"
              style="color: black"
              v-model="boat_form.boatAddress.street"
              bg-color="white"
              border
              placeholder="Street"
              outline
            />
          </div>
        </w-flex>
        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p style="margin-top: 3%">Description</p>
          </div>
          <div class="xs6 pa1">
              <w-textarea
              class="mb3"
              style="color: black"
              v-model="boat_form.description"
              bg-color="white"
              border
              placeholder="Description"
              outline
            />
          </div>
        </w-flex>
        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p style="margin-top: 3%">Equipment</p>
          </div>
          <div class="xs6 pa1">
            
              <w-textarea
              class="mb3"
              style="text-alignment:center"
              color: black
              v-model="boat_form.fishingEquipment"
              bg-color="white"
              border
              placeholder="Fishing equipment"
              outline
            ></w-textarea>
            </div>
          </w-flex>
        <w-flex wrap class="text-center">
          <div class="xs6 pa1">
            <p style="margin-top: 3%">Rules</p>
          </div>
          <div class="xs6 pa1">
            <w-textarea
              class="mb3"
              style="text-alignment:center"
              color: black
              v-model="boat_form.rules"
              bg-color="white"
              border
              placeholder="Rules"
              outline
            ></w-textarea>
          </div>
        </w-flex>

        <div class="text-right mt6">
          <w-button type="submit" bg-color="green" color="white" @click="save">Save</w-button>
        </div>
      </w-form>
    </w-card>
  </div>
</template>

<script>
export default {
  props: ["boat_info"],
  data() {
    return {
      boat_form: {},
    };
  },
  methods: {
    save() {
      console.log(this.boat_form);
    },
    nameChanged($event) {
      this.boat_form.name = $event.data;
      this.$emit("input", this.boat);
    },
  },
  watch: {
    boat_info: {
      immediate: true,
      handler(boatFromProps) {
        if (boatFromProps) {
          this.boat_form = {
            ...this.boat_form,
            ...boatFromProps,
          };
        }
      },
    },
  },
};
</script>
<style scoped>
p {
  text-align: start;
}
.flex-container {
  display: flex;
}

.flex-child {
  flex: 1;
}
div.row {
  margin-top: 15px;
  margin-left: 25px;
}
.flex-child:first-child {
  margin-right: 10%;
}
input.text-box-forma {
  margin: 0%;
}
label {
  left: 0;
  right: 0;
  top: 5%;
}

.info-card {
  margin: 0% 10%;
  padding: 3%;
}
textarea{
  max-width: inherit;
  color: black;
  text-align: center;
}
</style>
