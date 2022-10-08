import React, { useEffect, useState } from 'react';
import ButtonSkip from './button/ButtonSkip';
import { ModalProps, Value } from './ModalTypes';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ModalWrap from './modalwrap/ModalWrap';
import ModalWriteLink from './ModalWriteLink';
import ButtonAdd from './button/ButtonAdd';
import ModalCompany from './ModalCompany';
import { StyledButtonSubmit } from './button/ButtonStyle';
import { StyledDiv } from './modalwrap/ModalWrapStyle';

type Obj = {
	url: string;
	title: string;
};
export interface Props extends ModalProps {
	value: Value;
	linkList: Obj[];
	type: string | undefined;
}

function ModalAddLink({ setOpen, setTest, linkList, value, type }: Props) {
	const [link, setList] = useState<Obj[]>([]);

	useEffect(() => {
		if (linkList) setList(linkList);
	});

	return (
		<ModalWrap>
			<h1>링크 추가</h1>
			<p>개인 SNS나 웹사이트를 추가해 홍보해보세요.</p>

			{link?.map((l, idx) => {
				return (
					<StyledDiv key={idx} className={'list'}>
						{l.url}
					</StyledDiv>
				);
			})}

			{link.length == 5 ? null : (
				<ButtonAdd
					setOpen={setOpen}
					setTest={setTest}
					linkList={link}
					value={value}
					type={type}
				/>
			)}

			<StyledButtonSubmit
				className='submit'
				onClick={() => {
					if (setTest) {
						setTest(
							<ModalWriteLink
								setOpen={setOpen}
								setTest={setTest}
								linkList={[]}
								value={value}
								type={type}
							/>
						);
					}
				}}>
				가입완료
			</StyledButtonSubmit>

			<ButtonSkip setOpen={setOpen} setTest={setTest} submit={'sub'} />

			<button
				className='prev'
				onClick={() => {
					if (setTest)
						setTest(
							<ModalCompany
								setOpen={setOpen}
								setTest={setTest}
								value={value}
								type={type}
							/>
						);
				}}>
				<ArrowBackIcon />
			</button>

			<button className={'close'} onClick={() => setOpen(false)}>
				X
			</button>
		</ModalWrap>
	);
}

export default ModalAddLink;
