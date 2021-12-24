import moment from 'moment';
import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { getAllCategories } from '../../../actions/CategoriesActions';
import { getAllClients } from '../../../actions/ClientsActions';
import { getAllProjects } from '../../../actions/ProjectsActions';
import { addNewTask } from '../../../actions/TasksActions';
const TaskItem = ({ item }) => {
	const state = useSelector(state => state);
	const [showSubmit, setShowSubmit] = useState(false)
	const [task, setTask] = useState(item ? item : {
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

	});
	const dispatch = useDispatch();
	const showSubmitCheck = () => {
		task.clientId !== '' && task.projectId !== '' && task.categoryName !== '' && task.time !== '' && !item ? setShowSubmit(true) : setShowSubmit(false);
	}

	useEffect(() => {
		showSubmitCheck();
	}, [task])

	useEffect(() => {
		dispatch(getAllClients())
		dispatch(getAllProjects())
		dispatch(getAllCategories())
	}, [])
	return (
		<tr>
			<td>
				<select value={task.clientId} onChange={(e) => setTask({ ...task, clientId: e.target.value })}>
					<option>Choose client</option>
					{
						state.clientsReducer.clients.map(client => {
							return <option key={client.id} value={client.id}>{client.name}</option>
						})
					}
				</select>
			</td>
			<td>
				<select value={task.projectId} onChange={(e) => { setTask({ ...task, projectId: e.target.value }); showSubmitCheck() }}>
					<option>Choose project</option>
					{
						state.projectsReducer.projects.filter(project => project.clientId.toString().includes(task.clientId)).map(project => {
							return <option key={project.id} value={project.id}>{project.name}</option>
						})
					}
				</select>
			</td>
			<td>
				<select value={task.categoryName} onChange={(e) => { setTask({ ...task, categoryName: e.target.value }); showSubmitCheck() }}>
					<option>Choose category</option>
					{
						state.categoriesReducer.categories.map(category => {
							return <option key={category.name} value={category.name}>{category.name}</option>
						})
					}
				</select>
			</td>
			<td>
				<input type="text" className="in-text medium" value={task.description} onChange={(e) => setTask({ ...task, description: e.target.value })} />
			</td>
			<td className="small">
				<input type="text" className="in-text xsmall" value={task.time} onChange={(e) => { setTask({ ...task, time: e.target.value }); showSubmitCheck() }} />
			</td>
			<td className="small">
				<input type="text" className="in-text xsmall" value={task.overtime} onChange={(e) => setTask({ ...task, overtime: e.target.value })} />
			</td>

			{
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
				</td> : <></>
			}

		</tr>
	)
}

export default TaskItem