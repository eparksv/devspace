import React, { useEffect, useRef, useState } from 'react';
import CheckIcon from '@mui/icons-material/Check';
import ErrorIcon from '@mui/icons-material/Error';
import { StyledValidationAll } from './ValidationStyle';

function ValidationAll() {
	const name = useRef<HTMLInputElement>(null);
	const text = useRef<HTMLTextAreaElement>(null);
	const submit = useRef<HTMLInputElement>(null);

	const [word, setWord] = useState(0);
	const [pass, setPass] = useState<string>();
	const [nameMessage, setNameMessage] = useState('5글자 이하로 적어주세요');
	const [testMessage, setTextMessage] = useState<string>('');

	const validateName = (e: React.ChangeEvent<HTMLInputElement>) => {
		if (name.current == null) return;
		const data = name.current.value;
		message(data);
		const num = data.length;
		const checkSpc = /[~!@#$%^&*()_+|<>?:{}]/;
		const checkNum = /[0-9]/;
		const checkSpcData = checkSpc.test(data);
		const checkNumData = checkNum.test(data);

		if (checkSpcData || checkNumData) {
			name.current.parentElement?.classList.remove('pass');
			name.current.parentElement?.classList.add('nopass');
			setPass('no');
		} else {
			if (num >= 2) {
				name.current.parentElement?.classList.remove('nopass');
				name.current.parentElement?.classList.add('pass');
				setPass('ok');
			} else {
				name.current.parentElement?.classList.remove('pass');
				name.current.parentElement?.classList.add('nopass');
				setPass('no');
			}
		}
	};

	const message = (data: string) => {
		const last = data.slice(-1);
		const checkSpc = /[~!@#$%^&*()_+|<>?:{}]/;
		const checkNum = /[0-9]/;
		if (data.length < 2) setNameMessage('2자 이상 입력해주세요');
		if (checkSpc.test(last)) setNameMessage('특수문자는 사용하실 수 없습니다');
		if (checkNum.test(last)) setNameMessage('숫자는 사용하실 수 없습니다');
		if (!checkSpc.test(last) && !checkNum.test(last) && data.length >= 2)
			setNameMessage('사용 가능한 이름입니다.');
	};

	const sizing = () => {
		if (text.current) {
			const length = text.current.value.trim().length;
			setWord(length);
		}
	};

	const validateText = () => {
		if (text.current == null) return;
		const data = text.current.value.trim();
		if (data.length > 0) {
			text.current.parentElement?.classList.remove('nopass');
			text.current.parentElement?.classList.add('pass');
			setTextMessage('');
		} else {
			text.current.parentElement?.classList.remove('pass');
			text.current.parentElement?.classList.add('nopass');
			setTextMessage('자기소개를 입력해주세요');
		}
	};

	const checkPass = () => {
		if (
			name.current?.parentElement?.classList.contains('pass') &&
			text.current?.parentElement?.classList.contains('pass')
		) {
			submit.current?.classList.add('on');
		} else if (
			name.current?.parentElement?.classList.contains('nopass') ||
			text.current?.parentElement?.classList.contains('nopass')
		) {
			submit.current?.classList.remove('on');
		}
	};

	const eraseMessage = (e: React.FocusEvent<HTMLTextAreaElement>) => {
		e.target.placeholder = '';
		if (name.current?.parentElement?.classList.contains('pass'))
			setNameMessage(' ');
	};
	return (
		<StyledValidationAll>
			<div className='wrap-name'>
				<input
					type='text'
					name='이름'
					placeholder='이름을 입력해주세요'
					ref={name}
					onKeyDown={(e) => {
						if (e.key === ' ') e.preventDefault();
					}}
					onChange={(e) => {
						validateName(e);
						checkPass();
					}}
					onFocus={(e) => (e.target.placeholder = '')}
					onInput={(e) => {
						if (e.currentTarget.value.length > 5)
							e.currentTarget.value = e.currentTarget.value.slice(0, 5);
					}}
				/>
				{pass === 'ok' ? <CheckIcon /> : null}
				{pass === 'no' ? <ErrorIcon /> : null}
				<p>{nameMessage}</p>
			</div>
			<div className='wrap-text'>
				<textarea
					name='자기소개'
					rows={5}
					placeholder='자기소개를 입력해주세요'
					ref={text}
					maxLength={255}
					onFocus={eraseMessage}
					onChange={() => {
						sizing();
						validateText();
						checkPass();
					}}
				/>
				<div className='wrap-p'>
					<p>{testMessage}</p>
					<p>{word}/255</p>
				</div>
			</div>
		</StyledValidationAll>
	);
}

export default ValidationAll;

/* input text에 value는 2자 이하, 특수문자, 숫자를 포함하면 안되고 
	2자 이하이거나 특수문자, 숫자가 입력되면 즉시 에러 메세지를 나타내야 하므로 
	value 전체를 체크하는 함수(validateName)와 value의 길이와 마지막 글자를 체크하는 함수(message)로 구성되어 있다.  
	개선해야할 부분: 각 함수마다 유효성 검사를 한다. 한번의 유효성 검사만 할 방법을 생각해봐야 한다. 
	*/
