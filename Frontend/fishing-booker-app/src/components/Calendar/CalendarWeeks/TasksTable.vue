<template lang="">
	<table className="default-table">
				<tr>
					<th>
						Start date
					</th>
					<th>
						Duration
					</th>
					<th>
						Price
					</th>
					<th>Service: </th>
					<th>
						User name and surname:
					</th>
				</tr>
				<TaskItem v-for="appointment in appointments" :key="appointment" :item=appointment />
			</table>
</template>
<script>
import config from '../../../configuration/config'
import TaskItem from './TaskItem.vue'
import moment from 'moment'
export default {
	props:['date'],
	data() {
		return {
			role:'tutor',
			startDate:this.$route.params.date,
			appointments:[]
		}
	},
	components:{TaskItem},
	mounted() {
		this.$axios.get(`${config.apiStart}/api/appointments/${this.role}?startDate=${moment(this.startDate).format("YYYY-MM-DD")}&type=week`)
		.then(resp=>{
			this.appointments=resp.data;
			console.log(resp.data)
		});
	},
}
</script>

