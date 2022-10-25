import React from 'react';
import ModalAddLink from '../ModalAddLink';
import { ModalProps, Value } from '../ModalTypes';
import { StyledButtonSkip } from './ButtonStyle';

type Obj = {
	url: string;
	title: string;
};

export interface Props extends ModalProps {
	value: Value;
	linkList: Obj[];
	type: string | undefined;
}

function ButtonSkipNext({ setOpen, setTest, type, value }: Props) {
	const next = () => {
		console.log(value);
		value.company = '';
		value.career = '';
		setTest(
			<ModalAddLink
				setOpen={setOpen}
				setTest={setTest}
				value={value}
				linkList={[{ url: '', title: '' }]}
				type={type}
			/>
		);
	};
	return (
		<StyledButtonSkip>
			<p>입력을 원하지 않으시나요?</p>
			<button onClick={next}>넘어가기</button>
		</StyledButtonSkip>
	);
}

export default ButtonSkipNext;
