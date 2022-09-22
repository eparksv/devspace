import { ModalProps } from './ModalTypes';
import React from 'react';
import ModalWrap from './modalwrap/ModalWrap';
import ValidationAll from './input/ValidationAll';
import ModalCompany from './ModalCompany';

function ModalSignUp2({ setOpen, setTest }: ModalProps) {
	console.log('st');
	return (
		<ModalWrap>
			<h1>프로필입력</h1>
			<p> 나에 대해 자유롭게 소개해주세요.</p>

			<ValidationAll />

			<button
				className='next'
				onClick={() => {
					if (setTest)
						setTest(<ModalCompany setOpen={setOpen} setTest={setTest} />);
				}}>
				다음
			</button>

			<button className={'close'} onClick={() => setOpen(false)}>
				X
			</button>
		</ModalWrap>
	);
}

export default ModalSignUp2;
