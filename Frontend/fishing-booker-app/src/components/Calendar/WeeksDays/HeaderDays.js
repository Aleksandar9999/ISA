import React from 'react'
import HeaderDay from './HeaderDay'
import moment from 'moment'
const HeaderDays = ({ date }) => {
    let startDate = moment(date).subtract(3, 'd');
    let endDate = moment(date).add(3, 'd');
    const createHeder = () => {
        let res = [];
        for (startDate; startDate.isSameOrBefore(endDate); startDate.add(1, 'd')) {
            res.push(<HeaderDay className={startDate.clone().isSame(moment(date)) ? 'active' : ''} 
                                key={startDate.clone()} dayDate={startDate.clone()
                                }/>)
        }
        return res;
    }
    return (
        <>
            <div className="bottom">
                <ul className="days">
                    {createHeder()}
                </ul>
            </div>
        </>
    )
}

export default HeaderDays