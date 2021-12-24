import React from 'react'
import HeaderDays from './HeaderDays'
import TasksTable from './TasksTable'
import TotalHours from '../TotalHours'
import { useParams } from 'react-router'
import moment from 'moment'
import { useSelector } from 'react-redux'
const TimesheetDays = () => {
	const {date} = useParams();
	const timesheetReducer = useSelector(state => state.timesheetReducer)
    return (
        <>
            <div className="wrapper">
			<section className="content">
				<h2><i className="ico timesheet"></i>TimeSheet</h2>
				<div className="grey-box-wrap">
					<div className="top">
						<a href="javascript:;" className="prev"><i className="zmdi zmdi-chevron-left"></i>previous week</a>
						<span className="center">February 04 - February 10, 2013 (week 6)</span>
						<a href="javascript:;" className="next">next week<i className="zmdi zmdi-chevron-right"></i></a>
					</div>
					<HeaderDays date={date}></HeaderDays>
				</div>
				<TasksTable date={date}/>
			</section>			
		</div>
        </>
    )
}

export default TimesheetDays
