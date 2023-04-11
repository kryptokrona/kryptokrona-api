<script>
  import { COLOR } from "../helpers/colors";
  import CircleChart from "../components/charts/CircleChart.svelte";
  import ToggleBox from "../components/toggle-box/ToggleBox.svelte";
  import {
    chart1,
    chart4,
    chart5,
    requestsChart,
    SuccessfulRequestsChart,
    badRequestsChart,
  } from "../mock-data/data";
  import TitleAndTextContainer from "../components/containers/TitleAndTextContainer.svelte";
  import ChartWithTimeButtonsContainer from "../components/containers/ChartWithTimeButtonsContainer.svelte";
  import Grid from "../components/grids/Grid.svelte";
  import ColumnChart from "../components/charts/ColumnChart.svelte";
  import AreaChart from "../components/charts/AreaChart.svelte";
</script>

<ToggleBox title={"Quick overview"}>
  <Grid columns={5}>
    <TitleAndTextContainer title="Threads" text="14" />
    <TitleAndTextContainer title="Uptime" text="4 days" />
    <TitleAndTextContainer title="CPU usage" text="30%">
      <div
        class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
      >
        <CircleChart data={[30]} id="id8" />
      </div>
    </TitleAndTextContainer>
    <TitleAndTextContainer title="RAM usage" text="50%">
      <div
        class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
      >
        <CircleChart data={[50]} id="id9" />
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
    <ChartWithTimeButtonsContainer title="CPU usage">
      <AreaChart data={chart4.data} id={chart4.name} tooltipEnabled={true} />
    </ChartWithTimeButtonsContainer>

    <ChartWithTimeButtonsContainer title="RAM usage">
      <AreaChart
        data={chart5.data}
        id={chart5.name}
        tooltipEnabled={true}
        colors={[COLOR.FUSCHIA]}
      />
    </ChartWithTimeButtonsContainer>
    <ChartWithTimeButtonsContainer title="DISK usage">
      <AreaChart
        data={chart1.data}
        id={chart1.name}
        tooltipEnabled={true}
        colors={[COLOR.VIOLET]}
      />
    </ChartWithTimeButtonsContainer>
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
