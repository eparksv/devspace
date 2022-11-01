import React, { useContext, useEffect, useRef, useState } from 'react';
import ButtonSkip from './button/ButtonSkip';
import { ModalProps, Value } from './ModalTypes';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ModalWrap from './modalwrap/ModalWrap';
import ModalWriteLink from './ModalWriteLink';
import ButtonAdd from './button/ButtonAdd';
import ModalCompany from './ModalCompany';
import { StyledButtonSubmit } from './button/ButtonStyle';
import { StyledDiv } from './modalwrap/ModalWrapStyle';
import axios from 'axios';
import { ContextUser } from '@/pages/_app';

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
	const referenceLinks: object[] = [];
	const ref = useRef<HTMLDivElement>(null);
	const [link, setList] = useState<Obj[]>([]);
	const { token } = useContext(ContextUser);
	useEffect(() => {
		if (linkList) setList(linkList);
	}, []);

	return (
		<ModalWrap>
			<h1>링크 추가</h1>
			<p>개인 SNS나 웹사이트를 추가해 홍보해보세요.</p>

			<div ref={ref}>
				{link?.map((l, idx) => {
					console.log(l);
					if (l.url != '' && l.title != '') referenceLinks.push(l);
					console.log(referenceLinks);
					return l.url === '' ? null : (
						<StyledDiv key={idx} className={'list'}>
							{l.url}
						</StyledDiv>
					);
				})}
			</div>

			{link.length == 6 ? null : (
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
				onClick={async () => {
					value.referenceLinks = referenceLinks;
					console.log('body', value);
					{
						/*{
						"name": "이름",
						"introduction": "자기소개",
						"jobId": 1,
						"company": "회사",
						"career": "1년",
						"referenceLinks": [
							{
								"title": "구글",
								"url": "https://www.google.com"
							},
							{
								"title": "깃허브",
								"url": "https://www.github.com"
							}
						]
					}`
				*/
					}
					try {
						//처음 로그인 후(회원가입 안한 상태에서-세션에 기록없을때-) 딱 한번만 가능.
						const req = axios.post(
							'http://localhost:8080/api/profiles',
							{
								name: value.name,
								introduction: value.introduction,
								jobId: value.jobId,
								company: value.company, //?
								career: value.career, //?
								referenceLinks: value.referenceLinks,
							},
							{
								headers: {
									Authorization: `Bearer ${token}`,
								},
							}
						);
						const data = await req;
						console.log(data.data);
						console.log('?', value.company);
					} catch (e) {
						console.log('e', e);
						alert('죄송합니다. 잠시후 다시 시도해주세요');
					}
				}}>
				가입완료
			</StyledButtonSubmit>

			<ButtonSkip value={value} setOpen={setOpen} />

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
