import React, { useContext, useEffect, useState } from 'react';
import { StyledSection, TextBar } from './Main_style';
import EditIcon from '@mui/icons-material/Edit';
import { ModalPost } from '../common/Modal/ModalPost';
import { ContextUser } from '../../pages/_app';
import ModalSignUp2 from '../common/reModal/ModalSignUp2';
import axios from 'axios';
import { useQuery, useQueryClient } from '@tanstack/react-query';
import styled from '@emotion/styled';
import dayjs from 'dayjs';
import PostedModal from '../common/Posted/PostedModal';

export type PostArr = {
	id: number;
	profile: {
		id: number;
		name: string;
		job: string;
		company: string;
		image: null | string;
	};
	title: string;
	likeCount: number;
	commentCount: number;
	content: string;
	createdDate: string;
	hashtags: string[];
};

type DataArr =
	| {
			/*	data: {
				count : null;
				posts : object[];
				[key:string] : string; */
			data: {
				messege: string;
				data: {
					count: null;
					posts: PostArr[];
					[key: string]: string | null | object; // 추가적으로 생길 수 있는 객체에 대한 타입 처리.
				};
			};
			status: number;
			statusText: string;
			header: Object;
			request: XMLHttpRequest;
			config: Object;
	  }
	| undefined;

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

	/* useEffect(() => {
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
	}, []); */

	const { data, isLoading, isError } = useQuery(
		['posts'],
		async () =>
			axios.get(`http://localhost:8080/api/posts?sort=recent&filter=none`),
		{
			staleTime: Infinity,
			cacheTime: Infinity,
			onSuccess: (data) => {
				// 성공시 호출
				console.log(data.data.data.posts);
			},
			onError: (e) => {
				// 실패시 호출 (401, 404 같은 error가 아니라 정말 api 호출이 실패한 경우만 호출됩니다.)
				// 강제로 에러 발생시키려면 api단에서 throw Error 날립니다. (참조: https://react-query.tanstack.com/guides/query-functions#usage-with-fetch-and-other-clients-that-do-not-throw-by-default)
				console.log(e);
			},
		}
	);

	const queryClient = useQueryClient();
	const arr: DataArr = queryClient.getQueryData(['posts']);
	//console.log('쿼리키', arr?.['data']?.['data']?.['posts']);
	const list = arr?.data.data.posts;

	const handleModal = (data: PostArr, comment?: boolean) => {
		setOpen(true);
		setTest(<PostedModal arr={data} setOpen={setOpen} comment={comment} />);
	};

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

				<Header>
					<h2>전체 카테고리</h2>
					<div className='flex_box'>
						<span> 최신</span>
						<span> 팔로잉</span>
					</div>
				</Header>
				{list
					? list.map((a) => {
							const time = a.createdDate;
							const now = dayjs();
							let diff;
							//console.log('diff', now.diff(time, 'month'));
							if (now.diff(time, 'm') > 60) {
								diff = a.createdDate.slice(0, 10);
							} else {
								diff = now.diff(time, 'm') + '분 전';
							}

							return (
								<Wrap key={a.id}>
									<Wrapper>
										<div className='user_info'>
											<img
												src={a.profile.image ? a.profile.image : undefined}
												alt=''
											/>
											<div className='name_time'>
												<div>{a.profile.name}</div>
												<div>{diff}</div>
											</div>
											<div className='job'>{a.profile.job}</div>
											<div className='corp'>
												{a.profile.company ? a.profile.company : null}
											</div>
										</div>
										<div className='hashtags'>
											{a.hashtags.length > 0
												? a.hashtags.map((h, idx) => {
														return (
															<div className='post-tag' key={idx}>
																#{h}
															</div>
														);
												  })
												: null}
										</div>
										<ContentBox onClick={() => handleModal(a)}>
											<p className='title'>{a.title ? a.title : null}</p>
											<p className='prev_text'>{a.content}</p>
										</ContentBox>
										<div className='like_box'>
											<img src='/images/comment.png' alt='' />
											<img src='/images/like.png' alt='' />
											<div className='box'>
												<div className='liked'>좋아요 {a.likeCount}</div>
												<div
													className='commented'
													onClick={() => handleModal(a, true)}>
													댓글 {a.commentCount}
												</div>
											</div>
										</div>
										{/*로그인 && <img className='picked' src='/images/pick.png' alt='' />*/}
									</Wrapper>
								</Wrap>
							);
					  })
					: null}

				{/* side components*/}

				{/*
				<button
					onClick={() => {
						setOpen(true);
						setModal(<ModalSignUp setOpen={setOpen} setModal={setModal} />);
					}}>
					프로필 설정 테스트
				</button>*/}

				{open && test}
			</StyledSection>
		</>
	);
};

export default Main;

const Header = styled.div`
	display: flex;
	justify-content: space-between;
	text-align: center;
	margin-top: 42px;

	> h2 {
		font-family: 'Pretendard';
		font-style: normal;
		font-weight: 700;
		font-size: 20px;
		line-height: 150%;
		color: #5b5b5b;
	}

	.flex_box > span {
		font-family: 'Pretendard';
		font-style: normal;
		font-weight: 700;
		font-size: 16px;
		line-height: 150%;
		text-align: center;
		color: #8e8e8e;
		padding: 4px;
		&.on {
			color: #808dd7;
			border-bottom: 1px solid #808dd7;
		}
	}
`;

const Wrapper = styled.div`
	margin: 40px;
`;

const ContentBox = styled.div`
	cursor: pointer;
`;

const Wrap = styled.div`
	width: 604px;
	margin: 16px 0px;
	border: 1px solid gray;
	box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
	border-radius: 16px;
	background: #ffff;
	font-family: 'Pretendard';
	position: relative;

	.picked {
		position: absolute;
		top: 0;
		right: 0;
		width: 14px;
		height: 18px;
	}

	p {
		margin: 0 auto;
		width: 500px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: normal;
		word-wrap: break-word;
		// 크롬, 사파리 브라우저용 webkit 속성
		display: -webkit-box;
		-webkit-line-clamp: 6; // n번째 줄마다 ... 처리
		-webkit-box-orient: vertical;
		//min-height: 144px; // 폰트 라인 높이 * (보여주고 싶은 줄의 수)
		min-height: 50px;
		font-family: 'Pretendard';
		font-style: normal;
		font-weight: 400;
		font-size: 16px;
		line-height: 150%;
	}

	.user_info {
		display: flex;
		position: relative;
		margin-bottom: 18px;
		img {
			width: 40px;
			height: 40px;
			border-radius: 50%;
			background: #eeee;
		}
		.name_time {
			margin: 0 8px 0 16px;
			div {
				&:nth-of-type(1) {
					font-style: normal;
					font-weight: 700;
					font-size: 16px;
					line-height: 150%;
					color: #000000;
				}
				&:nth-of-type(2) {
					position: absolute;
					font-style: normal;
					font-weight: 400;
					font-size: 12px;
					line-height: 150%;
					color: #8e8e8e;
				}
			}
		}
		.job {
			font-style: normal;
			font-weight: 400;
			font-size: 14px;
			line-height: 150%;
			color: #5b5b5b;
			margin-right: 4px;
		}
		.corp {
			font-style: normal;
			font-weight: 400;
			font-size: 12px;
			line-height: 150%;
			color: #8e8e8e;
		}
	}

	.hashtags {
		display: flex;
		font-style: normal;
		font-weight: 400;
		font-size: 10px;
		line-height: 150%;
		color: #282828;
		margin-bottom: 16px;
		.post-tag {
			padding: 4px 12px;
			background: #cdd3ef;
			border-radius: 24px;
			margin-right: 8px;
		}
	}
	.title {
		margin-left: 0;
		font-style: normal;
		font-weight: 500;
		font-size: 20px;
		line-height: 150%;
		color: #000000;
		min-height: 0;
		margin-bottom: 8px;
	}
	.prev_text {
		margin-left: 0;
	}
	.like_box {
		margin-top: 18px;
		position: relative;
		img {
			width: 20px;
			height: 18px;
			margin-right: 16px;
		}
		.box {
			position: absolute;
			right: 0;
			top: 50%;
			transform: translateY(-50%);
			display: flex;
			font-style: normal;
			font-weight: 400;
			font-size: 12px;
			line-height: 150%;
			color: #5b5b5b;
			.liked {
				margin-right: 8px;
				cursor: pointer;
			}
			.commented {
				cursor: pointer;
			}
		}
	}
`;
