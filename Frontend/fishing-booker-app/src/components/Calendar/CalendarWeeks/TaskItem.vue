<template lang="">

		<tr>
			<td>
				<select v-model=task.clientId>
					<option>Choose client</option>
				</select>
			</td>
			<td>
				<select v-model=task.projectId >
					<option>Choose project</option>
				</select>
			</td>
			<td>
				<select v-model=task.categoryName >
					<option>Choose category</option>
				</select>
			</td>
			<td>
				<input type="text" className="in-text medium" v-model=task.description />
			</td>
			<td className="small">
				<input type="text" className="in-text xsmall" v-model=task.time />
			</td>
			<td className="small">
				<input type="text" className="in-text xsmall" v-model=task.overtime />
			</td>

			<!--{
				showSubmit ? <td>
					<input type="button" value="SUBMIT" onClick={() => {
						dispatch(addNewTask({ task })); setTask({
							...task, clientId: '',
							clientName: '',
							projectId: '',
							projectName: '',
							categoryName: '',
							description: '',
							time: '',
							overtime: '',
						})
					}} />
				</td> : <td></td>
			}-->

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
	mounted() {
		this.task= this.item?this.item:{
		clientId: '',
		clientName: '',
		projectId: '',
		projectName: '',
		categoryName: '',
		description: '',
		time: '',
		overtime: '',
		taskDate: moment().format('yyyy-MM-DD'),
		userUsername: 'aco'
	};


	},
}
</script>

