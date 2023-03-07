import styled from '@emotion/styled';

const Profile = () => {
	return (
		<StyledBody>
			<StyledHeader>
				<StyledProfileImg></StyledProfileImg>
				<StyledProfile></StyledProfile>
			</StyledHeader>
			<StyledProfileP>sdfsddfdf</StyledProfileP>
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
