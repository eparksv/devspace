import React, { useState } from 'react';
import { Props } from '../ModalAddLink';
import { ModalProps } from '../ModalTypes';
import ModalWriteLink from '../ModalWriteLink';
import { StyledButtonAdd } from './ButtonStyle';

function ButtonAdd({ setOpen, setTest, linkList }: Props) {
	return (
		<StyledButtonAdd
			onClick={() => {
				if (setTest)
					setTest(
						<ModalWriteLink
							setOpen={setOpen}
							setTest={setTest}
							linkList={linkList}
						/>
					);
			}}>
			{`(아이콘)추가`}
		</StyledButtonAdd>
	);
}
export default ButtonAdd;
