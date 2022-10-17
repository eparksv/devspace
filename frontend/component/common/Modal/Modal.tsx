import React from 'react';
import { StyledModal, Wrap } from './Modal_style';

type Props = {
	children?: React.ReactNode;
};

//function Modal({ children }: { children: React.ReactNode }) {
function Modal({ children }: Props) {
	return (
		<Wrap>
			<StyledModal>{children}</StyledModal>
		</Wrap>
	);
}

export default Modal;
