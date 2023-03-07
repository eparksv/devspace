import React from 'react';
import { WrapProps } from '../ModalTypes';
import { StyledModalWrap, StyledWrap } from './ModalWrapStyle';

function ModalWrap({ children }: WrapProps) {
	return (
		<StyledWrap>
			<StyledModalWrap className='???'>{children}</StyledModalWrap>
		</StyledWrap>
	);
}

export default ModalWrap;
