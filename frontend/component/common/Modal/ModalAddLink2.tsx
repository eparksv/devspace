import React, { useContext, useState } from 'react';
import { Wrap, StyledModalAddLink2, StyledModal } from './Modal_style';
import InputTypeText from './components/InputTypeText';
import axios from 'axios';
import { ContextUser } from '@/pages/_app';

type modalProps = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	setModal?: React.Dispatch<React.SetStateAction<React.ReactNode>>;
};

function ModalAddLink2({ setOpen }: modalProps) {
	const [on, setOn] = useState(false);
	const { token } = useContext(ContextUser);

	const submit = async () => {
		console.log('~');
		const res = await axios.post(
			`profiles`,
			{
				name: '수정',
				introduction: 'test',
				jobId: '1',
				company: 'dev',
				career: '0',
				referenceLinks: [
					{
						title: 'null',
						url: 'https://df',
					},
				],
			},
			{
				headers: {
					Authorization: `Bearer ${token}`,
				},
			}
		);
		console.log(res.data);
	};
	return (
		<Wrap>
			<StyledModal>
				<h1>링크입력</h1>
				<p>링크 주소를 입력...</p>
				<InputTypeText
					name={'link'}
					placeholder={'주소를 입력해주세요'}
					maxLen={30}
					setOn={setOn}
				/>
				<button className={`next submit ${on ? 'on' : ``}`} onClick={submit}>
					가입완료
				</button>
				<button className='skip'>넘어가기</button>
				<button className='close' onClick={() => setOpen(false)}>
					x
				</button>
			</StyledModal>
		</Wrap>
	);
}
export default ModalAddLink2;
