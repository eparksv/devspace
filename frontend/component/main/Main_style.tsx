import styled from '@emotion/styled';

export const StyledSection = styled.section`
	position: relative;
	width: 604px;
	min-height: 100vh;
	margin-top: 4rem;
	//border: 2px solid red;
`;

export const Button1 = styled.button`
	position: absolute;
`;

export const TextBar = styled.div`
	display: flex;
	width: 100%;
	height: 2rem;
	position: relative;
	align-items: center;
	justify-content: space-between;
	gap: 16px;

	.mainTopWrap {
		background: white;
		border-radius: 24px;
		height: 40px;
		flex: 1;
		position: relative;
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 0 11px;
		font-family: 'Pretendard';
		font-style: normal;
		font-weight: 700;
		font-size: 14px;
		line-height: 150%;

		&:hover {
			box-shadow: 0px 4px 4px 0px #00000040;
		}
	}
`;

export const StyledArticle = styled.article`
	width: 90%;
	max-height: 25rem;
	margin: 3rem auto;
	border: 1px solid black;
	color: #444;

	.prev {
		p {
			position: relative;
			width: 800px;
			text-overflow: ellipsis;
			overflow: hidden;
			white-space: nowrap;
		}
	}
`;
