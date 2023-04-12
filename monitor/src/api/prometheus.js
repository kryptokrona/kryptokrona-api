const endpoint = "http://localhost:9090/api/v1";

export async function getCpuUsage() {
  try {
    const response = await fetch(
      endpoint + "/query?query=rate(node_cpu_seconds_total{mode!='idle'}[30s])"
    );
    const data = await response.json();
    return data.data.result[0].value[1] * 100;
  } catch (error) {
    console.error(error);
  }
}
export async function getramUsage() {
  try {
    const response = await fetch(
      endpoint + "/query?query=10*(avg_over_time(node_memory_MemTotal_bytes[1m]))/(avg_over_time(node_memory_MemFree_bytes[1m])+avg_over_time(node_memory_Cached_bytes[1m])+avg_over_time(node_memory_Buffers_bytes[1m]))"
    );
    const data = await response.json();
    console.log(data)
    return data.data.result[0].value[1] * 100;
  } catch (error) {
    console.error(error);
  }
}
export async function getUptime() {
  try {
    const response = await fetch(
      endpoint + "/query?query=node_time_seconds-node_boot_time_seconds"
    );
    const data = await response.json();
    console.log(data)
    return data.data.result[0].value[1] * 100;
  } catch (error) {
    console.error(error);
  }
}

export async function getThreads() {
  try {
    const response = await fetch(endpoint + "/query?query=go_threads");
    const data = await response.json();
    console.log(data.data.result[0].value[1])
    return data.data.result[0].value[1];
  } catch (error) {
    console.error(error);
  }
}
