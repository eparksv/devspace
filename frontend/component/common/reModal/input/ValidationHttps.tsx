import React, { useEffect, useRef, useState } from 'react';
import CheckIcon from '@mui/icons-material/Check';
import ErrorIcon from '@mui/icons-material/Error';
import { StyledValidationSpc } from './ValidationStyle';

type Props = {
	checkPass: () => void;
};

function ValidationHttps({ checkPass }: Props) {
	const ref = useRef<HTMLInputElement>(null);

	const [pass, setPass] = useState<string>();
	const [inputMessage, setInputMessage] = useState('');

	const validateHttps = (e: React.ChangeEvent<HTMLInputElement>) => {
		if (ref.current == null) return;
		const data = ref.current.value.slice(0, 8);
		const text = 'https://';

		if (!data.includes(text)) {
			ref.current.parentElement?.classList.remove('pass');
			ref.current.parentElement?.classList.add('nopass');
			setPass('no');
			setInputMessage('올바른 형식으로 입력해주세요.');
		} else {
			ref.current.parentElement?.classList.remove('nopass');
			ref.current.parentElement?.classList.add('pass');
			setPass('ok');
			setInputMessage('');
		}

		if (ref.current.value.length === 0) {
			ref.current.parentElement?.classList.remove('pass');
			ref.current.parentElement?.classList.remove('nopass');
			setPass('');
			setInputMessage('');
		}
	};

	return (
		<StyledValidationSpc>
			<div className={`standard link`}>
				<input
					type='text'
					name='이름'
					placeholder='https://를 포함한 URL을 입력해주세요.'
					ref={ref}
					onKeyDown={(e) => {
						if (e.key === ' ') e.preventDefault();
					}}
					onBlur={(e) => {
						validateHttps(e);
						checkPass();
					}}
				/>
				{pass === 'ok' ? <CheckIcon /> : null}
				{pass === 'no' ? <ErrorIcon /> : null}
				<div className='text-box'>
					<p>{inputMessage}</p>
				</div>
			</div>
		</StyledValidationSpc>
	);
}

export default ValidationHttps;
