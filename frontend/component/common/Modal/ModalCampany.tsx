import React, { useEffect, useRef, useState } from 'react';
import { StyledModalCompany, Wrap } from './Modal_style';
import CheckIcon from '@mui/icons-material/Check';
import ErrorIcon from '@mui/icons-material/Error';
import ModalAddLink1 from './ModalAddLink1';

type modalProps = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	setModal?: React.Dispatch<React.SetStateAction<React.ReactNode>>;
	value: { name: string; text: string; [propName: string]: any };
	type?: string | undefined;
};

function ModalCampany({ setModal, setOpen, value }: modalProps) {
	const ref = useRef<any>(null);
	const company = useRef<HTMLInputElement>(null);
	const career = useRef<HTMLInputElement>(null);

	const [nameLen, setNameLen] = useState(0);
	const [careerLen, setCareerLen] = useState(0);
	const [nameMessage, setNameMessage] = useState('');
	const [pass, setPass] = useState<string>();

	useEffect(() => {
		if (ref.current) {
			ref.current.focus();
		}
	}, []);

	const sizing = (e: React.ChangeEvent) => {
		if (company.current && career.current) {
			const { name } = e.currentTarget as HTMLButtonElement;
			if (name === '회사명') {
				setNameLen(company.current.value.length);
			} else {
				setCareerLen(career.current.value.length);
			}
		}
	};

	const validName = (e: React.ChangeEvent<HTMLInputElement>) => {
		if (company.current == null) return;
		const data = company.current.value;
		const checkSpc = /[~!@#$%^&*()_+|<>?:{}]/;
		const checkSpcData = checkSpc.test(data);

		if (checkSpcData) {
			company.current.parentElement?.classList.remove('pass');
			company.current.parentElement?.classList.add('nopass');
			setPass('no');
			setNameMessage('특수문자는 사용하실 수 없습니다');
		} else {
			company.current.parentElement?.classList.remove('nopass');
			setPass('');
			setNameMessage('');
		}
	};

	const checkPass = () => {
		const nextBt = ref.current?.querySelector('.next');
		if (company.current?.value && career.current?.value) {
			nextBt.classList.add('on');
		} else {
			nextBt.classList.remove('on');
		}
	};

	return (
		<Wrap ref={ref} tabIndex={0}>
			<StyledModalCompany>
				<h1>프로필 입력</h1>
				<p>동종 업계...</p>
				<div className='company-name'>
					<input
						type='text'
						name='회사명'
						placeholder='회사명을 입력해주세요.'
						maxLength={30}
						ref={company}
						onKeyDown={(e) => {
							if (e.key === ' ') e.preventDefault();
						}}
						onChange={(e) => {
							validName(e);
							sizing(e);
							checkPass();
						}}
						onInput={(e) => {
							if (e.currentTarget.value.length > 30)
								e.currentTarget.value = e.currentTarget.value.slice(0, 30);
						}}
					/>
					{pass === 'ok' ? <CheckIcon /> : null}
					{pass === 'no' ? <ErrorIcon /> : null}

					<div className='wrap-p'>
						<p>{nameMessage}</p>
						<p>{nameLen}/30</p>
					</div>
				</div>

				<div className='career'>
					<input
						type='text'
						name='경력'
						placeholder='회사명을 입력해주세요.'
						ref={career}
						maxLength={30}
						onKeyDown={(e) => {
							if (e.key === ' ') e.preventDefault();
						}}
						onChange={(e) => {
							sizing(e);
							checkPass();
						}}
						onInput={(e) => {
							if (e.currentTarget.value.length > 30)
								e.currentTarget.value = e.currentTarget.value.slice(0, 30);
						}}
					/>

					<div className='wrap-p'>
						<p>{careerLen}/30</p>
					</div>
				</div>

				<button
					className='next'
					onClick={() => {
						value.company = career.current?.value;
						if (setModal)
							setModal(
								<ModalAddLink1
									setModal={setModal}
									setOpen={setOpen}
									value={value}
								/>
							);
					}}>
					다음
				</button>

				<p>입력을 원하시지 않나요?</p>
				<button
					className='skip'
					onClick={() => {
						value.company = '';
						if (setModal)
							setModal(
								<ModalAddLink1
									setModal={setModal}
									setOpen={setOpen}
									value={value}
								/>
							);
					}}>
					넘어가기
				</button>

				<button
					className='close'
					onClick={() => setOpen(false)}
					arira-label='닫기'>
					X
				</button>
			</StyledModalCompany>
		</Wrap>
	);
}

export default ModalCampany;
