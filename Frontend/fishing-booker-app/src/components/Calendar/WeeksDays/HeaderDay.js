import React from 'react'
import moment from 'moment'
const HeaderDay = ({dayDate,className}) => {
    return (
        <li className={className}>
            <a href="javascript:;">
                <b>{dayDate.format("MMM DD")}</b>
                <span>{dayDate.format('dddd')}</span>
            </a>
        </li>
    )
}

export default HeaderDay
//<!-- <i>7.5</i> -->