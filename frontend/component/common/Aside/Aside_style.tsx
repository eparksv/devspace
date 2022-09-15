import styled from '@emotion/styled';

export const StyledAside = styled.aside`
	position: relative;
	top: 20px;
	right: 24px;
	width: 290px;
	border: 1px solid green;
	.any {
		border: 1px solid;
		width: 100%;
		height: 300px;
		margin-top: calc(4rem - 20px);
		background-image: url(./images/Astronaut.svg);
		background-repeat: no-repeat;
		background-position: center;
		background-size: 420px;
	}
`;

export const AsideBox = styled.div`
	width: 90%;
	height: 13rem;
	margin: 1rem auto;
	border: 1px solid gray;
	background: #ffff;
`;

export const AsideWrap = styled.div`
	margin-top: 20px;
	position: sticky;
	top: 20px;
`;
