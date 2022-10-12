//물어볼 부분 :
//1. 하드코딩 스타일로 일일히 css기입? emotion의 props 이용해서 동적으로 css제어?
//2. 하나의 style페이지에 모아둘지, 모달마다 style 페이지 만들어서 분산시킬지.

import styled from '@emotion/styled';

export const Wrap = styled.div`
	position: fixed;
	width: 100vw;
	height: 100vh;
	left: 0;
	top: 0;
	z-index: 5;
	background: #000000ad;
`;

export const StyledModal = styled.div`
	position: fixed;
	min-width: 20rem;
	background: #fff;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	p {
		position: relative;
	}
`;

export const WrapEditor = styled.div`
	width: 100%;
	max-height: 550px;
	overflow-y: auto;
`;

export const StyledModalSignIn = styled.div`
	position: fixed;
	width: 320px;
	height: 480px;
	text-align: center;
	background-image: url(./images/Background.svg),
		linear-gradient(179.87deg, #191c46 16.31%, #ffdae1 146.31%);
	background-repeat: no-repeat;
	background-position: bottom;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	border-radius: 24px;

	.signin-title {
		font: 700 28px/1.5 'Pretendard';
		text-align: center;
		margin-top: 100px;
		background: linear-gradient(89.97deg, #596acb -40.16%, #e9afb9 99.97%);
		-webkit-background-clip: text;
		-webkit-text-fill-color: transparent;
	}
	.signin-p {
		font: 400 14px/1.5 'Pretendard';
		text-align: center;
		color: #ffffff;
		width: 65%;
		margin: 35px auto 80px;
	}
	button {
		position: relative;
		width: 234px;
		height: 40px;
		font: 700 14px/1.5 'Pretendard';
		background-color: #ffffff;
		color: #5b5b5b;
		border-radius: 24px;
		border: none;
		cursor: pointer;
		margin-bottom: 16px;
		&:hover {
			box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
		}

		&:nth-of-type(2) {
			background-color: #000000;
			color: #ffffff;
			.icon {
				background: url('/images/github120.png') center/cover;
			}
		}

		.icon {
			width: 24px;
			height: 24px;
			position: absolute;
			top: 50%;
			transform: translateY(-50%);
			left: 16px;
			background: url('/images/google48.png') center/cover;
		}
	}
	.close {
		width: 24px;
		height: 24px;
		background: none;
		position: absolute;
		top: 21px;
		right: 17px;
		font: 700 22px/1.5 'Pretendard';
		color: #fff;
		&:hover {
			box-shadow: none;
			color: #808dd7;
		}
	}
`;

export const StyledModalPost = styled.div`
	position: absolute;
	background: #fff;
	width: 665px;
	min-height: 761px;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	padding: 0 40px 16px;
	border-radius: 24px;

	h1 {
		font: 700 20px/1.5 'Pretendard';
		margin: 40px 10px;
	}

	form {
		.editor-top {
			width: 100%;
			display: flex;
			justify-content: space-between;

			input[type='text'] {
				display: inline-block;
				max-width: 70%;
				padding: 5px 20px 5px 20px;
				background: #f4f4f4;
				border: none;
				border-radius: 24px;
				font: 500 20px/1.5 'Pretendard';
				color: #5b5b5b;
			}
		}

		.editor-menu {
			display: inline-block;
			position: absolute;
			top: 14.5%;
			right: 5%;
			width: 25%;
			> button {
				border: none;
				background: none;
				padding: 0;
				:active > svg {
					width: 36px;
					height: auto;
				}
			}
		}

		.ProseMirror {
			width: 98%;
			min-height: 450px;
			margin-top: 16px;
			font: 400 16px/24px 'Pretendard';
			border: none;
			border-top: 1px solid #dbdbdb;
			border-bottom: 1px solid #dbdbdb;
			padding: 10px 10px 0 10px;

			pre {
				background: #0d0d0d;
				color: #fff;
				font-family: 'JetBrainsMono', monospace;
				padding: 0.75rem 1rem;
				border-radius: 0.5rem;

				code {
					color: inherit;
					padding: 0;
					background: none;
					font-size: 0.8rem;
				}
			}
			p {
				position: relative;
				margin: 4px;
				top: auto;
				left: auto;
				transform: none;
			}
			img {
				display: inline-block;
				width: 100%;
				margin: 4px;
				object-fit: cover;

				&.ProseMirror-selectednode {
					outline: 3px solid #68cef8;
				}
			}
			blockquote {
				padding-left: 1rem;
				border-left: 2px solid #0d0d0d;
				margin: 1rem;
			}
		}
		input[type='submit'] {
			width: 100%;
			height: 65px;
			margin: 30px 0 10px;
			font: 700 16px/1.5 'Pretendard';
			text-align: center;
			color: #ffff;
			background: #212c6a;
			border-radius: 8px;
			border: none;
		}
	}
	.tags {
		display: flex;
		.tag-wrap {
			display: inline-block;
		}
		.tag {
			margin: 0px 10px 0 10px;
			font: 700 16px/1.5 'Pretendard';
			.tag-value {
				padding: 6px 8px;
				background: #cdd3ef;
				margin: 0 5px;
				border-radius: 24px;
				font: 400 14px/1.5 'arial';
				&:nth-of-type(1) {
					margin-left: 25px;
				}
			}
			.tag-close {
				color: #8e8e8e;
				border: none;
				background: none;
				font-size: 14px;
				margin: 0 0 0 3px;
				padding: 0;
				vertical-align: middle;
				> svg {
					width: 20px;
					height: auto;
				}
			}
			> input[type='text'] {
				min-width: 20px;
				border: none;
				padding: 0 0 0 10px;
				font: 400 14px/1.5 'arial';
				background: #cdd3ef;
			}
		}
		.add-tag {
			height: 30px;
		}
	}
	.close {
		position: absolute;
		top: 25px;
		right: 17px;
		border: none;
		background: none;
		font: 400 30px/1.5 'arial';
	}
`;

export const StyledModalSignUp = styled.div`
	position: fixed;
	width: 320px;
	background: #fff;
	border-radius: 24px;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	h1 {
		font: 700 20px/1.5 'Pretendard';
		color: #424242;
		text-align: center;
	}
	form {
		width: 90%;
		margin: 1rem auto 0;
		input,
		textarea {
			width: 100%;
			font: 400 14px/21px 'Pretendard';
			color: #5b5b5b;
			border-radius: 8px;
			border: 1px solid #5b5b5b;
			resize: none;
			padding: 10px;
		}
		.wrap-p {
			display: flex;
			justify-content: space-between;
			p {
				position: relative;
				font: 400 12px/0 'Pretendard';
				margin: 10px;
			}
		}
		.wrap-name {
			width: 100%;
			position: relative;
			input {
				height: 2rem;
			}
			svg {
				position: absolute;
				top: 25%;
				right: 3%;
				width: 0.7rem;
				height: 0.7rem;
			}
			p {
				position: relative;
				font: 400 12px/1.5 'Pretendard';
				text-align: left;
				min-height: 16px;
				margin: 5px 10px;
			}
			&.pass {
				p,
				svg {
					color: #88e25d;
				}
				input {
					border-color: #88e25d;
					&:focus {
						outline: 1px solid #88e25d;
					}
				}
			}
			&.nopass {
				p,
				svg {
					color: #ff5c00;
				}
				input {
					border-color: #ff5c00;
					&:focus {
						outline: 1px solid #ff5c00;
					}
				}
			}
		}

		.wrap-text {
			&.pass {
				p,
				svg {
					color: #88e25d;
				}
				textarea {
					border-color: #88e25d;
					&:focus {
						outline: 1px solid #88e25d;
					}
				}
			}
			&.nopass {
				p,
				svg {
					color: #ff5c00;
				}
				textarea {
					border-color: #ff5c00;
					&:focus {
						outline: 1px solid #ff5c00;
					}
				}
			}
			textarea {
				margin-top: 5px;
			}
		}

		input[type='submit'] {
			color: #fff;
			background: #212c6a;
			margin: 0.5rem auto;
			border: none;
			opacity: 0.5;
			cursor: pointer;
			&.on {
				opacity: 1;
				&:hover {
					background: #3a4db7;
				}
			}
		}
	}
	.close {
		position: absolute;
		top: 15px;
		right: 0.7rem;
		font: 600 20px/30px 'Pretendard';
		color: #5b5b5b;
		border: none;
		background: none;
		cursor: pointer;
		&:hover {
			color: #808dd7;
		}
	}
`;

export const StyledModalJob = styled.div`
	position: fixed;
	width: 320px;
	background: #fff;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	border-radius: 24px;

	h1 {
		font: 700 20px/1.5 'Pretendard';
		color: #424242;
		margin-bottom: 8px;
		text-align: center;
	}

	p {
		position: relative;
		width: 100%;
		padding: 0 32px;
		font: 400 14px/1.5 'Pretendard';
		margin-bottom: 19px;
	}

	button {
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
		margin: 16px auto;
		color: #fff;
		opacity: 0.5;
		font: 700 14px/21px 'Pretendard';
		background: #212c6a;
		border: none;
		text-align: center;
		&:hover {
			background: #212c6a;
		}
		&.on {
			opacity: 1;
			&:hover {
				background: #3a4db7;
			}
		}
	}
`;

export const StyledModalJob2 = styled.div`
	position: fixed;
	width: 320px;
	background: #fff;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	border-radius: 24px;

	h1 {
		font: 700 20px/1.5 'Pretendard';
		color: #424242;
		margin-bottom: 8px;
		text-align: center;
	}

	p {
		position: relative;
		width: 100%;
		padding: 0 32px;
		font: 400 14px/1.5 'Pretendard';
		margin-bottom: 19px;
	}

	button {
		display: block;
		position: relative;
		margin: 8px auto;
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
	}
`;

export const StyledModalAlert = styled.div``;

export const StyledModalImage = styled.div`
	position: fixed;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	width: 288px;
	height: 288px;
	background: url(./images/Astronaut.svg) no-repeat center/cover;
	opacity: 1;
	transition: 1s;

	p {
		width: 100%;
		font: 700 28px/1.5 'Pretendard';
		margin-top: 300px;
		text-align: center;
		background: linear-gradient(89.97deg, #596acb -40.16%, #e9afb9 99.97%);
		-webkit-background-clip: text;
		-webkit-text-fill-color: transparent;
	}
`;

export const StyledModalTest = styled.div`
	position: fixed;
	width: 604px;
	background: #fff;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	border-radius: 24px;

	.wrap {
		display: block;
		width: 100%;
		max-height: 450px;
		overflow-y: auto;

		.test1 {
			width: 100%;
			min-height: 450px;
		}
		.comment-list {
			width: 100%;
			min-height: 300px;
			padding-top: 5vmin;
			.comment {
				display: flex;
				justify-content: center;
				margin-bottom: 5vmin;

				.profile-image {
					min-width: 40px;
					height: 40px;
					border-radius: 50%;
					background: gray;
				}
				.text {
					width: 60%;
					border-radius: 16px;
					background: #dddd;
					font: 400 14px/1.5 'Pretendard';
				}
			}
		}
	}

	.close {
		position: fixed;
		top: 10px;
		right: 30px;
	}
`;

export const StyledModalCompany = styled.div`
	position: fixed;
	width: 320px;
	background: #fff;
	border-radius: 24px;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	h1 {
		font: 700 20px/1.5 'Pretendard';
		color: #424242;
		text-align: center;
	}
	.company-name,
	.career {
		width: 90%;
		margin: 1rem auto 0;
		input {
			width: 100%;
			font: 400 14px/21px 'Pretendard';
			color: #5b5b5b;
			border-radius: 8px;
			border: 1px solid #5b5b5b;
			resize: none;
			padding: 10px;
		}
		svg {
			position: absolute;
			top: 30%;
			right: 10%;
			width: 0.7rem;
			height: 0.7rem;
			transform: translateY(-25%);
		}
		.wrap-p {
			display: flex;
			justify-content: space-between;
			p {
				position: relative;
				font: 400 12px/0 'Pretendard';
				margin: 10px;
			}
		}
		p {
			position: relative;
			font: 400 12px/1.5 'Pretendard';
			text-align: left;
			min-height: 16px;
			margin: 5px 10px;
		}
		&.pass {
			p,
			svg {
				color: #88e25d;
			}
			input {
				border-color: #88e25d;
				&:focus {
					outline: 1px solid #88e25d;
				}
			}
		}
		&.nopass {
			p,
			svg {
				color: #ff5c00;
			}
			input {
				border-color: #ff5c00;
				&:focus {
					outline: 1px solid #ff5c00;
				}
			}
		}
	}

	.next {
		color: #fff;
		background: #212c6a;
		margin: 0.5rem auto;
		border: none;
		opacity: 0.5;
		cursor: pointer;
		&.on {
			opacity: 1;
			&:hover {
				background: #3a4db7;
			}
		}
	}

	.close {
		position: absolute;
		top: 15px;
		right: 0.7rem;
		font: 600 20px/30px 'Pretendard';
		color: #5b5b5b;
		border: none;
		background: none;
		cursor: pointer;
		&:hover {
			color: #808dd7;
		}
	}
`;

export const StyledModalAddLink2 = styled.div``;
