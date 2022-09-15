import styled from '@emotion/styled';

export const StyledSection = styled.section`
	position: relative;
	width: 604px;
	min-height: 100vh;
	margin-top: 4rem;
	border: 2px solid red;
`;

export const Button1 = styled.button`
	position: absolute;
`;

export const TextBar = styled.div`
	display: flex;
	border: 1px solid;
	width: 100%;
	height: 2rem;
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
