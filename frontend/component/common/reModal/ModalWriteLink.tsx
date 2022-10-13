import React, { ReactNode, useRef } from 'react';
import ModalWrap from './modalwrap/ModalWrap';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ModalAddLink, { Props } from './ModalAddLink';
import ValidationSpcNum from './input/ValidationSpcNum';
import ValidationHttps from './input/ValidationHttps';
import ButtonFinishAdd from './button/ButtonFinishAdd';

function ModalWriteLink({ setOpen, setTest, linkList, type, value }: Props) {
	const ref = useRef<HTMLDivElement>(null);
	const nextBt = useRef<HTMLDivElement>(null);

	const getValue = () => {
		if (ref.current) {
			const data = ref.current.querySelectorAll('input');
			const obj: { url: string; title: string } = { url: '', title: '' };
			obj.url = data[0].value;
			obj.title = data[1].value;
			console.log('obj', obj);
			return obj;
		} else {
			return { url: '', title: '' };
		}
	};

	const checkPass = () => {
		const link = ref.current?.querySelector('.link');
		const linkName = ref.current?.querySelector('.link-name');
		const isPass1 = link?.classList.contains('pass');
		const isPass2 = linkName?.classList.contains('pass');
		if (isPass1 && isPass2) {
			console.log(nextBt.current?.querySelector('button'));
			nextBt.current?.querySelector('button')?.classList.add('on');
		} else {
			nextBt.current?.querySelector('button')?.classList.remove('on');
		}
	};

	return (
		<ModalWrap>
			<h1>링크 입력</h1>
			<p> 링크 URL을 입력해주세요.</p>

			<div className='link-wrap' ref={ref}>
				<ValidationHttps checkPass={checkPass} />
				<ValidationSpcNum
					name={'link-name'}
					maxLength={10}
					checkPass={checkPass}
				/>
			</div>

			<div ref={nextBt}>
				<ButtonFinishAdd
					setOpen={setOpen}
					setTest={setTest}
					linkList={linkList}
					getValue={getValue}
					type={type}
					value={value}
				/>
			</div>
			<button
				className='prev'
				onClick={() => {
					if (setTest)
						setTest(
							<ModalAddLink
								setOpen={setOpen}
								setTest={setTest}
								linkList={linkList}
								type={type}
								value={value}
							/>
						);
				}}>
				<ArrowBackIcon />
			</button>
		</ModalWrap>
	);
}

export default ModalWriteLink;
