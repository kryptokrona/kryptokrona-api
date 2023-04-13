import {PrometheusDriver} from 'prometheus-query';
import dotenv from "dotenv";

dotenv.config();

const prom = new PrometheusDriver({
    endpoint: `${process.env.PROMETHEUS_URL}`,
    baseURL: "/api/v1" // default value
});

console.log("PROMETHEUS DRIVER: ", prom)

export async function getCpuUsage() {
  try {
    var usage = 0;
    const q = "100 - (avg by (instance) (rate(node_cpu_seconds_total{job='node',mode='idle'}[1m])) * 100)";
    prom.instantQuery(q)
    .then((res) => {
        const series = res.result;
        series.forEach((serie) => {
            usage = usage + serie.value.value
        });
        return usage;
    })
  } catch (error) {
    console.error(error);
  }
}
export async function getCpuUsage1h() {
  try {
    const start = new Date().getTime() - 60 * 60 * 1000;
    const end = new Date();
    const step = 10 * 60
    var values = []
    var times = []
    var usage = 0;
    const q = "100 - (avg by (instance) (rate(node_cpu_seconds_total{job='node',mode='idle'}[1m])) * 100)";
    const res = await prom.rangeQuery(q, start, end, step); // wait for the promise to resolve
    const series = res.result;
    series.forEach((serie) => {
      serie.values.forEach((value) => {
        values.push(value.value)
        times.push(value.time)
      })
    });
    var object = { values, times }
    return object; // return the object from the function
  } catch (error) {
    console.error(error);
  }
}

export async function getCpuUsage24h() {
  try {
    const start = new Date().getTime() - 60 * 60 * 24 * 1000;
    const end = new Date();
    const step = 60 * 60
    var values = []
    var times = []
    const q = "100 - (avg by (instance) (rate(node_cpu_seconds_total{job='node',mode='idle'}[1m])) * 100)";
    prom.rangeQuery(q, start, end, step)
    .then((res) => {
        const series = res.result;
        var i = 0;
        series.forEach((serie) => {
         serie.values.forEach((value) => {
          values.push(value.value)
          times.push(value.time)
         })
        });
      
      var object = { values, times }
      return object
    })
  } catch (error) {
    console.error(error);
  }
}
export async function getCpuUsage7d() {
  try {
    const start = new Date().getTime() - 60 * 60 * 24 * 7 * 1000;
    const end = new Date();
    const step = 60 * 60 * 24
    var values = []
    var times = []
    const q = "100 - (avg by (instance) (rate(node_cpu_seconds_total{job='node',mode='idle'}[1m])) * 100)";
    prom.rangeQuery(q, start, end, step)
    .then((res) => {
        const series = res.result;
        var i = 0;
        series.forEach((serie) => {
         serie.values.forEach((value) => {
          values.push(value.value)
          times.push(value.time)
         })
        });
      
      var object = { values, times }
      return object
    })
  } catch (error) {
    console.error(error);
  }
}
export async function getCpuUsage30d() {
  try {
    const start = new Date().getTime() - 60 * 60 * 24 * 30 * 1000;
    const end = new Date();
    const step = 60 * 60 * 24 
    var values = []
    var times = []
    const q = "100 - (avg by (instance) (rate(node_cpu_seconds_total{job='node',mode='idle'}[1m])) * 100)";
    prom.rangeQuery(q, start, end, step)
    .then((res) => {
        const series = res.result;
        var i = 0;
        series.forEach((serie) => {
         serie.values.forEach((value) => {
          values.push(value.value)
          times.push(value.time)
         })
        });
      
      var object = { values, times }
      return object
    })
  } catch (error) {
    console.error(error);
  }
}
export async function getramUsage() {
  try {
    var usage = 0;
    const q = "100*((1-(avg_over_time(node_memory_MemFree_bytes{job='node'}[1m])+avg_over_time(node_memory_Cached_bytes{job='node'}[1m])+avg_over_time(node_memory_Buffers_bytes{job='node'}[1m]))/avg_over_time(node_memory_MemTotal_bytes{job='node'}[1m])))";
    prom.instantQuery(q)
    .then((res) => {
        const series = res.result;
        series.forEach((serie) => {
            usage = usage + serie.value.value
        });
        return usage * 100;
    })
  } catch (error) {
    console.error(error);
  }
} 
export async function getUptime() {
  try {
    var time = 0;
    const q = "node_time_seconds{job='node'} - node_boot_time_seconds{job='node'}";
    prom.instantQuery(q)
    .then((res) => {
        const series = res.result;
        series.forEach((serie) => {
            time = time + serie.value.value
        });
        return time / 60 / 60 / 24;
    })
  } catch (error) {
    console.error(error);
  }
}

export async function getThreads() {
  try {
    var cores = 0;
    const q = "count without(cpu, mode) (node_cpu_seconds_total{mode='idle'})";
    prom.instantQuery(q)
    .then((res) => {
        const series = res.result;
        series.forEach((serie) => {
            cores = cores + serie.value.value
        });
        return cores
    })
  } catch (error) {
    console.error(error);
  }
}
