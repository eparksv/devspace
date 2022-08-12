import React, { useEffect, useRef, useState } from 'react';
import { useMutation } from '@tanstack/react-query';
import axios from 'axios';
import { StyledModalJob, Wrap } from './Modal_style';
import { ModalSignUp } from './ModalSignUp';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ModalJob2 from './ModalJob2';

type modalProps = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	setModal?: React.Dispatch<React.SetStateAction<React.ReactNode>>;
	value?: { name: string; text: string | undefined };
	token?: string;
};

export const ModalJob = ({ setOpen, setModal, value, token }: modalProps) => {
	const jobs = ['개발자', '디자이너', '기획자', '마케터', '창업'];

	const [job, setJob] = useState<string>();

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
			ref.current.querySelector('.next')?.classList.add('on');
		}
	};

	return (
		<Wrap ref={ref} tabIndex={0}>
			<StyledModalJob>
				<h1>직군 선택</h1>

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
					className='next'
					onClick={(e) => {
						if (setModal && e.currentTarget.classList.contains('on'))
							setModal(
								<ModalJob2
									setOpen={setOpen}
									setModal={setModal}
									token={token}
									value={value}
									jobNumber={job}
								/>
							);
					}}>
					다음
				</button>

				<button
					className='prev'
					onClick={() => {
						if (setModal)
							setModal(<ModalSignUp setOpen={setOpen} setModal={setModal} />);
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
			</StyledModalJob>
		</Wrap>
	);
};
