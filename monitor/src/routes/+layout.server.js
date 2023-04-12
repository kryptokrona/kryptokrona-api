export const ssr = true;

import {getCpuUsage1h} from "../api/prometheus";
import {getRepoStats} from "../server/github.server";
import {getNodes} from "../api/nodes";

export const load = async () => {
  async function fetchNodes() {
    return await getNodes();
  }
  async function fetchRepo() {
    return await getRepoStats();
  }
  await getCpuUsage1h()
  /*
	async function fetchPosts()  {
		return await getPosts("1m");
	}*/
  return {
    repo: fetchRepo(),
    nodes: fetchNodes() /* huginStats: fetchPosts() */,
  };
};
