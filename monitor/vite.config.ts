import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vite';

export default defineConfig({
	plugins: [sveltekit()],
	build: {
		outDir: "./build"
	},
	server: {
    	origin: 'http://127.0.0.1:3000/monitor',
  	},
});
