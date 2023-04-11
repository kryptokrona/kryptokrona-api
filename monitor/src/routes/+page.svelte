<script>
  import { COLOR } from "../helpers/colors";
  import CircleChart from "../components/charts/CircleChart.svelte";
  import ToggleBox from "../components/toggle-box/ToggleBox.svelte";
  import { chart1, chart4, chart5 } from "../mock-data/data";
  import TitleAndTextContainer from "../components/containers/TitleAndTextContainer.svelte";
  import AreaChartWithLabels from "../components/charts/AreaChartWithLabels.svelte";
  import ChartWithTimeButtonsContainer from "../components/containers/ChartWithTimeButtonsContainer.svelte";
  import TitleAndTextContainerGreen from "../components/containers/TitleAndTextContainerGreen.svelte";
  import Grid from "../components/grids/Grid.svelte";
</script>

<div id="main" />
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
    <ChartWithTimeButtonsContainer title="CPU usage" height={"h-80"}>
      <AreaChartWithLabels data={chart4.data} id={chart4.name} />
    </ChartWithTimeButtonsContainer>

    <ChartWithTimeButtonsContainer title="RAM usage" height={"h-80"}>
      <AreaChartWithLabels
        data={chart5.data}
        id={chart5.name}
        colors={[COLOR.VIOLET]}
      />
    </ChartWithTimeButtonsContainer>
    <ChartWithTimeButtonsContainer title="DISK usage" height={"h-80"}>
      <AreaChartWithLabels
        data={chart1.data}
        id={chart1.name}
        colors={[COLOR.FUSCHIA]}
      />
    </ChartWithTimeButtonsContainer>
  </Grid>
</ToggleBox>

<div class="mt-8" />

<ToggleBox title={"Status"}>
  <Grid columns={6}>
    <TitleAndTextContainerGreen title="Something" text="OK" />
    <TitleAndTextContainerGreen title="Something" text="OK" />
    <TitleAndTextContainerGreen title="Something" text="OK" />
    <TitleAndTextContainer title="Something" text="OFFLINE" />
    <TitleAndTextContainer title="Something" text="OFFLINE" />
    <TitleAndTextContainerGreen title="Something" text="OK" />
  </Grid>
</ToggleBox>

<div class="mt-8" />
