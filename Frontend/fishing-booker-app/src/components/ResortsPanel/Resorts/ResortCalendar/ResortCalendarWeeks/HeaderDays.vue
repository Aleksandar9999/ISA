<template>
    <div class="bottom">
                <ul class="days">
                    <HeaderDay v-for="date in dates" :key=date :dayDate=date.date 
                    :class=headerDayClassName(date.reserved) />
                </ul>
            </div>
</template>
<script>
import HeaderDay from './HeaderDay.vue'
import moment from 'moment'
import config from '../../../../../configuration/config'
export default {
    props:['dateProp'],
    components:{
        HeaderDay
    },
    data() {
        return {
            dates:[],
            dateLocal:'',
            datesReserved:[]
        }
    },
    methods: {
        headerDayClassName(reserved){
            return reserved?'active':''
            
        }
    },
    async beforeMount()  {
        this.dateLocal= this.$route.params.date
        this.dateLocal=moment(this.dateLocal);
    },
    mounted() {
        let startDate = this.dateLocal.clone();
        let endDate = this.dateLocal.clone().add(6,'days');
        let rola='resort';
       
        this.$axios.get(`${config.apiStart}/api/appointments/${rola}/${this.$route.params.idresort}/calendar/month?startDate=${startDate.format("YYYY-MM-DD")}&endDate=${endDate.format("YYYY-MM-DD")}`)
        .then(resp=>{
            this.datesReserved=resp.data;
            console.log(this.datesReserved)
            resp.data.forEach(element => {
                this.dates.push({date:moment(element.date),reserved:element.reservedHours})
            });
        });
        
        
        
    },   
    
}
</script>
<style scoped>

</style>