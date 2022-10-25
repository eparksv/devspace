import React, { useContext, useEffect, useState } from 'react';
import { StyledSection, TextBar } from './Main_style';
import EditIcon from '@mui/icons-material/Edit';
import { ModalPost } from '../common/Modal/ModalPost';
import { ContextUser } from '../../pages/_app';
import { ModalSignUp } from '../common/Modal/ModalSignUp';
import ModalSignUp2 from '../common/reModal/ModalSignUp2';
import ModalCompany from '../common/reModal/ModalCompany';
import axios from 'axios';

const Main = (/*{ data }: any*/) => {
	const [open, setOpen] = useState(false);
	const [test, setTest] = useState<boolean | React.ReactNode>(false);

	/*const [list, setList] = useState<
		[] | firebase.default.firestore.DocumentData[]
	>([]); */

	const context = useContext(ContextUser); // /ouath를 거쳐넘어온 main컴포넌트라면 ContextUser 객체 업데이트됨.

	useEffect(() => {
		if (context.user.job === '') {
			setOpen(true);
			console.log(context.user);
			setTest(<ModalSignUp2 setOpen={setOpen} setTest={setTest} />);
		}
	}, []);

	useEffect(() => {
		const getDate = async () => {
			try {
				const req = axios.get(
					`http://localhost:8080/api/posts?sort=recent&filter=none`
				);
				const data = await req;
				console.log(data.data);
			} catch (e) {
				console.log(e);
			}
		};
		getDate();
	}, []);

	return (
		<>
			<StyledSection>
				<TextBar>
					<button
						onClick={() => {
							setOpen(true);
							setTest(<ModalPost setOpen={setOpen} />);
						}}>
						<EditIcon />
					</button>
					<button onClick={() => setOpen(true)}>x</button>
				</TextBar>

				{/*list.map((l, idx) => {
					const now = dayjs();
					return (
						<StyledArticle key={idx} onClick={more}>
							<div className='user-info'>
								{<img /> 유저 프로필 이미지 }
								<span>{l.userId}</span>
								<p>{l.postCreated}</p>
								{l.preImage ? (
									<img src={l.preImage} alt='이미지 미리보기' />
								) : null}
								<div className='prev'>
									{l.postTitle ? <p>{l.postTitle}</p> : null}
									<p>{l.preText}</p>
								</div>
							</div>
						</StyledArticle>
					);
				})*/}

				{/* side components*/}

				{/*
				<button
					onClick={() => {
						setOpen(true);
						setModal(<ModalSignUp setOpen={setOpen} setModal={setModal} />);
					}}>
					프로필 설정 테스트
				</button>*/}

				<button
					onClick={() => {
						setOpen(true);
						setTest(<ModalSignUp2 setOpen={setOpen} setTest={setTest} />);
					}}>
					회원가입 모달 리팩토링
				</button>

				{/*모달을 제어하는 컴포넌트로 분리할까? (하위 컴포넌트만 리렌더링 되도록)*/}
				{/*open && modal*/}
				{open && test}
			</StyledSection>
		</>
	);
};

export default Main;
