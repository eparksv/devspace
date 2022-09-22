import React from 'react';
import { ModalProps } from '../ModalTypes';
import ModalWriteLink from '../ModalWriteLink';
import { StyledButtonAdd } from './ButtonStyle';

function ButtonAdd({ setOpen, setTest }: ModalProps) {
	return (
		<StyledButtonAdd
			onClick={() => {
				if (setTest)
					setTest(<ModalWriteLink setOpen={setOpen} setTest={setTest} />);
			}}>
			{`(아이콘)추가`}
		</StyledButtonAdd>
	);
}
export default ButtonAdd;
