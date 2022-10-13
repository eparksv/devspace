import styled from '@emotion/styled';
import React from 'react';

function Tiptap_loading() {
	return (
		<Wrapper>
			<img src={'/images/loading2.gif'} alt='불러오는중' />
		</Wrapper>
	);
}

export default Tiptap_loading;

const Wrapper = styled.div`
	width: 90%;
	height: 100%;
	position: absolute;
	top: 0%;
	left: 5%;
	text-align: center;
	background: #ffffff91;
	img {
		position: absolute;
		transform: translate(-50%, -55%);
		top: 50%;
		left: 50%;
	}
`;
