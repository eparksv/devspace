import React, { useRef } from 'react';
import ButtonSkip from './button/ButtonSkip';
import ValidationSpc from './input/ValidationSpc';
import { ModalProps, Value } from './ModalTypes';
import ModalWrap from './modalwrap/ModalWrap';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ModalAddLink from './ModalAddLink';
import ModalJob2 from './ModalJob2';
import { check } from 'prettier';

interface Props extends ModalProps {
	value: Value;
	type: string | undefined;
}

function ModalCompany({ setOpen, setTest, value, type }: Props) {
	const ref = useRef<HTMLDivElement>(null);
	const nextBt = useRef<HTMLButtonElement>(null);

	const getValue = () => {
		if (ref.current) {
			const inputs = ref.current.querySelectorAll('input');
			const company = inputs[0].value;
			const career = inputs[1].value;
			console.log(company, career);
			value.company = company;
			value.career = career;
		}
	};
	const checkPass = () => {
		const company = ref.current?.querySelector('.company');
		const career = ref.current?.querySelector('.career');
		const isPass1 = company?.classList.contains('pass');
		const isPass2 = career?.classList.contains('pass');
		if (isPass1 && isPass2) {
			nextBt.current?.classList.add('on');
		} else {
			nextBt.current?.classList.remove('on');
		}
	};

	return (
		<ModalWrap>
			<h1>회사 입력</h1>
			<p> 동종 업계 사람들을 쉽게 찾을 수 있어요.</p>

			<div ref={ref}>
				<ValidationSpc name={'company'} maxLength={30} checkPass={checkPass} />
				<ValidationSpc name={'career'} maxLength={30} checkPass={checkPass} />
			</div>

			<button
				className='next next-company'
				ref={nextBt}
				onClick={() => {
					getValue();
					if (setTest && nextBt.current?.classList.contains('on'))
						setTest(
							<ModalAddLink
								setOpen={setOpen}
								setTest={setTest}
								value={value}
								linkList={[{ url: '', title: '' }]}
								type={type}
							/>
						);
				}}>
				다음
			</button>

			<ButtonSkip setOpen={setOpen} value={value} />

			<button
				className='prev'
				onClick={() => {
					if (setTest)
						setTest(
							<ModalJob2
								setOpen={setOpen}
								setTest={setTest}
								value={value}
								type={type}
							/>
						);
				}}>
				<ArrowBackIcon />
			</button>

			<button className={'close'} onClick={() => setOpen(false)}>
				X
			</button>
		</ModalWrap>
	);
}

export default ModalCompany;
