import styled from '@emotion/styled';

export const StyledButtonSkip = styled.div`
	display: flex;
	justify-content: center;

	p {
		font: 400 12px/1.5 'Pretendard';
		color: #c1c1c1;
		margin-right: 8px;
	}
	button {
		border: none;
		font: 400 12px/1.5 'Pretendard';
		text-decoration: underline;
		color: #212c6a;
		background: none;
		padding: 0;
	}
`;

export const StyledButtonAdd = styled.button`
	width: 90%;
	height: 48px;
	border-radius: 8px;
	cursor: pointer;
	margin: 19px auto 16px;
	position: relative;
	display: block;
	font: 700 14px/21px 'Pretendard';
	background: #212c6a;
	border: none;
	text-align: center;
	color: #ffff;
	opacity: 0.5;
	&:hover {
		background: #3a4db7;
	}
	&.on {
		opacity: 1;
	}
`;

export const StyledButtonSubmit = styled.button`
	width: 90%;
	height: 48px;
	border-radius: 8px;
	cursor: pointer;
	margin: 16px auto 0;
	position: relative;
	display: block;
	font: 700 14px/21px 'Pretendard';
	background: #212c6a;
	border: none;
	text-align: center;
	color: #ffff;
`;
