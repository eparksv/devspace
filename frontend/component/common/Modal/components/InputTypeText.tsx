import React, { useEffect, useRef, useState } from 'react';
import CheckIcon from '@mui/icons-material/Check';
import ErrorIcon from '@mui/icons-material/Error';
import { LensBlur } from '@mui/icons-material';

type InputTypeTextProps = {
	name: string;
	placeholder: string;
	maxLen: number;
	setOn: React.Dispatch<React.SetStateAction<boolean>>;
};

function InputTypeText({
	name,
	placeholder,
	maxLen,
	setOn,
}: InputTypeTextProps) {
	const ref = useRef<any>(null);

	const [len, setLen] = useState(0);
	const [message, setMessage] = useState('');
	const [pass, setPass] = useState<string>();

	const sizing = (e: React.ChangeEvent<HTMLInputElement>) => {
		const len = e.currentTarget.value.length;
		setLen(len);
	};

	const validName = (e: React.ChangeEvent<HTMLInputElement>) => {
		if (ref.current == null) return;
		const data = ref.current.value;
		const checkSpc = /[~!@#$%^&*()_+|<>?:{}]/;
		const checkSpcData = checkSpc.test(data);

		if (checkSpcData) {
			ref.current.parentElement?.classList.remove('pass');
			ref.current.parentElement?.classList.add('nopass');
			setPass('no');
			setMessage('특수문자는 사용 불가합니다');
		} else {
			ref.current.parentElement?.classList.remove('nopass');
			setPass('ok');
			setMessage('');
		}
	};

	const checkPass = () => {
		if (ref.current?.value) {
			setOn(true);
		} else {
			setOn(false);
		}
	};

	return (
		<>
			<input
				type='text'
				name={name}
				placeholder={placeholder}
				maxLength={maxLen}
				ref={ref}
				onKeyDown={(e) => {
					if (e.key === ' ') e.preventDefault();
				}}
				onChange={(e) => {
					validName(e);
					sizing(e);
					checkPass();
				}}
				onInput={(e) => {
					if (e.currentTarget.value.length > maxLen)
						e.currentTarget.value = e.currentTarget.value.slice(0, maxLen);
				}}
			/>
			{pass === 'ok' ? <CheckIcon /> : null}
			{pass === 'no' ? <ErrorIcon /> : null}

			<div className='wrap-p'>
				<p>{message}</p>
				<p>{len}/30</p>
			</div>
		</>
	);
}

export default InputTypeText;
