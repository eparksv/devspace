import React from 'react';
import ModalAddLink from '../ModalAddLink';
import { ModalProps } from '../ModalTypes';
import { StyledButtonSkip } from './ButtonStyle';

function ButtonSkip({ setOpen, setTest, submit }: ModalProps) {
	return (
		<StyledButtonSkip>
			<p>입력을 원하지 않으시나요?</p>
			<button
				onClick={() => {
					if (setTest && !submit) {
						setTest(<ModalAddLink setOpen={setOpen} setTest={setTest} />);
					} else {
						//submitData();
					}
				}}>
				넘어가기
			</button>
		</StyledButtonSkip>
	);
}

export default ButtonSkip;
