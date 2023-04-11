export const ssr = true;

import { getPosts } from "../api/hugin";
import { getThreads, getCpuUsage } from "../api/prometheus";
import { getRepoStats } from "../server/github.server";

export const load = async () => {
  await getCpuUsage();
  await getThreads();
  async function fetchRepo() {
    return await getRepoStats();
  }
  /*
	async function fetchPosts()  {
		return await getPosts("1m");
	}*/
  return { repo: fetchRepo() /* huginStats: fetchPosts() */ };
};
