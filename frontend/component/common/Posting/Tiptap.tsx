import React, { KeyboardEvent, useRef, useState } from 'react';
import { Cancel } from '@mui/icons-material';
import { WrapEditor } from '../Modal/Modal_style';
import Tiptap_submit from './Tiptap_submit';
import Tiptap_post from './Tiptap_post';

type Props = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
};

const Tiptap = ({ setOpen }: Props) => {
	console.log('tiiptap start');

	const getValue = () => {
		console.log('val');
		const titleValue = title.current?.value;
		const targetCurrent = ref.current;
		return {
			title: titleValue,
			target: targetCurrent,
		};
	};

	const title = useRef<HTMLInputElement>(null);
	const ref = useRef<HTMLInputElement>(null);
	const tag = useRef<HTMLInputElement>(null);

	const [inputSize, setInputSize] = useState<number>();
	const [tagList, setTaglist] = useState<string[]>([]);

	const inputSizing = () => {
		const num = title.current?.value.length;
		num ? setInputSize(num * 2) : null;
	};

	const makeTag = (e: KeyboardEvent<HTMLInputElement>) => {
		if (e.key == '#') e.preventDefault();
		if ((e.key === 'Enter' || e.key === ' ') && tag.current) {
			e.preventDefault();
			setTaglist([...tagList, tag.current.value]);
			tag.current.value = '';
		}
	};

	return (
		<>
			<form onSubmit={(e) => e.preventDefault()}>
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
				<div className='tag'>
					<label htmlFor='tag'>태그</label>
					{tagList.map((t, idx) => {
						return (
							<span
								className='tag-value'
								onClick={() => {}}
								key={idx}
								tabIndex={0}>
								{` #` + t}
								<button className='tag-close' onClick={() => {}} tabIndex={0}>
									<Cancel />
								</button>
							</span>
						);
					})}
					{tagList.length < 5 ? (
						<input
							type='text'
							id='tag'
							onKeyDown={makeTag}
							ref={tag}
							placeholder='태그 입력'
						/>
					) : null}
				</div>
				<Tiptap_submit getValue={getValue} setOpen={setOpen} />
			</form>
		</>
	);
};

export default Tiptap;
