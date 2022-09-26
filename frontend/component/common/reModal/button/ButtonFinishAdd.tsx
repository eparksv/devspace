import React, { useState } from 'react';
import ModalAddLink, { Props } from '../ModalAddLink';
import { ModalProps } from '../ModalTypes';
import { StyledButtonAdd } from './ButtonStyle';

interface Newprops extends Props {
	getValue: () => {
		url: string;
		title: string;
	};
}

function ButtonFinishAdd({ setOpen, setTest, linkList, getValue }: Newprops) {
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
						/>
					);
				}
			}}>
			{`(아이콘)추가!!`}
		</StyledButtonAdd>
	);
}

export default ButtonFinishAdd;
