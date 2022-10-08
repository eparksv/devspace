import React, { KeyboardEvent, useRef, useState } from 'react';
import { WrapEditor } from '../Modal/Modal_style';
import Tiptap_submit from './Tiptap_submit';
import Tiptap_post from './Tiptap_post';
import Tiptap_tag from './Tiptap_tag';

type Props = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
};

const Tiptap = ({ setOpen }: Props) => {
	console.log('tiiptap start');

	const title = useRef<HTMLInputElement>(null);
	const ref = useRef<HTMLDivElement>(null);
	const tags = useRef<HTMLDivElement>(null);
	const protect = useRef<HTMLDivElement>(null);

	const [inputSize, setInputSize] = useState<number>();

	const inputSizing = () => {
		const num = title.current?.value.length;
		num ? setInputSize(num * 1.5) : null;
	};

	const getValue = () => {
		const titleValue = title.current?.value;
		const targetCurrent = ref.current; //innerHTML뽑기
		const taglist = tags.current ? tags.current.querySelectorAll('input') : [];
		const hashtags: string[] = [];
		taglist.forEach((h, idx) => {
			hashtags.push(h.value);
		});
		console.log('hashs', hashtags);
		return {
			title: titleValue,
			target: targetCurrent,
			hashtags: hashtags,
		};
	};

	return (
		<>
			<form onSubmit={(e) => e.preventDefault()}>
				{/*fieldset, legend 태그 추가 */}
				<div className='editor-top'>
					<input
						type='text'
						ref={title}
						size={inputSize}
						onChange={inputSizing}
						placeholder='제목을 입력해주세요'
					/>
				</div>

				<WrapEditor ref={ref}>
					<Tiptap_post />
				</WrapEditor>

				{/*컴포넌트로 */}
				<div className='tags' ref={tags}>
					<Tiptap_tag />
				</div>

				<div className='link'>링크</div>
				<div className='protect' ref={protect}>
					공개여부
				</div>

				<Tiptap_submit getValue={getValue} setOpen={setOpen} />
			</form>
		</>
	);
};

export default Tiptap;
