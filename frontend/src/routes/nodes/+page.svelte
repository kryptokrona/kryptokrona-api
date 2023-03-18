<script>
  import { onMount } from "svelte";
  import AreaChart from "../../components/charts/AreaChart.svelte";
  import ColumnChart from "../../components/charts/ColumnChart.svelte";
  import AreaChartWithLabels from "../../components/charts/AreaChartWithLabels.svelte";
  import TwoItemsGrid from "../../components/grids/TwoItemsGrid.svelte";
  import TitleAndTextContainer from "../../components/containers/TitleAndTextContainer.svelte";
  import ToggleBox from "../../components/toggle-box/ToggleBox.svelte";
  import {
    chart1,
    chart2,
    chart3,
    chart4,
    chart5,
    nodes,
    chart7,
    chart8,
    months,
  } from "../../mock-data/data";
  import { goto } from "$app/navigation";
  import LineChartWithLabels from "../../components/charts/LineChartWithLabels.svelte";
  import MultiItemsGrid from "../../components/grids/MultiItemsGrid.svelte";
  import TitleAndTextContainerGreen from "../../components/containers/TitleAndTextContainerGreen.svelte";

  let searchInput = "";
</script>

<div class="flex items-center justify-center">
  <div class="w-full">
    <div class="relative dark:text-neutral-900">
      <i class="fa-solid fa-magnifying-glass search-icon " />
      <input
        bind:value={searchInput}
        class="search-input rounded-md w-full border-neutral-700 border dark:border-none"
        type="text"
        aria-label="search for node"
        placeholder="search"
      />
    </div>
    <div
      class="rounded-md border-t border-l border-r mt-4 dark:border-neutral-100 border-neutral-700"
    >
      <div
        class="flex flex-row border-b p-2 dark:border-neutral-100 border-neutral-700"
      >
        <h2 class="text-left sm:text-center w-1/2 sm:w-1/3 lg:w-1/5">Name</h2>
        <h2 class="text-right sm:text-center w-1/2 sm:w-1/3 lg:w-1/5">Url</h2>
        <h2 class="hidden text-center sm:inline-block sm:w-1/3 lg:w-1/5">
          Port
        </h2>
        <h2 class="hidden text-center lg:inline-block lg:w-1/5">Data</h2>
        <h2 class="hidden text-center lg:inline-block lg:w-1/5">Data</h2>
      </div>
      {#each nodes.filter((n) => n.name
          .toLowerCase()
          .includes(searchInput.toLowerCase())) as node, i}
        <button
          on:click={goto(`/nodes/${node.name}`)}
          class={(i == nodes.length - 1 ? "rounded-md " : "") +
            "flex text-left w-full border-b p-2 dark:border-neutral-100 border-neutral-700 lg:hover:cursor-pointer lg:hover:bg-neutral-200 dark:lg:hover:bg-neutral-800"}
        >
          <p class=" text-left sm:text-center w-1/2 sm:w-1/3 lg:w-1/5">
            {node.name}
          </p>
          <p class="text-right sm:text-center w-1/2 sm:w-1/3 lg:w-1/5">
            {node.url}
          </p>
          <p class="hidden text-center sm:inline-block sm:w-1/3 lg:w-1/5">
            {node.port}
          </p>
          <p class="hidden text-center lg:inline-block lg:w-1/5">data</p>
          <p class="hidden text-center lg:inline-block lg:w-1/5">data</p>
        </button>
      {/each}
    </div>
  </div>
</div>

<div class="mt-8" />

<TwoItemsGrid>
  <div class="w-full h-80">
    <AreaChartWithLabels data={chart7.data} labels={months} id={chart7.name} />
  </div>

  <div class="w-full h-80">
    <LineChartWithLabels data={chart8.data} labels={months} id={chart8.name} />
  </div>
</TwoItemsGrid>

<div class="mt-8" />

<ToggleBox title={"Data"}>
  <MultiItemsGrid columns={5}>
    <TitleAndTextContainer
      title="TITLE"
      text={chart1.data[0].data[chart1.data[0].data.length - 1]}
    >
      <AreaChart data={chart1.data} id={chart1.name} />
    </TitleAndTextContainer>
    <TitleAndTextContainer
      title="TITLE"
      text={chart2.data[0].data[chart2.data[0].data.length - 1]}
    >
      <AreaChart data={chart2.data} id={chart2.name} colors={["#4c1d95"]} />
    </TitleAndTextContainer>
    <TitleAndTextContainer
      title="TITLE"
      text={chart3.data[0].data[chart3.data[0].data.length - 1]}
    >
      <AreaChart data={chart3.data} id={chart3.name} colors={["#701a75"]} />
    </TitleAndTextContainer>
    <TitleAndTextContainer
      title="TITLE"
      text={chart4.data[0].data[chart4.data[0].data.length - 1]}
    >
      <ColumnChart data={chart4.data} id={chart4.name} colors={["#881337"]} />
    </TitleAndTextContainer>
    <TitleAndTextContainer
      title="TITLE"
      text={chart5.data[0].data[chart5.data[0].data.length - 1]}
    >
      <ColumnChart
        data={chart5.data}
        id={chart5.name}
        colors={["#4c1d95", "#701a75"]}
        multipleDataSets={true}
      />
    </TitleAndTextContainer>
  </MultiItemsGrid>
</ToggleBox>

<div class="mt-8" />

<ToggleBox title={"Data"}>
  <MultiItemsGrid columns={4}>
    <TitleAndTextContainer title={"TITLE"} text="VALUE" />
    <TitleAndTextContainer title={"TITLE"} text="VALUE" />
    <TitleAndTextContainerGreen title={"TITLE"} text="VALUE" />
    <TitleAndTextContainerGreen title={"TITLE"} text="VALUE" />
  </MultiItemsGrid>
</ToggleBox>

<div class="mt-8" />

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
