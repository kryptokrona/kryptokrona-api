<script>
  import { COLOR } from "../helpers/colors";
  import CircleChart from "../components/charts/CircleChart.svelte";
  import ToggleBox from "../components/toggle-box/ToggleBox.svelte";
  import {
    badRequestsChart,
    chart1,
    chart5,
    requestsChart,
    SuccessfulRequestsChart,
  } from "../mock-data/data";
  import TitleAndTextContainer from "../components/containers/TitleAndTextContainer.svelte";
  import Grid from "../components/grids/Grid.svelte";
  import ColumnChart from "../components/charts/ColumnChart.svelte";
  import AreaChart from "../components/charts/AreaChart.svelte";
  import GrayButton from "../components/buttons/GrayButton.svelte";
  import { cpuUsageOverTime } from "../stores/data";
  import CpuUsageOverTimeChart from "../components/charts/CpuUsageOverTimeChart.svelte";

  export let data;

  $cpuUsageOverTime = data.prometheus.cpuUsageOverTime;
</script>

<ToggleBox title={"Quick overview"}>
  <Grid columns={5}>
    <TitleAndTextContainer
      title="Threads"
      text={data.prometheus.threads.toString()}
    />
    <TitleAndTextContainer
      title="Uptime"
      text={data.prometheus.uptime.toFixed(0) + " days"}
    />
    <TitleAndTextContainer
      title="CPU usage"
      text={data.prometheus.cpuUsage.toFixed(0) + "%"}
    >
      <div
        class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
      >
        <CircleChart data={[data.prometheus.cpuUsage]} id="cpuUsage" />
      </div>
    </TitleAndTextContainer>
    <TitleAndTextContainer
      title="RAM usage"
      text={data.prometheus.ramUsage.toFixed(0) + "%"}
    >
      <div
        class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
      >
        <CircleChart data={[data.prometheus.ramUsage]} id="ramUsage" />
      </div>
    </TitleAndTextContainer>
    <TitleAndTextContainer title="DISK usage" text="40%">
      <div
        class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
      >
        <CircleChart data={[40]} id="id10" />
      </div>
    </TitleAndTextContainer>
  </Grid>
</ToggleBox>

<div class="mt-8" />

<ToggleBox title={"History"}>
  <Grid columns={3} gridClass="md-grid">
    <CpuUsageOverTimeChart />

    <div
      class={"bg-neutral-200 dark:bg-neutral-800 w-full  rounded-md relative h-64"}
    >
      <div class="h-2/5 pt-1 text-center">
        <p class="pb-1">RAM usage</p>
        <GrayButton text="1h" />
        <GrayButton text="24h" />
        <GrayButton text="7d" />
        <GrayButton text="30d" />
      </div>
      <div class="h-3/5">
        <AreaChart
          data={chart5.data}
          id={chart5.name}
          tooltipEnabled={true}
          colors={[COLOR.FUSCHIA]}
        />
      </div>
    </div>

    <div
      class={"bg-neutral-200 dark:bg-neutral-800 w-full  rounded-md relative h-64"}
    >
      <div class="h-2/5 pt-1 text-center">
        <p class="pb-1">DISK usage</p>
        <GrayButton text="1h" />
        <GrayButton text="24h" />
        <GrayButton text="7d" />
        <GrayButton text="30d" />
      </div>
      <div class="h-3/5">
        <AreaChart
          data={chart1.data}
          id={chart1.name}
          tooltipEnabled={true}
          colors={[COLOR.VIOLET]}
        />
      </div>
    </div>
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
