import { PostArr } from '@/component/main/Main.page';
import { ContextUser } from '@/pages/_app';
import styled from '@emotion/styled';
import { useContext, useRef } from 'react';

type Props = {
	arr: PostArr;
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	comment?: boolean;
};

const PostedModal = ({ arr, setOpen, comment }: Props) => {
	const user = useContext(ContextUser);
	const commnetRef = useRef<HTMLDivElement>(null);
	//console.log(user);

	if (comment) {
		setTimeout(() => {
			commnetRef.current?.focus();
		}, 50);
	}

	return (
		<Wrapper>
			<Posted>
				<div>{arr.content}</div>
				<CommentBox ref={commnetRef} tabIndex={1}>
					{/* 나중에 코멘트 데이터가 생기면 댓글 컴포넌트 만들기. */}
					<MyCommentBox>
						{/* 유저 정보의 이미지 src*/}
						<img src={user.user.id ? user.user.id : 'no'} className='my_img' />
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
	background: #22222281;
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
	z-index: 6;
	overflow-y: scroll;
	/* 스크롤바 숨기기 */
	-ms-overflow-style: none; /* IE and Edge */
	&::-webkit-scrollbar {
		display: none; /* Chrome , Safari , Opera */
	}
`;
const CommentBox = styled.div`
	position: relative;
	margin: 20px 0;
	padding: 15px 0;
`;

const MyCommentBox = styled.div`
	position: absolute;
	width: 100%;
	display: flex;
	align-items: center;
	&::before {
		content: '';
		display: block;
		clear: both;
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 2px;
		background: #666666;
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
`;
