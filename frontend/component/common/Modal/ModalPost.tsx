import React, { useEffect, useRef, useState } from 'react';
import Tiptap from '../Posting/Tiptap';
import { StyledModalPost, Wrap } from './Modal_style';
import { StyledModalWrap } from '../reModal/modalwrap/ModalWrapStyle';
import PostingCate from '../Posting/PostingCate';

type modalProps = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
};
export type dataObj = {
	title: string;
	content: string;
	secret: boolean;
	hashtags: string[]; //빈 배열 가능 []
	categoryId: number;
};

export const ModalPost = ({ setOpen }: modalProps) => {
	const [close, setClose] = useState<boolean>(false);
	const [data, setData] = useState<dataObj>();
	const [cateOn, setCateOn] = useState(false);

	const ref = useRef<any>(null);
	useEffect(() => {
		setTimeout(() => ref.current.focus());
	}, []);

	return (
		<Wrap ref={ref} tabIndex={0}>
			{close && <>그만 두시겠습니까?</>}

			{cateOn ? (
				<PostingCate data={data} setOpen={setOpen} setCateOn={setCateOn} />
			) : (
				<StyledModalPost>
					<h1>포스트 작성</h1>
					<Tiptap setOpen={setOpen} setCateOn={setCateOn} setData={setData} />
					<button className='close' onClick={() => setClose(true)}>
						x
					</button>
				</StyledModalPost>
			)}
		</Wrap>
	);
};
