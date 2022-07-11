import React from 'react';
import styled from '@emotion/styled'

export const Table = ({ data }) => {
    const entries = Object.entries(data);

    if (!entries.length) {
		return null;
	}
	return (
		<Tables>
			<thead>
				<tr>
					<th>Name</th>
					<th>Value</th>
				</tr>
			</thead>
			<tbody>
				{entries.map(([name, value], index) => (
					<tr key={index}>
						<td>{name}</td>
						<td>{value}</td>
					</tr>
				))}
			</tbody>
		</Tables>
	);
}

const Tables = styled.table`
    width: 100%;
    border-collapse: collapse;
    overflow: hidden;

    &>thead {
        &>tr {
            &>th {
                padding: 0.6rem 2.0rem;
                width: 30%;
                color: #323e48;
                text-align: start;
                font-size: 1.2rem;
                background: #f7f7f7;
            }
        }
    }

    &>tbody {
        &>tr {
            &>td {
                padding: 0.6rem 2.0rem;
                font-weight: 400;
                font-size: 1.4rem;
                border: 1px solid #f7f7f7;
            }
        }
    }
`

