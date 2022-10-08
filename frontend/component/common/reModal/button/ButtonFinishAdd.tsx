import React, { useState } from 'react';
import ModalAddLink, { Props } from '../ModalAddLink';
import { Value } from '../ModalTypes';
import { StyledButtonAdd } from './ButtonStyle';

interface Newprops extends Props {
	getValue: () => {
		url: string;
		title: string;
	};
	value: Value;
	type: string | undefined;
}

function ButtonFinishAdd({
	setOpen,
	setTest,
	linkList,
	getValue,
	value,
	type,
}: Newprops) {
	return (
		<StyledButtonAdd
			onClick={() => {
				if (setTest) {
					const obj = getValue();
					linkList.push(obj);
					setTest(
						<ModalAddLink
							setOpen={setOpen}
							setTest={setTest}
							linkList={linkList}
							value={value}
							type={type}
						/>
					);
				}
			}}>
			{`(아이콘)추가!!`}
		</StyledButtonAdd>
	);
}

export default ButtonFinishAdd;
