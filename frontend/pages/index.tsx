import React from 'react';
import type { GetServerSideProps, NextPage } from 'next';
import Layout from '../component/common/Layout/Layout';
import Main from '../component/main/Main.page';
//import { fData } from '../firebase';

const Home: NextPage = (/*{ data }: any*/) => {
	console.log('index start');
	return (
		<>
			<Layout>
				<Main /*data={data}*/ />
			</Layout>
		</>
	);
};

export default Home;

//getServerSideProps는 pages의 컴포넌트만 가능..!!
// export const getServerSideProps: GetServerSideProps = async (context) => {
// 	console.log('get', context);
// 	const data = context.params ? context.params : '/';

// 	return { props: { data } };
// };

/*export const getServerSideProps: GetServerSideProps = async () => {
	const res = await fData.collection('ds-Post').limit(5).get();
	//const data = res.forEach((d) => console.log(d.data()));
	const data = res.docs.map((d) => d.data());

	return { props: { data } };
};
/*
=> 오래 걸린다! 서버사이드인데 함수까지 돌려서 그런걸까??ㅠㅠ 
차라리 컴포넌트 함수내에서 불러오는게 더 빠르다.
*/
