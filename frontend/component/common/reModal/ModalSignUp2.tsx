import { ModalProps, Value } from './ModalTypes';
import React, { useRef } from 'react';
import ModalWrap from './modalwrap/ModalWrap';
import ValidationAll from './input/ValidationAll';
import ModalJob from './ModalJob';

function ModalSignUp2({ setOpen, setTest }: ModalProps) {
	const ref = useRef<HTMLDivElement>(null);
	const nextBt = useRef<HTMLButtonElement>(null);
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

	const checkPass = () => {
		const name = ref.current?.querySelector('.wrap-name');
		const text = ref.current?.querySelector('.wrap-text');
		if (name?.classList.contains('pass') && text?.classList.contains('pass')) {
			nextBt.current?.classList.add('on');
		} else if (
			name?.classList.contains('nopass') ||
			text?.classList.contains('nopass')
		) {
			nextBt.current?.classList.remove('on');
		}
	};

	return (
		<ModalWrap>
			<h1>프로필입력</h1>
			<p> 나에 대해 자유롭게 소개해주세요.</p>

			<div ref={ref}>
				<ValidationAll checkPass={checkPass} />
			</div>

			<button
				className='next'
				ref={nextBt}
				onClick={() => {
					getValue();
					if (setTest && nextBt.current?.classList.contains('on'))
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
