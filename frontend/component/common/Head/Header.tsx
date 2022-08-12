import React, { useContext, useState } from 'react';
import { StyledHeader, HeaderBox } from '../Head/Header_style';
import { ContextDispatch, ContextUser } from '../../../pages/_app';
import { ModalSignIn } from '../Modal/ModalSignIn';

function Header() {
	const { token } = useContext(ContextUser);
	const dispatch = useContext(ContextDispatch);

	const [open, setOpen] = useState(false);
	const [modal, setModal] = useState<React.ReactElement | null>();

	const login = async () => {
		setOpen(true);
		setModal(<ModalSignIn setOpen={setOpen} />);
	};

	const logout = () => {
		if (dispatch) {
			dispatch({ type: 'TOKEN', token: '' });
			//쿠키 파괴 코드
		}
	};

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
				<button className='start' onClick={token ? logout : login}>
					{token ? `로그아웃` : `시작하기`}
				</button>
				{/*설정*/}
			</HeaderBox>
			{open && modal}
		</StyledHeader>
	);
}

export default Header;
