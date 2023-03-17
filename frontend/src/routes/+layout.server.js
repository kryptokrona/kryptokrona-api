export let ssr = true;

import { getRepoStats } from "../api/github-api";

export const load = async () => {
	let repo = await getRepoStats();
	return { repo };
};
