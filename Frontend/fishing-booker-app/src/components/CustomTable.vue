<template lang="">
  <div>
    <div class="grid-container" id="tabela">
      <div>
        <table class="r-table" cellspacing="0" cellpadding="0" border="0">
          <thead>
            <tr>
              <th v-for="header in headerList" :key="header">
                {{ header }}
              </th>
            </tr>
          </thead>
          <tbody class="tbl-content" v-for="item in data_local" :key="item">
            <itemComp :item=item />
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    headerList: {
      type: Array,
    },
    dataList: { type: Array },
    itemRow: { type: Object },
  },
  components: {},
  data() {
    return {
      data_local:[]
    }
  },
  mounted() {
    this.$options.components.itemComp = this.itemRow;
    this.$forceUpdate(); //Koristi se da bi renderovao proslijedjenu komponentu
  },
  watch:{
    dataList:{
      immediate:true,
      handler(itemFromProps) {
        if (itemFromProps) {
          this.data_local = {
            ...this.data_local,
            ...itemFromProps,
          };
       }
      }
    }
  }

};
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

.r-table thead {
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
  background: rgba(0, 0, 0, 0.1);
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

.searchBox {
  display: flex;
  justify-content: center;
  margin: 5px;
  padding: 1%;
  border: solid 4px rgb(5, 100, 76);
  border-radius: 25px;
  background-color: green;
}

.searchBox input {
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
  height: 30px;
  transition: 0.8s;
}
.searchBox button:hover {
  background-color: rgb(10, 226, 28);
}

.advanced-drop {
  display: flex;
  justify-content: center;
}

.advanced-drop-data {
  display: none;
  width: 60%;
  height: 160px;
  border: solid 3px black;
  background-color: white;
  border-radius: 5px;
}

.show-drop {
  display: block;
}

.advanced-btn {
  font-size: 18px;
  border: 3px black;
  padding: 4px 5px;
  color: whitesmoke;
  background-color: rgb(15, 71, 10);
  margin-left: 10px;
  border-radius: 15px;
  width: 160px;
  height: 30px;
  transition: 0.8s;
}

.advanced-btn:hover {
  background-color: rgb(10, 226, 28);
}

.adv-div {
  margin: 0.5%;
  height: 100px;
}

.adv-div input {
  justify-content: left;
  display: inline-block;
  align-items: left;
  padding: 5px;
  margin: 5px;
  width: 40px;
}

.adv-div select {
  margin: 6px;
}
</style>
