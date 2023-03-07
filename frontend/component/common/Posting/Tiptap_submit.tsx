import { ContextUser } from '@/pages/_app';
import { useMutation } from '@tanstack/react-query';
import axios from 'axios';
import React, { useContext, useState } from 'react';
import { dataObj } from '../Modal/ModalPost';
import PostingCate from './PostingCate';
import Tiptap_loading from './Tiptap_loading';

type Props = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	setCateOn: React.Dispatch<React.SetStateAction<boolean>>;
	setData: React.Dispatch<React.SetStateAction<dataObj | undefined>>;
	getValue: () => {
		title: string | undefined;
		target: HTMLDivElement | null;
		hashtags: string[];
		secret: boolean;
	};
};

function Tiptap_submit({ getValue, setData, setCateOn }: Props) {
	console.log('submit start');

	const [loading, setLoading] = useState(false);

	//부모 컴포넌트의 ref.curent의 값을 받는 함수.
	const click = () => {
		const { title, target, hashtags, secret } = getValue();
		postSubmit(title, target, hashtags, secret);
	};

	const postSubmit = (
		title: string | undefined,
		target: HTMLDivElement | null,
		hashtags: string[],
		secret: boolean
	) => {
		console.log('전송!!!');

		if (target !== null) {
			const targetEle = target;
			const content = target.querySelector('.ProseMirror')?.innerHTML;
			const imgs = targetEle.querySelectorAll('img');
			const dataObj = {
				title: title ? title : '',
				content: content ? content : '',
				secret: secret,
				hashtags: hashtags, //빈 배열 가능 []
				categoryId: 0,
			};

			console.log('data전송', dataObj);
			// setLoading(true);

			// setTimeout(() => setOpen(false), 2600);
			setData(dataObj);
		}
		setCateOn(true);
	};
	return (
		<>
			<input type='submit' value='입력' onClick={click} />
		</>
	);
}

export default Tiptap_submit;
