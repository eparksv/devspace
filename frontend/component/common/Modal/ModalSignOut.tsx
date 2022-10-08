import React, { useEffect, useRef, useState } from 'react';
import { StyledModal, Wrap } from './Modal_style';

type modalProps = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
};

export const ModalSignOut = ({ setOpen }: modalProps) => {
	const ref = useRef<any>(null);
	useEffect(() => {
		setTimeout(() => ref.current.focus());
	}, []);
	return (
		<Wrap ref={ref} tabIndex={0}>
			<StyledModal>
				<button onClick={() => setOpen(false)} arira-label='닫기'>
					X
				</button>
			</StyledModal>
		</Wrap>
	);
};
