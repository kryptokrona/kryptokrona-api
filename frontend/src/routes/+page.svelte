<script>
  import CircleChart from "../components/charts/CircleChart.svelte";
  import ToggleBox from "../components/toggle-box/ToggleBox.svelte";
  import { chart1, chart4, chart5 } from "../mock-data/data";
  import MultiItemsgrid from "../components/grids/MultiItemsgrid.svelte";
  import TitleAndTextContainer from "../components/containers/TitleAndTextContainer.svelte";
  import TwoItemsGrid from "../components/grids/TwoItemsGrid.svelte";
  import AreaChartWithLabels from "../components/charts/AreaChartWithLabels.svelte";
  import ChartWithTimeButtonsContainer from "../components/containers/ChartWithTimeButtonsContainer.svelte";
  import MultiItemsGridFullWidthTablet from "../components/grids/MultiItemsGridFullWidthTablet.svelte";
</script>

<div>
  <ToggleBox title={"Quick overview"}>
    <MultiItemsgrid columns={5}>
      <TitleAndTextContainer title="Threads" text="14" />
      <TitleAndTextContainer title="Uptime" text="4 days" />
      <TitleAndTextContainer title="CPU total" text="30%">
        <div
          class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
        >
          <CircleChart data={[30]} id="id8" />
        </div>
      </TitleAndTextContainer>
      <TitleAndTextContainer title="RAM total" text="50%">
        <div
          class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
        >
          <CircleChart data={[50]} id="id9" />
        </div>
      </TitleAndTextContainer>
      <TitleAndTextContainer title="DISK total" text="40%">
        <div
          class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
        >
          <CircleChart data={[40]} id="id10" />
        </div>
      </TitleAndTextContainer>
    </MultiItemsgrid>
  </ToggleBox>

  <div class="mt-8" />

  <ToggleBox title={"Quick overview"}>
    <MultiItemsGridFullWidthTablet columns={3} fullWidthOnPhone={true}>
      <ChartWithTimeButtonsContainer title="CPU usage" height={"h-80"}>
        <AreaChartWithLabels data={chart4.data} id={chart4.name} />
      </ChartWithTimeButtonsContainer>

      <ChartWithTimeButtonsContainer title="RAM usage" height={"h-80"}>
        <AreaChartWithLabels
          data={chart5.data}
          id={chart5.name}
          colors={["#4c1d95"]}
        />
      </ChartWithTimeButtonsContainer>
      <ChartWithTimeButtonsContainer title="DISK usage" height={"h-80"}>
        <AreaChartWithLabels
          data={chart1.data}
          id={chart1.name}
          colors={["#701a75"]}
        />
      </ChartWithTimeButtonsContainer>
    </MultiItemsGridFullWidthTablet>
  </ToggleBox>
</div>
