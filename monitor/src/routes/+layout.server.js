export let ssr = true;

import { getRepoStats } from "../server/github.server";

export const load = async () => {
	let repo = await getRepoStats();
	return { repo };
};
