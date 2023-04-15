export const ssr = true;

import {
  getCpuUsage,
  getCpuUsageOverTime,
  getRamUsage,
  getRamUsageOverTime,
  getDiskUsage,
  getDiskUsageOverTime,
  getUptime,
  getThreads,
} from "../api/prometheus";
import { getRepoStats } from "../server/github.server";
import { getNodes } from "../api/nodes";

export const load = async () => {
  async function fetchPrometheus() {
    let cpuUsage = await getCpuUsage();
    let cpuUsageOverTime = await getCpuUsageOverTime("1h");
    let ramUsage = await getRamUsage();
    let uptime = await getUptime();
    let threads = await getThreads();
    let diskUsage = await getDiskUsage();
    let ramUsageOverTime = await getRamUsageOverTime("1h");
    let diskUsageOverTime = await getDiskUsageOverTime("1h");
    return {
      cpuUsage,
      diskUsage,
      ramUsage,
      cpuUsageOverTime,
      ramUsageOverTime,
      diskUsageOverTime,
      uptime,
      threads,
    };
  }
  async function fetchNodes() {
    return await getNodes();
  }
  async function fetchRepo() {
    return await getRepoStats();
  }
  return {
    repo: fetchRepo(),
    nodes: fetchNodes(),
    prometheus: fetchPrometheus(),
  };
};
