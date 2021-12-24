import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { getAllCategories } from '../../../actions/CategoriesActions';
import { getAllClients } from '../../../actions/ClientsActions';
import { getAllProjects } from '../../../actions/ProjectsActions';
import { getAllTasks } from '../../../actions/TasksActions';
import TotalHours from '../TotalHours';
import TaskItem from './TaskItem'
import moment from 'moment';
const TasksTable = ({ date }) => {
	const state = useSelector(state => state);
	const dispatch = useDispatch();
	useEffect(() => {
		dispatch(getAllClients())
		dispatch(getAllProjects())
		dispatch(getAllCategories())
		dispatch(getAllTasks({ startDate: date }))
	}, [])
	const totalHoursValue = () => {
		let total = 0;
		state.tasksReducer.tasks.forEach(element => {
			total += element.time + element.overtime;
		});
		return total;
	}
	return (
		<>
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
				{
					state.tasksReducer.tasks.map(task => {
						return <TaskItem item={task} date={date} />
					})
				}
				{
					moment(date).format('YYYY-DD-MM') === moment().format('YYYY-DD-MM') ? 
					<><TaskItem /><TaskItem />
						<TaskItem />
						<TaskItem />
						<TaskItem />
						<TaskItem />
						<TaskItem /></> : <div></div>
				}

			</table>
			<TotalHours value={totalHoursValue()}></TotalHours>
		</>
	)
}

export default TasksTable
