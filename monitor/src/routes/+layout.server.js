export const ssr = true;
import { getRepoStats } from "../server/github.server";
import { getNodes } from "../api/nodes";

export const load = async () => {
  async function fetchNodes() {
    return await getNodes();
  }
  async function fetchRepo() {
    return await getRepoStats();
  }
  /*
	async function fetchPosts()  {
		return await getPosts("1m");
	}*/
  return {
    repo: fetchRepo(),
    nodes: fetchNodes() /* huginStats: fetchPosts() */,
  };
};
