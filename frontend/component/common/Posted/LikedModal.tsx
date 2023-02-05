import { likeData } from '@/component/main/Main.page';
import styled from '@emotion/styled';
import { useQuery } from '@tanstack/react-query';
import axios from 'axios';
import React, { useEffect } from 'react';

type Props = {
	setTopModalOpen: React.Dispatch<React.SetStateAction<string>>;
	id: string;
};

function LikedModal({ setTopModalOpen, id }: Props) {
	const { data } = useQuery<likeData>(
		['postLike'],
		async () =>
			axios.get(`http://localhost:8080/api/like/${Number(id)}?page=0&size=8`),
		{
			staleTime: Infinity,
			cacheTime: Infinity,
			onSuccess: (res) => console.log('??', res),
			onError: () => console.log('x'),
		}
	);

	useEffect(() => {
		console.log('data를 불러줘야지 useQuery가 데이터를 get()하지', data);
	}, []);

	return (
		<Wrapper>
			<Content>
				<p>좋아요</p>
				{/* 좋아요 누른 사람들 리스트. 최신순 나열 */}
				<List></List>
				<button className='close' onClick={() => setTopModalOpen('')}>
					<img src='/images/CloseImg.png' alt='close' />
				</button>
			</Content>
			<Layer onClick={() => setTopModalOpen('')} />
		</Wrapper>
	);
}

export default LikedModal;

const Wrapper = styled.div`
	position: fixed;
	width: 100vw;
	height: 100vh;
	top: 0;
	left: 0;
	z-index: 6;
`;

const Content = styled.div`
	position: absolute;
	display: flex;
	flex-direction: column;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 320px;
	height: 574px;
	background: white;
	border-radius: 16px;
	padding: 12px;
	z-index: 7;
	/* 스크롤바 숨기기 */

	> p {
		margin: 0;
		font-family: 'Pretendard';
		font-style: normal;
		font-weight: 700;
		font-size: 20px;
		line-height: 150%;
		text-align: center;
		color: #424242;
	}

	.close {
		width: 14px;
		height: 14px;
		position: absolute;
		top: 12px;
		right: 12px;
		padding: 0;
		border: none;
		background: transparent;
		cursor: pointer;
		> img {
			position: absolute;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;

			&:hover {
				filter: invert(78%) sepia(76%) saturate(4961%) hue-rotate(209deg)
					brightness(91%) contrast(84%);
			}
		}
	}
`;
const List = styled.ul`
	list-style: none;
	height: 476px;
	overflow-y: auto;
	padding: 0;
	margin: 0;
`;
const Layer = styled.div`
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	background: rgba(0, 0, 0, 0.6); ;
`;
