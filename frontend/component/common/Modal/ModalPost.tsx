import React, { useEffect, useRef } from 'react';
import Tiptap from '../Posting/Tiptap';
import { StyledModalPost, Wrap } from './Modal_style';

type modalProps = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
};

export const ModalPost = ({ setOpen }: modalProps) => {
	const ref = useRef<any>(null);
	useEffect(() => {
		setTimeout(() => ref.current.focus());
	}, []);

	return (
		<Wrap ref={ref} tabIndex={0}>
			<StyledModalPost>
				<h1>포스트 작성</h1>
				<Tiptap setOpen={setOpen} />
				<button className='close' onClick={() => setOpen(false)}>
					x
				</button>
			</StyledModalPost>
		</Wrap>
	);
};
