import React, { useEffect, useRef, useState } from 'react';
import { useMutation } from '@tanstack/react-query';
import axios from 'axios';
import { StyledModalJob, StyledModalJob2, Wrap } from './Modal_style';
import { ModalSignUp } from './ModalSignUp';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { ModalJob } from './ModalJob';

type modalProps = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	setModal?: React.Dispatch<React.SetStateAction<React.ReactNode>>;
	value?: { name: string; text: string | undefined };
	token?: string;
	jobNumber: string | undefined;
};

export const ModalJob2 = ({
	setOpen,
	setModal,
	value,
	token,
	jobNumber,
}: modalProps) => {
	//나중에 드롭다운으로 메뉴 더 생길수도.

	const [jobs, setJobs] = useState<string[]>([]);
	const [job, setJob] = useState<string>();

	useEffect(() => {
		if (!job)
			switch (jobNumber) {
				case '개발자': {
					setJobs([
						'풀스택개발자',
						'프론트엔드',
						'백엔드',
						'미들티어개발자',
						'퍼블리셔',
						'모바일개발자',
						'그래픽개발자',
						'데이터사이언티스트',
					]);
					break;
				}
				case '디자이너': {
					setJobs([
						'UIUX디자이너',
						'편진디자이너',
						'제품디자이너',
						'그래픽디자이너',
						'브랜드디자이너',
						'영상디자이너',
						'웹디자이너',
					]);
					break;
				}
				case '기획자': {
					setJobs([
						'프로젝트관리자',
						'전략기획자',
						'서비스기획자',
						'콘텐츠기획자',
						'일반기획자',
					]);
					break;
				}
				case '마케터': {
					setJobs([
						'콘텐츠마케터',
						'브랜드마케터',
						'퍼포먼스마케터',
						'일반마케터',
					]);
					break;
				}
				case '창업': {
					setJobs(['대표', 'CEO', '스타트업']);
					break;
				}
			}
	}, []);

	const mutation = useMutation(
		async (data: object) => {
			console.log(data);
			console.log(`${token}`);

			await axios.post(`/post`, data, {
				headers: {
					authorization: `Bearer ${token}`,
				},
			});
		},
		{
			onSuccess: () => setOpen(false),
			onError: (err) => console.log(err),
		}
	);

	const ref = useRef<HTMLDivElement>(null);

	useEffect(() => {
		setTimeout(() => ref.current?.focus());
	}, []);

	const select = (idx: number) => {
		if (ref.current !== null) {
			const buttons = ref.current.querySelectorAll('.job-button');
			buttons.forEach((b, idx) => {
				b.classList.remove('job-on');
			});
			buttons[idx].classList.add('job-on');
			ref.current.querySelector('.submit')?.classList.add('on');
		}
	};

	//버튼 클릭시 ref.current에 속한 모든 버튼의 on 클래스네임 제거, 클릭한 버튼만 클래스네임 on 주기.=> 섹상 변경을 위해
	return (
		<Wrap ref={ref} tabIndex={0}>
			<StyledModalJob2>
				<h1>{jobNumber}</h1>

				{jobs.map((j, idx) => {
					return (
						<button
							className='job-button'
							key={idx}
							aria-label={j + ` 직군 선택`}
							onClick={() => {
								select(idx);
								setJob(j);
							}}>
							{j}
						</button>
					);
				})}

				<button
					className='submit'
					onClick={(e) => {
						console.log(value);
						if (value && e.currentTarget.classList.contains('on')) {
							console.log({
								name: `${value.name}`,
								introduction: `${value.text}`,
								jobId: job,
							});
							mutation.mutate({
								name: `${value.name}`,
								introduction: `${value.text}`,
								jobId: 1, //job,
							});
						}
					}}>
					가입완료
				</button>

				<button
					className='prev'
					onClick={() => {
						if (setModal)
							setModal(<ModalJob setOpen={setOpen} setModal={setModal} />);
					}}
					arira-label='이전 단계로'>
					<ArrowBackIcon />
				</button>

				<button
					className='close'
					onClick={() => setOpen(false)}
					arira-label='닫기'>
					X
				</button>
			</StyledModalJob2>
		</Wrap>
	);
};

export default ModalJob2;
