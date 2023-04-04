import styled from '@emotion/styled';
import { useQuery } from '@tanstack/react-query';
import axios from 'axios';
import { PostArr } from '../main/Main.page';
import Card from '../common/Posted/Card';

type PostData = {
	data: {
		data: {
			posts: PostArr[];
		};
	};
};

const Profile = () => {
	const { data, isLoading, isError } = useQuery<PostData>(
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

	return (
		<StyledBody>
			<StyledHeader>
				<StyledProfileImg></StyledProfileImg>
				<StyledProfile></StyledProfile>
			</StyledHeader>
			<StyledProfileP>sdfsddfdf</StyledProfileP>
			{data?.data.data.posts.map((ele, idx) => {
				return <Card a={ele} key={idx} />;
			})}
			<StyledProfileLink></StyledProfileLink>
		</StyledBody>
	);
};

export default Profile;

const StyledBody = styled.div`
	min-height: 100vh;
	width: 100%;
	position: relative;
`;

const StyledHeader = styled.div`
	background-color: gray;
	height: 264px;
	width: 100%;
	position: relative;
	margin-top: 54px;
`;

const StyledProfileImg = styled.img`
	width: 248px;
	height: 248px;
	position: absolute;
	top: 50%;
	left: 24px;
	border-radius: 50%;
	background-color: silver;
`;

const StyledProfile = styled.div`
	min-height: 118px;
	width: 200px;
	border: 1px solid orange;
	position: absolute;
	top: 100%;
	left: 272px;
	padding: 16px 0 0 32px;
`;

const StyledProfileP = styled.p`
	margin: 164px 24px 11.5px;
	font-family: 'Pretendard';
	font-style: normal;
	font-weight: 400;
	font-size: 16px;
	line-height: 150%;
`;
const StyledProfileLink = styled.div``;
