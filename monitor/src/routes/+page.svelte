<script>
  import { COLOR } from "../helpers/colors";
  import CircleChart from "../components/charts/CircleChart.svelte";
  import ToggleBox from "../components/toggle-box/ToggleBox.svelte";
  import {
    badRequestsChart,
    requestsChart,
    SuccessfulRequestsChart,
  } from "../mock-data/data";
  import TitleAndTextContainer from "../components/containers/TitleAndTextContainer.svelte";
  import Grid from "../components/grids/Grid.svelte";
  import ColumnChart from "../components/charts/ColumnChart.svelte";
  import {
    cpuUsageOverTime,
    ramUsageOverTime,
    diskUsageOverTime,
    cpuUsage,
    ramUsage,
    diskUsage,
    threads,
  } from "../stores/data";
  import { onMount, onDestroy } from "svelte";
  import {
    getCpuUsage,
    getRamUsage,
    getDiskUsage,
    getRamUsageOverTime,
    getCpuUsageOverTime,
    getDiskUsageOverTime,
    getThreads,
  } from "../api/prometheus";
  import TimeIntervalChart from "../components/charts/TimeIntervalChart.svelte";

  export let data;

  $cpuUsage = data.prometheus.cpuUsage;
  $ramUsage = data.prometheus.ramUsage;
  $diskUsage = data.prometheus.diskUsage;
  $cpuUsageOverTime = data.prometheus.cpuUsageOverTime;
  $ramUsageOverTime = data.prometheus.ramUsageOverTime;
  $diskUsageOverTime = data.prometheus.diskUsageOverTime;
  $threads = data.prometheus.threads;

  let fiveSecondInterval;
  let oneSecondInterval;
  let ramUsageOverTimeInterval = "1h";
  let cpuUsageOverTimeInterval = "1h";
  let diskUsageOverTimeInterval = "1h";

  onMount(() => {
    fiveSecondInterval = setInterval(() => {
      setRamUsageOverTime();
      setCpuUsageOverTime();
      setDiskUsageOverTime();
    }, 5000);
    oneSecondInterval = setInterval(() => {
      setCpuUsage();
      setRamUsage();
      setDiskUsage();
      setThreads();
    }, 1000);
  });

  onDestroy(() => {
    clearInterval(oneSecondInterval);
    clearInterval(fiveSecondInterval);
  });

  async function setCpuUsage() {
    $cpuUsage = await getCpuUsage();
  }
  async function setRamUsage() {
    $ramUsage = await getRamUsage();
  }
  async function setDiskUsage() {
    $diskUsage = await getDiskUsage();
  }
  async function setCpuUsageOverTime(event) {
    if (event) cpuUsageOverTimeInterval = event.detail.time;
    $cpuUsageOverTime = await getCpuUsageOverTime(cpuUsageOverTimeInterval);
  }
  async function setRamUsageOverTime(event) {
    if (event) ramUsageOverTimeInterval = event.detail.time;
    $ramUsageOverTime = await getRamUsageOverTime(ramUsageOverTimeInterval);
  }
  async function setDiskUsageOverTime(event) {
    if (event) diskUsageOverTimeInterval = event.detail.time;
    $diskUsageOverTime = await getDiskUsageOverTime(diskUsageOverTimeInterval);
  }
  async function setThreads() {
    $threads = await getThreads();
  }
</script>

<ToggleBox title={"Quick overview"}>
  <Grid columns={5}>
    <TitleAndTextContainer title="Threads" text={$threads.toString()} />
    <TitleAndTextContainer
      title="Uptime"
      text={data.prometheus.uptime.toFixed(0) + " days"}
    />
    <TitleAndTextContainer title="CPU usage" text={$cpuUsage.toFixed(0) + "%"}>
      <div
        class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
      >
        <CircleChart data={[$cpuUsage]} id="cpuUsage" />
      </div>
    </TitleAndTextContainer>
    <TitleAndTextContainer title="RAM usage" text={$ramUsage.toFixed(0) + "%"}>
      <div
        class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
      >
        <CircleChart data={[$ramUsage]} id="ramUsage" />
      </div>
    </TitleAndTextContainer>
    <TitleAndTextContainer
      title="DISK usage"
      text={$diskUsage.toFixed(0) + "%"}
    >
      <div
        class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
      >
        <CircleChart data={[$diskUsage]} id="diskUsage" />
      </div>
    </TitleAndTextContainer>
  </Grid>
</ToggleBox>

<div class="mt-8" />

<ToggleBox title={"History"}>
  <Grid columns={3} gridClass="md-grid">
    <TimeIntervalChart
      data={[{ data: $cpuUsageOverTime.values, name: "CPU" }]}
      labels={$cpuUsageOverTime.times}
      id="cpuUsageOverTime"
      title="CPU Usage"
      color={COLOR.BLUE}
      on:updateTimeInterval={setCpuUsageOverTime}
    />
    <TimeIntervalChart
      data={[{ data: $ramUsageOverTime.values, name: "RAM" }]}
      labels={$ramUsageOverTime.times}
      id="ramUsageOverTime"
      title="RAM Usage"
      color={COLOR.VIOLET}
      on:updateTimeInterval={setRamUsageOverTime}
    />
    <TimeIntervalChart
      data={[{ data: $diskUsageOverTime.values, name: "DISK" }]}
      labels={$diskUsageOverTime.times}
      id="diskUsageOverTime"
      title="DISK Usage"
      color={COLOR.FUSCHIA}
      on:updateTimeInterval={setDiskUsageOverTime}
    />
  </Grid>
</ToggleBox>

<div class="mt-8" />

<ToggleBox title={"Requests"}>
  <Grid columns={3} gridClass="sm-grid">
    <TitleAndTextContainer title="Total requests" text="19000">
      <ColumnChart
        data={requestsChart.data}
        id={requestsChart.name}
        tooltipEnabled={true}
      />
    </TitleAndTextContainer>
    <TitleAndTextContainer title="Bad requests" text="3000">
      <ColumnChart
        data={badRequestsChart.data}
        id={badRequestsChart.name}
        tooltipEnabled={true}
        colors={[COLOR.ORANGE]}
      />
    </TitleAndTextContainer>
    <TitleAndTextContainer title="Successful requests" text="16000">
      <ColumnChart
        data={SuccessfulRequestsChart.data}
        id={SuccessfulRequestsChart.name}
        tooltipEnabled={true}
        colors={[COLOR.GREEN]}
      />
    </TitleAndTextContainer>
  </Grid>
</ToggleBox>
