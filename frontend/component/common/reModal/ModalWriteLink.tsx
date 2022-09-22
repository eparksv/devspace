import React from 'react';
import { ModalProps } from './ModalTypes';
import ModalWrap from './modalwrap/ModalWrap';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ModalAddLink from './ModalAddLink';

function ModalWriteLink({ setOpen, setTest }: ModalProps) {
	return (
		<ModalWrap>
			<h1>링크 입력</h1>
			<p> 링크 URL을 입력해주세요.</p>
			<button
				className='prev'
				onClick={() => {
					if (setTest)
						setTest(<ModalAddLink setOpen={setOpen} setTest={setTest} />);
				}}>
				<ArrowBackIcon />
			</button>
		</ModalWrap>
	);
}

export default ModalWriteLink;
