import { PostArr } from '@/component/main/Main.page';
import { ContextUser } from '@/pages/_app';
import styled from '@emotion/styled';
import dayjs from 'dayjs';
import { useContext, useRef } from 'react';

type Props = {
	arr: PostArr;
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	comment?: boolean;
	setTopModalOpen: React.Dispatch<React.SetStateAction<string>>;
};

const PostedModal = ({ arr, setOpen, comment, setTopModalOpen }: Props) => {
	const user = useContext(ContextUser);
	const commnetRef = useRef<HTMLDivElement>(null);
	//console.log(user);

	if (comment) {
		setTimeout(() => {
			commnetRef.current?.focus();
		}, 50);
	}

	const handleTime = (time: string) => {
		const now = dayjs();
		let diff;
		//console.log('diff', now.diff(time, 'month'));
		if (now.diff(time, 'm') > 60) {
			diff = time.slice(0, 10);
		} else {
			diff = now.diff(time, 'm') + '분 전';
		}
		return diff;
	};

	return (
		<Wrapper>
			<Posted>
				<TopInfo>
					<div className='user_info'>
						<img
							src={arr.profile.image ? arr.profile.image : undefined}
							alt=''
						/>
						<div className='name_time'>
							<div>{arr.profile.name}</div>
							<div>{handleTime(arr.createdDate)}</div>
						</div>
						<div className='job'>{arr.profile.job}</div>
						<div className='corp'>
							{arr.profile.company && arr.profile.company}
						</div>
					</div>
					<div className='hashtags'>
						{arr.hashtags.length > 0
							? arr.hashtags.map((h, idx) => {
									return (
										<div className='post-tag' key={idx}>
											#{h}
										</div>
									);
							  })
							: null}
					</div>
				</TopInfo>
				<>
					{arr.title && <StyledH1>{arr.title}</StyledH1>}
					<Text>{arr.content}</Text>
					<IconBox>
						<FlexBox
							onClick={() =>
								setTopModalOpen(String(arr.id))
							} /* 유저 정보를 가져와서 스타일값 다르게 주기 */
						>
							<img src='/images/comment.png' alt='좋아요' />
							<span>좋아요</span>
							<span>{arr.likeCount}</span>
						</FlexBox>
						<FlexBox /* 유저 정보를 가져와서 스타일값 다르게 주기 */>
							<img src='/images/like.png' alt='댓글' />
							<span>댓글</span>
							<span>{arr.commentCount}</span>
						</FlexBox>
					</IconBox>
				</>
				<CommentBox ref={commnetRef} tabIndex={1}>
					{/* 나중에 코멘트 데이터가 생기면 댓글 컴포넌트 만들기. */}
					<MyCommentBox>
						{/* 유저 정보의 이미지 src*/}
						<img src={user.user.id ? user.user.id : ''} className='my_img' />
						{user.user.id ? (
							<Input placeholder='' />
						) : (
							<Input placeholder='로그인 후 댓글 작성이 가능합니다.' />
						)}
					</MyCommentBox>
				</CommentBox>
			</Posted>

			<Layer onClick={() => setOpen(false)} />
		</Wrapper>
	);
};

export default PostedModal;

const Wrapper = styled.div`
	position: fixed;
	width: 100vw;
	height: 100vh;
	top: 0;
	left: 0;
	z-index: 5;
`;
const Layer = styled.div`
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	background: rgba(0, 0, 0, 0.6); ;
`;
const Posted = styled.div`
	position: absolute;
	display: flex;
	flex-direction: column;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 604px;
	max-height: 60vh;
	background: white;
	border-radius: 16px;
	padding: 40pt;
	z-index: 6;
	overflow-y: scroll;
	/* 스크롤바 숨기기 */
	-ms-overflow-style: none; /* IE and Edge */
	&::-webkit-scrollbar {
		display: none; /* Chrome , Safari , Opera */
	}
`;

const TopInfo = styled.div`
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
`;

const StyledH1 = styled.h1`
	font-family: 'Pretendard';
	font-style: normal;
	font-weight: 500;
	font-size: 20px;
	line-height: 150%;
	color: #000000;
	margin: 0;
`;

const Text = styled.p`
	font-family: 'Pretendard';
	font-style: normal;
	font-weight: 400;
	font-size: 16px;
	line-height: 150%;
	color: #424242;
`;
const IconBox = styled.div`
	display: flex;
`;
const FlexBox = styled.div`
	display: flex;
	margin-right: 10px;
	cursor: pointer;

	img {
		width: 20px;
		margin-right: 10px;
	}
	> span {
		font-family: 'Pretendard';
		font-style: normal;
		font-weight: 400;
		font-size: 12px;
		line-height: 150%;
		color: #5b5b5b;
		margin-right: 4px;
	}
`;
const CommentBox = styled.div`
	position: relative;
	margin: 0 0 20px;
	padding: 15px 0 40px;
`;

const MyCommentBox = styled.div`
	position: absolute;
	width: 100%;
	display: flex;
	align-items: center;
	padding-top: 16px;
	&::before {
		content: '';
		display: block;
		clear: both;
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 1px;
		background: #dbdbdb;
	}
	.my_img {
		width: 40px;
		height: 40px;
		background: lightgrey;
		border-radius: 50%;
		margin-right: 10px;
	}
`;

const Input = styled.input`
	flex: auto;
	background: #f4f4f4;
	padding: 11px 48px 8px 12px;
	font-family: 'Pretendard';
	font-style: normal;
	font-weight: 400;
	font-size: 14px;
	line-height: 150%;
	border: none;
	border-radius: 24px;

	font-family: 'Pretendard';
	font-style: normal;
	font-weight: 400;
	font-size: 14px;
	line-height: 150%;

	&::placeholder {
		color: #8e8e8e;
	}
`;
