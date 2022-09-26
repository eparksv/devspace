import React, { useEffect, useRef, useState } from 'react';
import ErrorIcon from '@mui/icons-material/Error';
import { StyledValidationSpc } from './ValidationStyle';
import { ValidationProps } from '../ModalTypes';

function ValidationSpc({ name, maxLength }: ValidationProps) {
	const ref = useRef<HTMLInputElement>(null);

	const [pass, setPass] = useState<string>();
	const [inputMessage, setInputMessage] = useState('');
	const [word, setWord] = useState(0);

	const validateName = (e: React.ChangeEvent<HTMLInputElement>) => {
		if (ref.current == null) return;
		const data = ref.current.value;
		message(data);
		const checkSpc = /[~!@#$%^&*()_+|<>?:{}]/;
		const checkSpcData = checkSpc.test(data);

		if (checkSpcData) {
			ref.current.parentElement?.classList.remove('pass');
			ref.current.parentElement?.classList.add('nopass');
			setPass('no');
		} else {
			ref.current.parentElement?.classList.remove('nopass');
			ref.current.parentElement?.classList.add('pass');
			setPass('ok');
		}

		if (ref.current.value.length === 0) {
			ref.current.parentElement?.classList.remove('pass');
			ref.current.parentElement?.classList.remove('nopass');
			setPass('');
		}
	};

	const message = (data: string) => {
		const last = data.slice(-1);
		const checkSpc = /[~!@#$%^&*()_+|<>?:{}]/;
		if (checkSpc.test(last)) {
			setInputMessage('특수문자는 사용하실 수 없습니다');
		} else {
			setInputMessage('');
		}
	};

	const sizing = () => {
		if (ref.current) {
			const length = ref.current.value.trim().length;
			setWord(length);
		}
	};

	return (
		<StyledValidationSpc>
			<div className={`standard ${name}`}>
				<input
					type='text'
					name='이름'
					placeholder='이름을 입력해주세요'
					ref={ref}
					onKeyDown={(e) => {
						if (e.key === ' ') e.preventDefault();
					}}
					onChange={(e) => {
						validateName(e);
						sizing();
					}}
					onInput={(e) => {
						if (e.currentTarget.value.length > maxLength)
							e.currentTarget.value = e.currentTarget.value.slice(0, maxLength);
					}}
				/>
				{pass === 'no' ? <ErrorIcon /> : null}
				<div className='text-box'>
					<p>{inputMessage}</p>
					<p>{word}/30</p>
				</div>
			</div>
		</StyledValidationSpc>
	);
}

export default ValidationSpc;
