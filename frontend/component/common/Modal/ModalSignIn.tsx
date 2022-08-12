import { useRouter } from 'next/router';
import React, { useEffect, useRef } from 'react';
import { StyledModalSignIn, Wrap } from './Modal_style';

type modalProps = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
};

export const ModalSignIn = ({ setOpen }: modalProps) => {
	const ref = useRef<any>(null);
	const router = useRouter();

	useEffect(() => {
		setTimeout(() => ref.current.focus());
	}, []);

	const withGoogle = () => {
		router.push(
			`http://localhost:8080/oauth2/authorization/google?.redirect_uri=http://localhost:3000/oauth/redirect/`
		);
	};
	const withGithub = () => {
		router.push(
			'http://localhost:8080/oauth2/authorization/github?.redirect_uri=http://localhost:3000/oauth/redirect'
		);
	};

	return (
		<Wrap ref={ref} tabIndex={0} onClick={() => setOpen(false)}>
			<StyledModalSignIn>
				<p className='signin-title'>지금 바로 시작하기</p>
				<p className='signin-p'>
					데브스페이스와 함께 당신의 커리어를 더욱 성장시켜 보세요
				</p>
				<button onClick={withGoogle} /*aria-lable='구글로 로그인하기'*/>
					<div className='icon' /> 구글로 로그인하기
				</button>
				<button onClick={withGithub} /*aria-lable='깃허브로 로그인하기'*/>
					<div className='icon' />
					깃허브로 로그인하기
				</button>

				<button
					className='close'
					onClick={() => setOpen(false)}
					arira-label='닫기'>
					X
				</button>
			</StyledModalSignIn>
		</Wrap>
	);
};
