import React, { KeyboardEvent, useRef, useState } from 'react';
import { Cancel } from '@mui/icons-material';

function Tiptap_tag() {
	const tag = useRef<HTMLDivElement>(null);
	const target = useRef(0);

	const [taglist, setTaglist] = useState<string[]>([]);
	const [size, setSize] = useState(1);

	const makeTag = (e: KeyboardEvent<HTMLInputElement>) => {
		if (e.key == '#' || e.key === ' ') e.preventDefault();
	};

	const add = () => {
		if (taglist.length < 3) setTaglist(taglist.concat(''));
	};

	const val = (e: React.ChangeEvent<HTMLInputElement>, idx: number) => {
		const newValue = e.currentTarget.value;
		const newArr = [...taglist];
		newArr[idx] = newValue; //taglist.map()으로 모든 요소를 통과시키는거 보다 전부 복사 후 변경부분만 고치는게 나을 거 같아서...
		setTaglist(newArr);
	};

	const sizing = () => {};

	return (
		<>
			<label htmlFor='tag'>태그</label>
			{taglist.length !== 3 ? (
				<button className='tag-add' onClick={add}>
					+
				</button>
			) : null}
			{/*
    height: 30px;
    vertical-align: text-bottom; */}
			<div className='list' ref={tag}>
				{taglist.map((t, idx) => {
					return (
						<div
							className='tag-wrap'
							key={idx}
							onClick={() => {
								target.current = idx;
								//console.log('target:', target.current);
							}}>
							<span className='hash'>#</span>
							<input
								type='text'
								className='tag'
								name='tag'
								onKeyDown={makeTag}
								maxLength={20}
								value={t}
								onChange={(e) => val(e, idx)}
								size={taglist[idx] ? taglist[idx].length : 1} //onchange 이벤트 때문에 size도 계속 변함
							/>
							<button
								onClick={() => {
									setTaglist(
										taglist.filter((_, index) => {
											return idx != index;
										})
									);
								}}>
								<Cancel />
							</button>
						</div>
					);
				})}
			</div>
		</>
	);
}

export default Tiptap_tag;
