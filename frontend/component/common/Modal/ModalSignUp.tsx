import React, { useEffect, useRef, useState } from 'react';
import { ModalJob } from './ModalJob';
import { StyledModalSignUp, Wrap } from './Modal_style';
import CheckIcon from '@mui/icons-material/Check';
import ErrorIcon from '@mui/icons-material/Error';

type modalProps = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	setModal: React.Dispatch<React.SetStateAction<React.ReactNode>>;
	value?: { name: string; text: string | undefined };
	token?: string;
};

export const ModalSignUp = ({ setOpen, setModal, token }: modalProps) => {
	const ref = useRef<any>(null);

	useEffect(() => {
		setTimeout(() => ref.current.focus());
	}, []);

	const name = useRef<HTMLInputElement>(null);
	const text = useRef<HTMLTextAreaElement>(null);
	const submit = useRef<HTMLInputElement>(null);

	const [word, setWord] = useState(0);
	const [pass, setPass] = useState<string>();
	const [nameMessage, setNameMessage] = useState('5글자 이하로 적어주세요');
	const [testMessage, setTextMessage] = useState<string>('');

	const postProfile = (e: React.FormEvent) => {
		e.preventDefault();
		if (text.current?.value.trim() && name.current?.value) {
			const value = {
				name: name?.current.value,
				text: text?.current.value,
			};

			setModal(
				<ModalJob
					setOpen={setOpen}
					setModal={setModal}
					value={value}
					token={token}
				/>
			);
		}
	};

	const validName = (e: React.ChangeEvent<HTMLInputElement>) => {
		if (name.current == null) return;
		const data = name.current.value;
		validNameMessage(data);
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

	const validNameMessage = (data: string) => {
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
			setWord(text.current.value.trim().length);
		}
	};

	const validText = () => {
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
			console.log('!');
			submit.current?.classList.add('on');
		} else if (
			name.current?.parentElement?.classList.contains('nopass') ||
			text.current?.parentElement?.classList.contains('nopass')
		) {
			console.log('?');
			submit.current?.classList.remove('on');
		}
	};

	const eraseMessage = (e: React.FocusEvent<HTMLTextAreaElement>) => {
		e.target.placeholder = '';
		if (name.current?.parentElement?.classList.contains('pass'))
			setNameMessage(' ');
	};

	return (
		<Wrap ref={ref} tabIndex={0}>
			<StyledModalSignUp>
				<h1>프로필 입력</h1>
				<form onSubmit={postProfile}>
					{/*feildset, legend */}
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
								validName(e);
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
								validText();
								checkPass();
							}}
						/>
						<div className='p-wrap'>
							<p>{testMessage}</p>
							<p>{word}/255</p>
						</div>
					</div>

					<input type='submit' value='다음' ref={submit} />
				</form>

				<button
					className='close'
					onClick={() => setOpen(false)}
					arira-label='닫기'>
					X
				</button>
			</StyledModalSignUp>
		</Wrap>
	);
};
