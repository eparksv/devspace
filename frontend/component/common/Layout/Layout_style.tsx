import styled from '@emotion/styled';

export const StyledLayout = styled.div`
	width: 100%;
	position: relative;
	background: #f4f4f4;
`;

export const Inner = styled.div`
	width: 996px;
	border: 2px solid plum;
	margin-left: calc(((100% - (314px + 996px)) / 2) + 314px);
	.wrap {
		position: relative;
		display: flex;
		justify-content: space-between;
		width: 100%;
		padding-left: 24px;
		section {
			background: none;
			min-height: 150vh;
		}
	}
`;
