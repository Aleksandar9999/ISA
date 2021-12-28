<template lang="">
	<table className="default-table">
				<tr>
					<th>
						Client <em>*</em>
					</th>
					<th>
						Project <em>*</em>
					</th>
					<th>
						Category <em>*</em>
					</th>
					<th>Description</th>
					<th className="small">
						Time <em>*</em>
					</th>
					<th className="small">Overtime</th>
				</tr>
				<TaskItem />
			</table>
</template>
<script>
import TaskItem from './TaskItem.vue'
export default {
	props:['date'],
	components:{TaskItem},
	mounted() {
		console.log("TasksTable");
	},
}
</script>

