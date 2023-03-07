import React, { MouseEvent, useRef, useState } from 'react';
import { StyledForm, WrapEditor } from '../Modal/Modal_style';
import Tiptap_submit from './Tiptap_submit';
import Tiptap_post from './Tiptap_post';
import Tiptap_tag from './Tiptap_tag';
import { dataObj } from '../Modal/ModalPost';

type Props = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	setCateOn: React.Dispatch<React.SetStateAction<boolean>>;
	setData: React.Dispatch<React.SetStateAction<dataObj | undefined>>;
};

const Tiptap = ({ setOpen, setCateOn, setData }: Props) => {
	console.log('tiiptap start');

	const title = useRef<HTMLInputElement>(null);
	const ref = useRef<HTMLDivElement>(null);
	const tags = useRef<HTMLDivElement>(null);
	const protect = useRef<HTMLDivElement>(null);
	let secret: boolean;

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
			secret: secret,
		};
	};

	const checkPrivate = (st: string, e: MouseEvent) => {
		st === 'public' ? (secret = false) : (secret = true);
		e.currentTarget.parentElement
			?.querySelectorAll('.protect-bt')
			.forEach((a) => a.classList.remove('on'));
		e.currentTarget.classList.add('on');
	};

	return (
		<>
			<StyledForm onSubmit={(e) => e.preventDefault()} className='post-form'>
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
					<button
						className='protect-bt on'
						onClick={(e) => checkPrivate('public', e)}>
						전체 공개
					</button>
					<button
						className='protect-bt'
						onClick={(e) => checkPrivate('private', e)}>
						나만 보기
					</button>
				</div>

				<Tiptap_submit
					getValue={getValue}
					setOpen={setOpen}
					setCateOn={setCateOn}
					setData={setData}
				/>
			</StyledForm>
		</>
	);
};

export default Tiptap;
