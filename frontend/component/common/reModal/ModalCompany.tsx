import React from 'react';
import ButtonSkip from './button/ButtonSkip';
import ValidationSpc from './input/ValidationSpc';
import { ModalProps, Value } from './ModalTypes';
import ModalWrap from './modalwrap/ModalWrap';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ModalAddLink from './ModalAddLink';
import ModalJob2 from './ModalJob2';

interface Props extends ModalProps {
	value: Value;
	type: string | undefined;
}

function ModalCompany({ setOpen, setTest, value, type }: Props) {
	return (
		<ModalWrap>
			<h1>회사 입력</h1>
			<p> 동종 업계 사람들을 쉽게 찾을 수 있어요.</p>

			<ValidationSpc name={'company'} maxLength={30} />
			<ValidationSpc name={'career'} maxLength={30} />

			<button
				className='next next-company'
				onClick={() => {
					if (setTest)
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

			<ButtonSkip setOpen={setOpen} setTest={setTest} />

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
