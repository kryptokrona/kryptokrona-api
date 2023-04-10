export const ssr = true;

import { getPosts } from "../api/hugin";
import { getRepoStats } from "../server/github.server";

export const load = async () => {
  async function fetchRepo() {
    return await getRepoStats();
  }
  /*
	async function fetchPosts()  {
		return await getPosts("1m");
	}*/
  return { repo: fetchRepo() /* huginStats: fetchPosts() */ };
};
