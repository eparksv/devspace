import React from 'react';
import { WrapProps } from '../ModalTypes';
import { StyledModalWrap, StyledWrap } from './ModalWrapStyle';

function ModalWrap({ children }: WrapProps) {
	return (
		<StyledWrap>
			<StyledModalWrap>{children}</StyledModalWrap>
		</StyledWrap>
	);
}

export default ModalWrap;
