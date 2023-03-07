import React, { useContext, useEffect, useRef, useState } from 'react';
import ModalWrap from '../reModal/modalwrap/ModalWrap';
import { StyledButton } from '../reModal/modalwrap/ModalWrapStyle';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import axios from 'axios';
import { ContextUser } from '@/pages/_app';
import { useMutation } from '@tanstack/react-query';
import { dataObj } from '../Modal/ModalPost';

type Props = {
	data?: dataObj;
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	setCateOn: React.Dispatch<React.SetStateAction<boolean>>;
};

function PostingCate({ data, setOpen, setCateOn }: Props) {
	const jobs = ['개발자', '디자이너', '기획자', '마케터', '창업'];
	const jobType = ['developer', 'designer', 'marketer', 'planner', 'startup'];

	const { token } = useContext(ContextUser); //나중에 로컬스토리지로 변경해야함.
	const { mutate } = useMutation(
		async (data: object) => {
			axios.post(`http://localhost:8080/api/posts`, data, {
				headers: {
					Authorization: `Bearer ${token}`,
				},
			});
		},
		{
			onError: (e) => {
				console.log(e);
				console.log('실패');
			},
			onSuccess: () => {
				// setLoading(false);
				// setOpen(false);
				console.log('성공');
			},
		}
	);

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
		if (data?.categoryId) data.categoryId = idx;
	};

	return (
		<div ref={ref} tabIndex={0} className='???'>
			<ModalWrap>
				<h1>카테고리 선택</h1>

				{jobs.map((j, idx) => {
					return (
						<StyledButton
							className='job-button'
							key={idx}
							aria-label={j + ` 직군 선택`}
							onClick={() => {
								select(idx);
								setType(jobType[idx]);
							}}>
							{j}
						</StyledButton>
					);
				})}

				<button
					className='next'
					onClick={(e) => {
						if (e.currentTarget.classList.contains('on') && data) {
							mutate(data);
						}
					}}>
					완료
				</button>

				<button
					className='prev'
					onClick={() => {
						setCateOn(false);
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

export default PostingCate;
