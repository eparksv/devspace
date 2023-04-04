import styled from '@emotion/styled';
import dayjs from 'dayjs';
import React, { useState } from 'react';
import PostedModal from './PostedModal';
import { PostArr } from '@/component/main/Main.page';

type Props = {
	a: PostArr;
};

function Card({ a }: Props) {
	const [open, setOpen] = useState(false);
	const [topModalOpen, setTopModalOpen] = useState<string>('');
	const [test, setTest] = useState<boolean | React.ReactNode>(false);

	const time = a.createdDate;
	const now = dayjs();

	const getLike = (id: number) => {
		/* 모달을 키고, 그 모달에 id값을 넘겨주어서 모달안에서 API get().;*/
		setTopModalOpen(String(id));
	};

	const handleModal = (data: PostArr, comment?: boolean) => {
		setOpen(true);
		setTest(
			<PostedModal
				arr={data}
				setOpen={setOpen}
				comment={comment}
				setTopModalOpen={setTopModalOpen}
			/>
		);
	};

	let diff;
	//console.log('diff', now.diff(time, 'month'));
	if (now.diff(time, 'm') > 60) {
		diff = a.createdDate.slice(0, 10);
	} else {
		diff = now.diff(time, 'm') + '분 전';
	}
	return (
		<div>
			<Wrap key={a.id}>
				<Wrapper>
					<div className='user_info'>
						<img src={a.profile.image ? a.profile.image : undefined} alt='' />
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
							<div className='liked' onClick={() => getLike(2)}>
								좋아요 {a.likeCount}
							</div>
							<div className='commented' onClick={() => handleModal(a, true)}>
								댓글 {a.commentCount}
							</div>
						</div>
					</div>
					{/*로그인 && <img className='picked' src='/images/pick.png' alt='' />*/}
				</Wrapper>
			</Wrap>
		</div>
	);
}

export default Card;
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

	&:hover {
		box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	}

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
