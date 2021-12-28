<template>
    <div className="bottom">
                <ul className="days">
                    <HeaderDay v-for="date in dates" :key=date :dayDate=date 
                    :className=headerDayClassName(date) />
                </ul>
            </div>
</template>
<script>
import HeaderDay from './HeaderDay.vue'
import moment from 'moment'
export default {
    props:['dateProp'],
    components:{
        HeaderDay
    },
    data() {
        return {
            dates:[],
            dateLocal:''
        }
    },
    methods: {
        headerDayClassName(date){
            return this.dateLocal.isSame(moment(date))?'active':''
        }
    },
    beforeMount() {
        this.dateLocal= this.dateProp?this.dateProp:this.$route.params.date
        this.dateLocal=moment(this.dateLocal)
    },
    mounted() {
        let startDate = this.dateLocal.clone().subtract(3, 'd');
        let endDate = this.dateLocal.clone().add(3, 'd');
        for(startDate;startDate.isSameOrBefore(endDate); startDate= startDate.clone().add(1,'d'))
           this.dates.push(startDate);
        
    },   
    
}
</script>