import { PrometheusDriver } from "prometheus-query";
import dotenv from "dotenv";

dotenv.config();
try {
let prom = new PrometheusDriver({
  endpoint: `https://${process.env.PUBLIC_PROMETHEUS_URL}/prometheus`,
  baseURL: "/api/v1", // default value
});
} catch(error) {
  console.error(error)
}

console.log("PROMETHEUS DRIVER: ", prom);

export async function getCpuUsage() {
  try {
    let usage = 0;
    const q = "100 - (avg by (instance) (rate(node_cpu_seconds_total{job='node',mode='idle'}[1m])) * 100)";
    const response = await prom.instantQuery(q);
    response.result.forEach((serie) => {
      usage = usage + serie.value.value;
    });
    return usage;
  } catch (error) {
    console.error(error);
  }
  return 0;
}
export async function getCpuUsageOverTime(time) {
  switch (time) {
    case "1h":
      return await getCpuUsage1h();
    case "24h":
      return await getCpuUsage24h();
    case "7d":
      return await getCpuUsage7d();
    case "30d":
      return await getCpuUsage30d();
  }
}

export async function getCpuUsage1h() {
  try {
    const start = new Date().getTime() - 60 * 60 * 1000;
    const end = new Date();
    const step = 10 * 60;
    let values = [];
    let times = [];
    const q = "100 - (avg by (instance) (rate(node_cpu_seconds_total{job='node',mode='idle'}[1m])) * 100)";
    const response = await prom.rangeQuery(q, start, end, step); // wait for the promise to resolve
    response.result.forEach((serie) => {
      serie.values.forEach((value) => {
        values.push(value.value);
        times.push(value.time);
      });
    });
    return { values, times };
  } catch (error) {
    console.error(error);
  }
  return { values: [], time: [] };
}

export async function getCpuUsage24h() {
  try {
    const start = new Date().getTime() - 60 * 60 * 24 * 1000;
    const end = new Date();
    const step = 60 * 60;
    let values = [];
    let times = [];
    const q = "100 - (avg by (instance) (rate(node_cpu_seconds_total{job='node',mode='idle'}[1m])) * 100)";
    const response = await prom.rangeQuery(q, start, end, step);
    response.result.forEach((serie) => {
      serie.values.forEach((value) => {
        values.push(value.value);
        times.push(value.time);
      });
    });
    return { values, times };
  } catch (error) {
    console.error(error);
  }
  return { values: [], time: [] };
}
export async function getCpuUsage7d() {
  try {
    const start = new Date().getTime() - 60 * 60 * 24 * 7 * 1000;
    const end = new Date();
    const step = 60 * 60 * 24;
    let values = [];
    let times = [];
    const q = "100 - (avg by (instance) (rate(node_cpu_seconds_total{job='node',mode='idle'}[1m])) * 100)";
    const response = await prom.rangeQuery(q, start, end, step);
    response.result.forEach((serie) => {
      serie.values.forEach((value) => {
        values.push(value.value);
        times.push(value.time);
      });
    });
    return { values, times };
  } catch (error) {
    console.error(error);
  }
  return { values: [], time: [] };
}
export async function getCpuUsage30d() {
  try {
    const start = new Date().getTime() - 60 * 60 * 24 * 30 * 1000;
    const end = new Date();
    const step = 60 * 60 * 24;
    let values = [];
    let times = [];
    const q = "100 - (avg by (instance) (rate(node_cpu_seconds_total{job='node',mode='idle'}[1m])) * 100)";
    const response = await prom.rangeQuery(q, start, end, step);
    response.result.forEach((serie) => {
      serie.values.forEach((value) => {
        values.push(value.value);
        times.push(value.time);
      });
    });
    return { values, times };
  } catch (error) {
    console.error(error);
  }
  return { values: [], time: [] };
}
export async function getRamUsage() {
  try {
    let usage = 0;
    const q = "100*((1-(avg_over_time(node_memory_MemFree_bytes{job='node'}[1m])+avg_over_time(node_memory_Cached_bytes{job='node'}[1m])+avg_over_time(node_memory_Buffers_bytes{job='node'}[1m]))/avg_over_time(node_memory_MemTotal_bytes{job='node'}[1m])))";
    const response = await prom.instantQuery(q);
    response.result.forEach((serie) => {
      usage = usage + serie.value.value;
    });
    return usage;
  } catch (error) {
    console.error(error);
  }
  return 0;
}
export async function getRamUsageOverTime(time) {
  switch (time) {
    case "1h":
      return await getRamUsage1h();
    case "24h":
      return await getRamUsage24h();
    case "7d":
      return await getRamUsage7d();
    case "30d":
      return await getRamUsage30d();
  }
}
export async function getRamUsage1h() {
  try {
    const start = new Date().getTime() - 60 * 60 * 1000;
    const end = new Date();
    const step = 10 * 60;
    let values = [];
    let times = [];
    const q = "100*((1-(avg_over_time(node_memory_MemFree_bytes{job='node'}[1m])+avg_over_time(node_memory_Cached_bytes{job='node'}[1m])+avg_over_time(node_memory_Buffers_bytes{job='node'}[1m]))/avg_over_time(node_memory_MemTotal_bytes{job='node'}[1m])))";
    const response = await prom.rangeQuery(q, start, end, step); // wait for the promise to resolve
    response.result.forEach((serie) => {
      serie.values.forEach((value) => {
        values.push(value.value);
        times.push(value.time);
      });
    });
    return { values, times };
  } catch (error) {
    console.error(error);
  }
  return { values: [], time: [] };
}
export async function getRamUsage24h() {
  try {
    const start = new Date().getTime() - 60 * 60 * 24 * 1000;
    const end = new Date();
    const step = 60 * 60;
    let values = [];
    let times = [];
    const q = "100*((1-(avg_over_time(node_memory_MemFree_bytes{job='node'}[1m])+avg_over_time(node_memory_Cached_bytes{job='node'}[1m])+avg_over_time(node_memory_Buffers_bytes{job='node'}[1m]))/avg_over_time(node_memory_MemTotal_bytes{job='node'}[1m])))";
    const response = await prom.rangeQuery(q, start, end, step); // wait for the promise to resolve
    response.result.forEach((serie) => {
      serie.values.forEach((value) => {
        values.push(value.value);
        times.push(value.time);
      });
    });
    return { values, times };
  } catch (error) {
    console.error(error);
  }
  return { values: [], time: [] };
}
export async function getRamUsage7d() {
  try {
    const start = new Date().getTime() - 60 * 60 * 24 * 7 * 1000;
    const end = new Date();
    const step = 60 * 60 * 24;
    let values = [];
    let times = [];
    const q = "100*((1-(avg_over_time(node_memory_MemFree_bytes{job='node'}[1m])+avg_over_time(node_memory_Cached_bytes{job='node'}[1m])+avg_over_time(node_memory_Buffers_bytes{job='node'}[1m]))/avg_over_time(node_memory_MemTotal_bytes{job='node'}[1m])))";
    const response = await prom.rangeQuery(q, start, end, step); // wait for the promise to resolve
    response.result.forEach((serie) => {
      serie.values.forEach((value) => {
        values.push(value.value);
        times.push(value.time);
      });
    });
    return { values, times };
  } catch (error) {
    console.error(error);
  }
  return { values: [], time: [] };
}
export async function getRamUsage30d() {
  try {
    const start = new Date().getTime() - 60 * 60 * 24 * 30 * 1000;
    const end = new Date();
    const step = 60 * 60 * 24;
    let values = [];
    let times = [];
    const q = "100*((1-(avg_over_time(node_memory_MemFree_bytes{job='node'}[1m])+avg_over_time(node_memory_Cached_bytes{job='node'}[1m])+avg_over_time(node_memory_Buffers_bytes{job='node'}[1m]))/avg_over_time(node_memory_MemTotal_bytes{job='node'}[1m])))";
    const response = await prom.rangeQuery(q, start, end, step); // wait for the promise to resolve
    response.result.forEach((serie) => {
      serie.values.forEach((value) => {
        values.push(value.value);
        times.push(value.time);
      });
    });
    return { values, times };
  } catch (error) {
    console.error(error);
  }
  return { values: [], time: [] };
}
export async function getDiskUsage() {
  try {
    let usage = 0;
    const q = "100-((node_filesystem_avail_bytes{mountpoint='/',fstype!='rootfs',job='node'}*100)/node_filesystem_size_bytes{mountpoint='/',fstype!='rootfs',job='node'})";
    const response = await prom.instantQuery(q);
    response.result.forEach((serie) => {
      usage = usage + serie.value.value;
    });
    return usage;
  } catch (error) {
    console.error(error);
  }
  return 0;
}
export async function getDiskUsageOverTime(time) {
  switch (time) {
    case "1h":
      return await getDiskUsage1h();
    case "24h":
      return await getDiskUsage24h();
    case "7d":
      return await getDiskUsage7d();
    case "30d":
      return await getDiskUsage30d();
  }
}
export async function getDiskUsage1h() {
  try {
    const start = new Date().getTime() - 60 * 60 * 1000;
    const end = new Date();
    const step = 10 * 60;
    let values = [];
    let times = [];
    const q = "100-((node_filesystem_avail_bytes{mountpoint='/',fstype!='rootfs',job='node'}*100)/node_filesystem_size_bytes{mountpoint='/',fstype!='rootfs',job='node'})";
    const response = await prom.rangeQuery(q, start, end, step); // wait for the promise to resolve
    response.result.forEach((serie) => {
      serie.values.forEach((value) => {
        values.push(value.value);
        times.push(value.time);
      });
    });
    return { values, times };
  } catch (error) {
    console.error(error);
  }
  return { values: [], time: [] };
}
export async function getDiskUsage24h() {
  try {
    const start = new Date().getTime() - 60 * 60 * 24 * 1000;
    const end = new Date();
    const step = 60 * 60;
    let values = [];
    let times = [];
    const q = "100-((node_filesystem_avail_bytes{mountpoint='/',fstype!='rootfs',job='node'}*100)/node_filesystem_size_bytes{mountpoint='/',fstype!='rootfs',job='node'})";
    const response = await prom.rangeQuery(q, start, end, step); // wait for the promise to resolve
    response.result.forEach((serie) => {
      serie.values.forEach((value) => {
        values.push(value.value);
        times.push(value.time);
      });
    });
    return { values, times };
  } catch (error) {
    console.error(error);
  }
  return { values: [], time: [] };
}
export async function getDiskUsage7d() {
  try {
    const start = new Date().getTime() - 60 * 60 * 24 * 7 * 1000;
    const end = new Date();
    const step = 60 * 60 * 24;
    let values = [];
    let times = [];
    const q = "100-((node_filesystem_avail_bytes{mountpoint='/',fstype!='rootfs',job='node'}*100)/node_filesystem_size_bytes{mountpoint='/',fstype!='rootfs',job='node'})";
    const response = await prom.rangeQuery(q, start, end, step); // wait for the promise to resolve
    response.result.forEach((serie) => {
      serie.values.forEach((value) => {
        values.push(value.value);
        times.push(value.time);
      });
    });
    return { values, times };
  } catch (error) {
    console.error(error);
  }
  return { values: [], time: [] };
}
export async function getDiskUsage30d() {
  try {
    const start = new Date().getTime() - 60 * 60 * 24 * 30 * 1000;
    const end = new Date();
    const step = 60 * 60 * 24;
    let values = [];
    let times = [];
    const q = "100-((node_filesystem_avail_bytes{mountpoint='/',fstype!='rootfs',job='node'}*100)/node_filesystem_size_bytes{mountpoint='/',fstype!='rootfs',job='node'})";
    const response = await prom.rangeQuery(q, start, end, step); // wait for the promise to resolve
    response.result.forEach((serie) => {
      serie.values.forEach((value) => {
        values.push(value.value);
        times.push(value.time);
      });
    });
    return { values, times };
  } catch (error) {
    console.error(error);
  }
  return { values: [], time: [] };
}
export async function getUptime() {
  try {
    let time = 0;
    const q = "node_time_seconds{job='node'} - node_boot_time_seconds{job='node'}";
    const response = await prom.instantQuery(q);
    response.result.forEach((serie) => {
      time = time + serie.value.value;
    });
    return time / 60 / 60 / 24;
  } catch (error) {
    console.error(error);
  }
  return 0;
}

export async function getThreads() {
  try {
    let cores = 0;
    const q = "count without(cpu, mode) (node_cpu_seconds_total{mode='idle'})";
    const response = await prom.instantQuery(q);
    response.result.forEach((serie) => {
      cores = cores + serie.value.value;
    });
    return cores;
  } catch (error) {
    console.error(error);
  }
  return 0;
}
