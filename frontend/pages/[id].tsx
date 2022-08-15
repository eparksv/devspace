import Layout from '../component/common/Layout/Layout';
import Develop from '../component/develop/Develop.page';
import { useRouter } from 'next/router';
import React, { useContext, useEffect } from 'react';
import { ContextDispatch, ContextUser } from './_app';
import Main from '../component/main/Main.page';

const Pages = () => {
	const router = useRouter();
	const user = useContext(ContextUser);
	const dispatch = useContext(ContextDispatch);

	const query = router.query.id;
	let component;

	useEffect(() => {
		if (query === 'oauth' && dispatch) {
			dispatch({ type: 'TOKEN', token: `${router.query.token}` });
			dispatch({ type: 'JOB', job: `${router.query.job}` });
			//console.log(user);
			router.replace('/');
		}
	}, [query]);

	switch (query) {
		case '/':
			component = <Main />;

			break;

		case 'develop':
			component = <Develop />;
			break;

		case 'designer':
			return <Layout>TEST~~~</Layout>;

		case 'oauth': //http://localhost:3001/oauth?token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsImlhdCI6MTY1OTE3MTIyNCwiZXhwIjoxNjU5MTczMDI0fQ.iKUgrGNZ1KrSlzQRa4U248ZkJYofG_yY7cVAl1k_O1uqBKktll9ks5yFk8mhPOlRx99v2WClYP5hIrHqyRwLVw&id=1&job=
			//router.query = {{token: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPT…1k_O1uqBKktll9ks5yFk8mhPOlRx99v2WClYP5hIrHqyRwLVw', id: 'oauth', job: ''}}
			//토큰 저장();
			//dispatch({ type: 'TOCKEN', payload: `${router.query.token}` });
			//job 값이 없으면 프로필설정 모달 set();
			// 그 후 다시 로그인? 아니면 백엔드에 user정보 요구.
			//그 후 메인페이지로 이동

			console.log('test:', user);
			break;
	}

	return (
		<>
			<Layout>{component}</Layout>;
		</>
	);
};

export default Pages;
