import React, { useContext, useEffect, useState } from 'react';
import { StyledHeader, HeaderBox } from '../Head/Header_style';
import { ContextDispatch, ContextUser } from '../../../pages/_app';
import { ModalSignIn } from '../Modal/ModalSignIn';

function Header() {
	const { token } = useContext(ContextUser);
	const dispatch = useContext(ContextDispatch);

	const [open, setOpen] = useState(false);
	const [modal, setModal] = useState<React.ReactElement | null>();
	const [user, setUser] = useState<string>('');

	const login = async () => {
		setOpen(true);
		setModal(<ModalSignIn setOpen={setOpen} />);
	};

	const logout = () => {
		localStorage.removeItem('devS_user');
		setUser('');
	};

	useEffect(() => {
		console.log('유저체크');
		const isUserInfo = localStorage.getItem('devS_user');
		if (isUserInfo) {
			const userInfo = JSON.parse(isUserInfo);
			console.log(userInfo.expire);
			const now = Date.now();
			console.log(now);
			if (now > Number(userInfo.expire)) {
				console.log('now > ', userInfo);
				localStorage.removeItem('devS_user');
			} else {
				console.log('expire', userInfo);
				setUser(userInfo.id);
			}
		}
	}, []);

	return (
		<StyledHeader>
			<HeaderBox>
				{/*로고 */}
				{/*서치바*/}
			</HeaderBox>
			<HeaderBox>
				{/*아이콘1 */}
				{/*아이콘2*/}
				<span>아이콘__1 </span>
				<span>아이콘__2 </span>
				<button className='start' onClick={user ? logout : login}>
					{user ? `로그아웃` : `시작하기`}
				</button>
				{/*설정*/}
			</HeaderBox>
			{open && modal}
		</StyledHeader>
	);
}

export default Header;
