//최대한 정리해도 필요함수가 6개. 이 페이지에 둘지, 아니면 함수만 모아두는 파일을 만들어서 export시킬지..
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
	}, []); //모달 컴포넌트의 키보드 초첨 함수.

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
				//props 드릴링이 3단계...면 괜찮지않을까??..
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
					{/*feildset, legend.. 아니면 form태그 사용하지않고서.. */}
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
						<div className='wrap-p'>
							<p>{testMessage}</p>
							<p>{word}/255</p>
						</div>
					</div>

					{/*onChange - set..()오 각 글자마다 컴포넌트 렌더링을 호출하는 대신 -> 매 글자변경마다 렌더링이 발생 / 대신 useSate 값을 props로 전달할 수 있어서 컴포넌트로 분리하여 제어할 수 있다(유지보수,가독성)
						ref - ref.current로 value관리, submit 버튼을 클릭하면 ref.currnet의 value값을 객체로 만들어서 modalJob set(); 
						-> 클릭시 한번의 렌더링 / 대신 코드의 의존성이 높아서 jsx와 ref 제어 함수를 분리할 수 없다 + ref.current는 null타입이 기본이어서 타입가드 코드가 필수. (코드 가독성이 떨어짐)
					*/}
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
