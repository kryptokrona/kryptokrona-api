<script>
  import { onMount } from "svelte";
  import AreaChart from "../../components/charts/AreaChart.svelte";
  import ColumnChart from "../../components/charts/ColumnChart.svelte";
  import AreaChartWithLabels from "../../components/charts/AreaChartWithLabels.svelte";
  import ColumnChartWithLabels from "../../components/charts/ColumnChartWithLabels.svelte";
  import TwoItemsGrid from "../../components/grids/TwoItemsGrid.svelte";
  import InfoBox from "../../components/toggle-box/InfoBox.svelte";
  import ToggleBox from "../../components/toggle-box/ToggleBox.svelte";
  import {
    chart1,
    chart2,
    chart3,
    chart4,
    chart5,
    chart6,
    nodes,
    chart7,
    chart8,
    months,
  } from "../../mock-data/data";

  let searchInput = "";
</script>

<div class="flex items-center justify-center">
  <div class="w-min">
    <div class="relative text-neutral-900 ">
      <i class="fa-solid fa-magnifying-glass search-icon" />
      <input
        bind:value={searchInput}
        class="search-input rounded-md w-full text-neutral-900 border-neutral-900 border dark:border-none"
        type="text"
        aria-label="search for node"
        placeholder="search"
      />
    </div>
    <div
      class="rounded-md border-t border-l border-r mt-4 dark:border-neutral-100 border-neutral-900"
    >
      <div
        class="flex flex-row border-b p-2 dark:border-neutral-100 border-neutral-900"
      >
        <h2 class="w-72">Name</h2>
        <h2 class="w-72">Url</h2>
        <h2>Port</h2>
      </div>
      {#each nodes.filter((n) => n.name
          .toLowerCase()
          .includes(searchInput.toLowerCase())) as node, i}
        <div
          class={(i == nodes.length - 1 ? "rounded-md " : "") +
            "flex  border-b p-2 dark:border-neutral-100 border-neutral-900 hover:cursor-pointer hover:bg-neutral-200 dark:hover:bg-neutral-800"}
        >
          <p class="w-72">{node.name}</p>
          <p class="w-72">{node.url}</p>
          <p>{node.port}</p>
          <p />
        </div>
      {/each}
    </div>
  </div>
</div>

<div class="mt-28" />

<TwoItemsGrid>
  <div>
    <h2 class="mb-4">Data</h2>
    <div class="xl:h-72 h-48">
      <AreaChartWithLabels
        data={chart7.data}
        labels={months}
        id={chart7.name}
      />
    </div>
  </div>
  <div>
    <h2 class="mb-4">Data</h2>
    <div class="xl:h-72 h-48">
      <ColumnChartWithLabels
        data={chart8.data}
        labels={months}
        id={chart8.name}
      />
    </div>
  </div>
</TwoItemsGrid>

<div class="mt-28" />

<ToggleBox title={"Data"}>
  <InfoBox
    title="TITLE"
    text={chart1.data[0].data[chart1.data[0].data.length - 1]}
    width={"xl:w-60"}
  >
    <AreaChart data={chart1.data} id={chart1.name} />
  </InfoBox>
  <InfoBox
    title="TITLE"
    text={chart2.data[0].data[chart2.data[0].data.length - 1]}
    width={"xl:w-60"}
  >
    <AreaChart data={chart2.data} id={chart2.name} colors={["#4c1d95"]} />
  </InfoBox>
  <InfoBox
    title="TITLE"
    text={chart3.data[0].data[chart3.data[0].data.length - 1]}
    width={"xl:w-60"}
  >
    <AreaChart data={chart3.data} id={chart3.name} colors={["#701a75"]} />
  </InfoBox>
  <InfoBox
    title="TITLE"
    text={chart4.data[0].data[chart4.data[0].data.length - 1]}
    width={"xl:w-60"}
  >
    <ColumnChart data={chart4.data} id={chart4.name} colors={["#881337"]} />
  </InfoBox>
  <InfoBox
    title="TITLE"
    text={chart5.data[0].data[chart5.data[0].data.length - 1]}
    width={"xl:w-96"}
  >
    <ColumnChart
      data={chart5.data}
      id={chart5.name}
      colors={["#4c1d95", "#701a75"]}
      multipleDataSets={true}
    />
  </InfoBox>
</ToggleBox>

<style>
  .search-icon {
    display: flex;
    align-items: center;
    position: absolute;
    top: 0;
    bottom: 0;
    margin: auto;
    padding-left: 0.5rem;
  }
  .search-input {
    padding-left: 2rem;
    padding-bottom: 0.5rem;
    padding-top: 0.5rem;
  }
</style>
