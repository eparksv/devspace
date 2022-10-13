/** @type {import('next').NextConfig} */
const nextConfig = {
	reactStrictMode: true,
	async rewrites() {
		return [
			{
				source: '/profiles/:path*', //api request path
				destination: 'http://localhost:8080/api/profiles/:path*', //목적 path. CORS 에러 방지
			},
			{
				source: '/posts/:path*', //api request path
				destination: 'http://localhost:8080/api/posts/:path*', //목적 path. CORS 에러 방지
			},
		];
	},
};

module.exports = nextConfig;
