import styled from '@emotion/styled';

export const StyledWrap = styled.div`
	position: fixed;
	width: 100vw;
	height: 100vh;
	left: 0;
	top: 0;
	z-index: 5;
	background: #000000ad;
`;

export const StyledModalWrap = styled.div`
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	width: 320px;
	min-height: 250px;
	background: #ffffff;
	border-radius: 24px;
	h1 {
		font: 700 20px/1.5 'Pretendard';
		color: #424242;
		text-align: center;
	}
	p {
		font: 400 14px/1.5 'Pretendard';
		color: #8e8e8e;
		text-align: center;
	}

	.close,
	.prev {
		position: absolute;
		top: 17px;
		width: 24px;
		height: 24px;
		color: #5b5b5b;
		font: 400 20px/1 'arial';
		border: none;
		background: none;
		cursor: pointer;
		&:hover {
			background: none;
			color: #808dd7;
		}
	}

	.close {
		right: 16px;
	}

	.prev {
		left: 0.5rem;
		width: 32px;
		svg {
			font: 400 24px/1 'arial';
		}
	}

	.next {
		position: relative;
		display: block;
		padding: 0 16px;
		width: 90%;
		height: 48px;
		border-radius: 8px;
		cursor: pointer;
		margin: 16px auto;
		color: #fff;
		opacity: 0.5;
		font: 700 14px/21px 'Pretendard';
		background: #212c6a;
		border: none;
		text-align: center;
		&.on {
			opacity: 1;
			&:hover {
				background: #3a4db7;
			}
		}

		&.next-company {
			margin: 24px auto 8px;
		}
	}
`;

export const StyledDiv = styled.div`
	width: 284px;
	height: 32px;
	border: 1px solid #dbdbdb;
	border-radius: 8px;
	margin: 8px auto 0;
	padding-left: 12px;
	font-family: Pretendard;
	font-size: 14px;
	font-weight: 400;
	line-height: 28px;
	letter-spacing: 0em;
	text-align: left;
`;

export const StyledButton = styled.button`
	display: block;
	position: relative;
	margin: 8px auto;
	padding: 0 16px;
	color: #424242;
	background: none;
	width: 90%;
	height: 48px;
	font: bold 16px/1.5 'Pretendard';
	text-align: left;
	border: 1px solid #dbdbdb;
	border-radius: 8px;
	cursor: pointer;
	&:hover,
	&.job-on {
		background: #cdd3ef;
		border: none;
	}
`;
