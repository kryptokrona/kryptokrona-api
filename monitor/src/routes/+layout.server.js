export const ssr = true;

import { getCpuUsage } from "../api/prometheus";
import { getRepoStats } from "../server/github.server";
import { getNodes } from "../api/nodes";

export const load = async () => {
  let usage = await getCpuUsage();

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
    nodes: fetchNodes(),
    prometheus: {
      cpuUsage: usage,
    } /* huginStats: fetchPosts() */,
  };
};
