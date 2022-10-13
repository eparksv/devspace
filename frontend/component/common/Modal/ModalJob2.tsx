import React, { useContext, useEffect, useRef, useState } from 'react';
import { useMutation } from '@tanstack/react-query';
import axios from 'axios';
import { StyledModalJob2, Wrap } from './Modal_style';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { ModalJob } from './ModalJob';
import ModalCampany from './ModalCampany';
import { ContextUser } from '@/pages/_app';

type modalProps = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	setModal?: React.Dispatch<React.SetStateAction<React.ReactNode>>;
	value: { name: string; text: string; [propName: string]: any };
	type: string | undefined;
};

export const ModalJob2 = ({ setOpen, setModal, value, type }: modalProps) => {
	//나중에 드롭다운으로 메뉴 더 생길수도.

	const { token } = useContext(ContextUser);

	const [jobs, setJobs] = useState([]);
	const [job, setJob] = useState<number>();

	useEffect(() => {
		(async () => {
			try {
				const res = await axios.get(`http://localhost:8080/api/jobs/${type}`, {
					headers: {
						Authorization: `Bearer ${token}`,
					},
				});
				const data = res.data.data;
				//console.log(data);
				setJobs(data);
			} catch (e) {
				console.log(e);
				alert('죄송합니다. 잠시후 다시 시도해주세요');
			}
		})();
	}, []);

	/*
	const mutation = useMutation(
		async (data: object) => {
			console.log('전송된 data', data);

			await axios.post(`/post`, data, {
				headers: {
					authorization: `Bearer ${token}`,
				},
			});
		},
		{
			onSuccess: () => {
				if (setModal) setModal(<ModalSignUpImage />);
				console.log('성공');
			},
			onError: (err) => {
				console.log(err);
				if (setModal) setModal(<ModalSignUpImage setOpen={setOpen} />);
			},
		}
	);
	*/

	const ref = useRef<HTMLDivElement>(null);

	useEffect(() => {
		setTimeout(() => ref.current?.focus());
	}, []);

	const select = (idx: number) => {
		if (ref.current !== null) {
			const buttons = ref.current.querySelectorAll('.job-button');

			/*버튼 클릭시 ref.current에 속한 모든 버튼의 on 클래스네임 제거, 클릭한 버튼만 클래스네임 on 주기.=> 섹상 변경을 위해
			-> useState로도 style 제어할 수 있지만, (동시에, 동일하게) 스타일 제어해야 할 버튼이 많아서 class로 css제어. (classNode사용)*/
			buttons.forEach((b, idx) => {
				b.classList.remove('job-on');
			});
			buttons[idx].classList.add('job-on');
			ref.current.querySelector('.next')?.classList.add('on');
		}
	};

	return (
		<Wrap ref={ref} tabIndex={0}>
			<StyledModalJob2>
				<h1>{type}</h1>

				{jobs.map((j: { id: number; title: string }, idx) => {
					return (
						<button
							className='job-button'
							key={idx}
							aria-label={j.title + ` 직군 선택`}
							onClick={() => {
								select(idx);
								setJob(j.id);
							}}>
							{j.title}
						</button>
					);
				})}

				<button
					className='next'
					onClick={(e) => {
						value.jobId = job;
						//console.log('job2 value', value);
						if (setModal && e.currentTarget.classList.contains('on'))
							setModal(
								<ModalCampany
									setOpen={setOpen}
									setModal={setModal}
									value={value}
								/>
							);
					}}>
					다음
				</button>

				<button
					className='prev'
					onClick={() => {
						if (setModal)
							setModal(
								<ModalJob setOpen={setOpen} setModal={setModal} value={value} />
							);
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
