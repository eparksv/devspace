import React from 'react';
import ButtonSkip from './button/ButtonSkip';
import { ModalProps } from './ModalTypes';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ModalWrap from './modalwrap/ModalWrap';
import ModalWriteLink from './ModalWriteLink';
import ButtonAdd from './button/ButtonAdd';
import ModalCompany from './ModalCompany';
import { StyledButtonSubmit } from './button/ButtonStyle';

function ModalAddLink({ setOpen, setTest }: ModalProps) {
	return (
		<ModalWrap>
			<h1>링크 추가</h1>
			<p>개인 SNS나 웹사이트를 추가해 홍보해보세요.</p>

			<ButtonAdd setOpen={setOpen} setTest={setTest} />

			<StyledButtonSubmit
				className='submit'
				onClick={() => {
					if (setTest)
						setTest(<ModalWriteLink setOpen={setOpen} setTest={setTest} />);
				}}>
				가입완료
			</StyledButtonSubmit>

			<ButtonSkip setOpen={setOpen} setTest={setTest} submit={'sub'} />

			<button
				className='prev'
				onClick={() => {
					if (setTest)
						setTest(<ModalCompany setOpen={setOpen} setTest={setTest} />);
				}}>
				<ArrowBackIcon />
			</button>

			<button className={'close'} onClick={() => setOpen(false)}>
				X
			</button>
		</ModalWrap>
	);
}

export default ModalAddLink;
