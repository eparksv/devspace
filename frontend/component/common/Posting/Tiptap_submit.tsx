import React, { useState } from 'react';
import dayjs from 'dayjs';
import Tiptap_loading from './Tiptap_loading';

type Props = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	getValue: () => {
		title: string | undefined;
		target: HTMLInputElement | null;
	};
};

function Tiptap_submit({ getValue, setOpen }: Props) {
	console.log('submit start');

	const [loading, setLoading] = useState(false);

	//부모 컴포넌트의 ref.curent의 값을 받는 함수.
	const click = () => {
		const { title, target } = getValue();
		postSubmit(title, target);
	};

	const postSubmit = async (
		title: string | undefined,
		target: HTMLInputElement | null
	) => {
		console.log('전송!!!');

		const standard = dayjs('2022-07-28');
		const date = dayjs();
		const dateFormat = date.format('YY/MM/DD HH:mm:ss');

		if (target !== null) {
			let content: string | undefined;
			let preText: string | undefined;
			const targetEle = target;
			const imgs = targetEle.querySelectorAll('img');

			setLoading(true);

			content = target.querySelector('.ProseMirror')?.innerHTML;
			//??꼭 필요할까? content만 저장하고 post읽을때 content에서 inneertext 처리하면 pretext는 저장 안해도 될거같은데?
			//preText = target.querySelector('.ProseMirror')?.innerText;
			//console.log(preText);

			const post = {
				userId: 'dummy',
				postOrder: `${date.diff(standard)}`, //파이어베이스용 코드.. 파베DB 정렬기준.
				postCreated: dateFormat, //dayjs
				postTitle: title,
				postContent: content,
				preText: preText,
				liked: 0,
				commented: [],
			};

			console.log(post);

			//나중에 지울 코드. 테스트용
			setTimeout(() => setLoading(false), 2500);
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
