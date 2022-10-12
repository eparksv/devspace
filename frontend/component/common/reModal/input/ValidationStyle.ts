import styled from '@emotion/styled';

export const StyledValidationAll = styled.div`
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
`;

export const StyledValidationSpc = styled.div`
	width: 90%;
	margin: 16px auto 0;
	.standard {
		position: relative;
		input {
			width: 100%;
			font: 400 14px/21px 'Pretendard';
			color: #5b5b5b;
			border-radius: 8px;
			border: 1px solid #5b5b5b;
			resize: none;
			padding: 10px;
		}
		p {
			position: relative;
			font: 400 12px/1.5 'Pretendard';
			text-align: left;
			min-height: 16px;
			margin: 0px 10px;
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
		.text-box {
			display: flex;
			justify-content: space-between;
		}

		svg {
			position: absolute;
			top: 15%;
			right: 3%;
			width: 15px;
			height: 15px;
		}
	}
`;
