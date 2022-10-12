import React from 'react';
import { StyledModal, Wrap } from './Modal_style';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import ModalAddLink2 from './ModalAddLink2';

type modalProps = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	setModal: React.Dispatch<React.SetStateAction<React.ReactNode>>;
	value: { name: string; text: string; [propName: string]: any };
	type?: string | undefined;
};

function ModalAddLink1({ setModal, setOpen, value }: modalProps) {
	return (
		<Wrap>
			<StyledModal>
				<h1>링크 추가</h1>
				<p>SNS 링크 추가...</p>
				<button onClick={() => setModal(<ModalAddLink2 setOpen={setOpen} />)}>
					<AddCircleIcon /> 추가
				</button>
				<button
					onClick={() => {
						{
							/*mutate */
						}
					}}>
					가입 완료
				</button>
			</StyledModal>
		</Wrap>
	);
}

export default ModalAddLink1;
