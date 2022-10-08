import React, { useEffect, useRef, useState } from 'react';
import ModalSignUp2 from './ModalSignUp2';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ModalJob2 from './ModalJob2';
import ModalWrap from './modalwrap/ModalWrap';
import { ModalProps, Value } from './ModalTypes';

interface Props extends ModalProps {
	value: Value;
}

function ModalJob({ setOpen, setTest, value }: Props) {
	const jobs = ['개발자', '디자이너', '기획자', '마케터', '창업'];
	const jobType = ['developer', 'designer', 'marketer', 'planner', 'startup'];

	const [type, setType] = useState<string>();

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
		<div ref={ref} tabIndex={0}>
			<ModalWrap>
				<h1>직군 선택</h1>

				{jobs.map((j, idx) => {
					return (
						<button
							className='job-button'
							key={idx}
							aria-label={j + ` 직군 선택`}
							onClick={() => {
								select(idx);
								setType(jobType[idx]);
							}}>
							{j}
						</button>
					);
				})}

				<button
					className='next'
					onClick={(e) => {
						if (setTest && e.currentTarget.classList.contains('on'))
							setTest(
								<ModalJob2
									setOpen={setOpen}
									setTest={setTest}
									value={value}
									type={type}
								/>
							);
					}}>
					다음
				</button>

				<button
					className='prev'
					onClick={() => {
						if (setTest)
							setTest(<ModalSignUp2 setOpen={setOpen} setTest={setTest} />);
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
			</ModalWrap>
		</div>
	);
}

export default ModalJob;
