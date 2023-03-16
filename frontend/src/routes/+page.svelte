<script>
  import AreaChart from "../components/charts/AreaChart.svelte";
  import ColumnChart from "../components/charts/ColumnChart.svelte";
  import CircleChart from "../components/charts/CircleChart.svelte";
  import ToggleBox from "../components/toggle-box/ToggleBox.svelte";
  import {
    chart1,
    chart2,
    chart3,
    chart4,
    chart5,
    chart6,
    semiCircle,
  } from "../mock-data/data";
  import MultiItemsgrid from "../components/grids/MultiItemsgrid.svelte";
  import BaseGridItem from "../components/grids/BaseGridItem.svelte";
  import TwoItemsGrid from "../components/grids/TwoItemsGrid.svelte";
  import AreaChartWithLabels from "../components/charts/AreaChartWithLabels.svelte";
</script>

<div>
  <ToggleBox title={"Quick overview"}>
    <MultiItemsgrid>
      <BaseGridItem
        title="CPU usage"
        text={chart1.data[0].data[chart1.data[0].data.length - 1]}
      >
        <AreaChart data={chart1.data} id={chart1.name} />
      </BaseGridItem>

      <BaseGridItem
        title="RAM usage"
        text={chart2.data[0].data[chart2.data[0].data.length - 1]}
      >
        <AreaChart data={chart2.data} id={chart2.name} />
      </BaseGridItem>
      <BaseGridItem
        title="DISK usage"
        text={chart3.data[0].data[chart3.data[0].data.length - 1]}
      >
        <AreaChart data={chart3.data} id={chart3.name} />
      </BaseGridItem>
      <BaseGridItem title="CPU total" text="30%">
        <div
          class="flex h-5/6 items-end justify-center absolute top-8 left-0 right-0"
        >
          <CircleChart
            data={[30]}
            id="id8"
            colors={["#881337"]}
            warningPoint={50}
            criticalPoint={70}
          />
        </div>
      </BaseGridItem>
      <BaseGridItem title="RAM total" text="70%">
        <div
          class="flex h-5/6 items-end justify-center absolute top-8 left-0 right-0"
        >
          <CircleChart data={[70]} id="id9" colors={["#881337"]} />
        </div>
      </BaseGridItem>
      <BaseGridItem title="DISK total" text="90%">
        <div
          class="flex h-5/6 items-end justify-center absolute top-8 left-0 right-0"
        >
          <CircleChart data={[90]} id="id10" colors={["#881337"]} />
        </div>
      </BaseGridItem>
      <BaseGridItem title="Threads" text="14" />
      <BaseGridItem title="Uptime" text="4 days" />
    </MultiItemsgrid>
  </ToggleBox>

  <div class="mt-8" />

  <ToggleBox title={"CPU usage"} columns={2}>
    <TwoItemsGrid>
      <BaseGridItem title="CPU usage" height={"h-96"}>
        <AreaChartWithLabels data={chart4.data} id={chart4.name} />
      </BaseGridItem>
      <BaseGridItem title="CPU per process" height={"h-96"}>
        <AreaChartWithLabels data={chart5.data} id={chart5.name} />
      </BaseGridItem>
    </TwoItemsGrid>
  </ToggleBox>
</div>
