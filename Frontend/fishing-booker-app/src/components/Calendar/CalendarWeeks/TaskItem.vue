<template lang="">

		<tr>
			<td>
				<p>{{getStartDate()}}</p>
			</td>
			<td>
				<p>{{item.duration}} days</p>
			</td>
			<td>
				<p>{{item.price}}</p>
			</td>
			<td>
				<p>{{item.tutorService.name}}</p>
			</td>
			<td>
				<p>{{item.user.name}} {{item.user.surname}}</p>
			</td>
		</tr>
</template>
<script>
import moment from 'moment'
export default {
	props:['item'],
	data() {
		return {
			showSubmitButton:false,
			task:{}
		}
	},
	methods: {
		getStartDate(){return moment(this.item.start).format("MMM DD, YYYY")}
	},
}
</script>

