const endpoint = "http://localhost:9090/api/v1";

export async function getCpuUsage() {
  try {
    const response = await fetch(
      endpoint + "/query?query=rate(process_cpu_seconds_total[30s])"
    );
    const data = await response.json();
    return data.data.result[0].value[1] * 100;
  } catch (error) {
    console.error(error);
  }
}

export async function getThreads() {
  try {
    const response = await fetch(endpoint + "/query?query=go_threads");
    const data = await response.json();
    return data.data.result[0].value[1];
  } catch (error) {
    console.error(error);
  }
}
