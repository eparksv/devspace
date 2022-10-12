import { ContextUser } from '@/pages/_app';
import { useMutation } from '@tanstack/react-query';
import axios from 'axios';
import React, { useContext, useState } from 'react';
import Tiptap_loading from './Tiptap_loading';

type Props = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	getValue: () => {
		title: string | undefined;
		target: HTMLDivElement | null;
		hashtags: string[];
		secret: boolean;
	};
};

function Tiptap_submit({ getValue, setOpen }: Props) {
	console.log('submit start');

	const [loading, setLoading] = useState(false);
	const { token } = useContext(ContextUser);

	//부모 컴포넌트의 ref.curent의 값을 받는 함수.
	const click = () => {
		const { title, target, hashtags, secret } = getValue();
		postSubmit(title, target, hashtags, secret);
	};

	const { mutate } = useMutation(
		async (data: object) => {
			axios.post(`posts`, data, {
				headers: {
					Authorization: `Bearer ${token}`,
				},
			});
		},
		{
			onError: (e) => {
				console.log(e);
				console.log('실패');
			},
			onSuccess: () => {
				setLoading(false);
				setOpen(false);
				console.log('성공');
			},
		}
	);

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
			const data = {
				title: title ? title : null,
				content: content,
				secret: secret,
				hashtags: hashtags, //빈 배열 가능 []
				categoryId: 1,
			};

			console.log('data전송', data);
			setLoading(true);
			//mutate(data);

			const test = async () => {
				try {
					const res = await axios.post(`posts`, data, {
						headers: {
							Authorization: `Bearer ${token}`,
						},
					});
					console.log(res.data);
				} catch (e) {
					console.log(e);
					alert('죄송합니다, 다시 시도해주세요');
				}
			};
			test();

			//content = target.querySelector('.ProseMirror')?.innerHTML; //??

			//나중에 지울 코드. 테스트용
			//setTimeout(() => setLoading(false), 2500);
			setTimeout(() => setOpen(false), 2600);
		}
	};
	return (
		<>
			<input type='submit' value='입력' onClick={click} />
			{loading && <Tiptap_loading />}
		</>
	);
}

export default Tiptap_submit;
