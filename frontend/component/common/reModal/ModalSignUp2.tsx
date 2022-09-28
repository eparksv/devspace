import { ModalProps, Value } from './ModalTypes';
import React, { useRef } from 'react';
import ModalWrap from './modalwrap/ModalWrap';
import ValidationAll from './input/ValidationAll';
import ModalJob from './ModalJob';

function ModalSignUp2({ setOpen, setTest }: ModalProps) {
	const ref = useRef<HTMLDivElement>(null);
	const value: Value = {};

	const getValue = () => {
		if (ref.current) {
			const name = ref.current.querySelector('input')?.value;
			const introduction = ref.current.querySelector('textarea')?.value;
			value.name = name;
			value.introduction = introduction;
			console.log(value);
		}
	};

	return (
		<ModalWrap>
			<h1>프로필입력</h1>
			<p> 나에 대해 자유롭게 소개해주세요.</p>

			<div ref={ref}>
				<ValidationAll />
			</div>

			<button
				className='next'
				onClick={() => {
					if (setTest)
						setTest(
							<ModalJob setOpen={setOpen} setTest={setTest} value={value} />
						);
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
