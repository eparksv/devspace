import { ContextUser } from '@/pages/_app';
import axios from 'axios';
import React, { useContext } from 'react';
import { Value } from '../ModalTypes';
import { StyledButtonSkip } from './ButtonStyle';

type Props = {
	value: Value;
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
};

function ButtonSkip({ setOpen, value }: Props) {
	const { token } = useContext(ContextUser);
	const submit = async () => {
		console.log('body', value);
		try {
			const req = axios.post(
				'profiles',
				{
					name: value.name,
					introduction: value.introduction,
					jobId: value.jobId,
					company: '한글',
					career: '1년',
					referenceLinks: [],
				},
				{
					headers: {
						Authorization: `Bearer ${token}`,
					},
				}
			);
			const data = await req;
		} catch (e) {
			console.log('e', e);
			alert('죄송합니다. 잠시후 다시 시도해주세요');
		}
	};
	return (
		<StyledButtonSkip>
			<p>입력을 원하지 않으시나요?</p>
			<button onClick={submit}>넘어가기</button>
		</StyledButtonSkip>
	);
}

export default ButtonSkip;
