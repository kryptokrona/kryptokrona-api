<script>
    import {COLOR} from "../../helpers/colors";
    import AreaChart from "../../components/charts/AreaChart.svelte";
    import ColumnChart from "../../components/charts/ColumnChart.svelte";
    import AreaChartWithLabels from "../../components/charts/AreaChartWithLabels.svelte";
    import TitleAndTextContainer from "../../components/containers/TitleAndTextContainer.svelte";
    import ToggleBox from "../../components/toggle-box/ToggleBox.svelte";
    import {base} from "$app/paths";
    import {chart1, chart2, chart3, chart4, chart5, chart7, chart8, months,} from "../../mock-data/data";
    import {goto} from "$app/navigation";
    import LineChartWithLabels from "../../components/charts/LineChartWithLabels.svelte";
    import TitleAndTextContainerGreen from "../../components/containers/TitleAndTextContainerGreen.svelte";
    import Grid from "../../components/grids/Grid.svelte";
    import {nodes} from "../../stores/data";
    import {onMount} from "svelte";

    export let data;

  let page = 0;
  let rowsPerPage = 5;
  let sliceIndex = 5;

  $nodes = data.nodes;

  $: sliceIndex = calculateSliceIndex(page);

  onMount(() => {
    console.log($nodes);
  });
  function calculateSliceIndex(page) {
    if (page == 0) return rowsPerPage;
    if (page + rowsPerPage > $nodes.length) return $nodes.length;
    return page + rowsPerPage;
  }
</script>

<div class="w-full">
  <div
    class="rounded-md border-t border-l border-r dark:border-neutral-100 border-neutral-700"
  >
    <div
      class="flex flex-row border-b p-2 dark:border-neutral-100 border-neutral-700"
    >
      <h2 class="text-left w-1/2 sm:w-1/3 lg:w-1/5">Name</h2>
      <h2 class="text-right sm:text-center w-1/2 sm:w-1/3 lg:w-1/5">Url</h2>
      <h2
        class="hidden text-right lg:text-center sm:inline-block sm:w-1/3 lg:w-1/5"
      >
        Port
      </h2>
      <h2 class="hidden text-center lg:inline-block lg:w-1/5">Status</h2>
      <h2 class="hidden text-center lg:inline-block lg:w-1/5">Synced</h2>
    </div>
    {#each $nodes.slice(page, sliceIndex) as node, i}
      <button
        on:click={goto(
          `${base}/nodes/${node.nodeName}?name=${node.nodeName}&port=${node.nodePort}`
        )}
        class={(i == $nodes.length - 1 ? "rounded-md " : "") +
          "flex text-left w-full border-b p-2 dark:border-neutral-100 border-neutral-700 lg:hover:cursor-pointer lg:hover:bg-neutral-200 dark:lg:hover:bg-neutral-800"}
      >
        <p class="text-left w-1/2 sm:w-1/3 lg:w-1/5">
          {node.nodeName}
        </p>
        <p class="text-right sm:text-center w-1/2 sm:w-1/3 lg:w-1/5">
          {node.nodeUrl}
        </p>
        <p
          class="hidden text-right lg:text-center sm:inline-block sm:w-1/3 lg:w-1/5"
        >
          {node.nodePort}
        </p>
        <p class="hidden text-center lg:inline-block lg:w-1/5">
          {node.nodeStatus}
        </p>
        <p class="hidden text-center lg:inline-block lg:w-1/5">
          {node.nodeSynced}
        </p>
      </button>
    {/each}
  </div>
</div>

<div class="flex justify-end mt-4">
  <div>
    <button
      class="rounded-md border dark:border-neutral-100 border-neutral-700 lg:enabled:hover:cursor-pointer lg:enabled:hover:bg-neutral-200 dark:lg:enabled:hover:bg-neutral-800 px-3 py-1 disabled:opacity-40"
      disabled={page <= 0}
      on:click={() => {
        page -= rowsPerPage;
      }}><i class="fa-solid fa-chevron-left" /></button
    >
    <button
      disabled={sliceIndex == $nodes.length}
      class="rounded-md border dark:border-neutral-100 border-neutral-700 lg:enabled:hover:cursor-pointer lg:enabled:hover:bg-neutral-200 dark:lg:enabled:hover:bg-neutral-800 px-3 py-1 disabled:opacity-40"
      on:click={() => {
        page += rowsPerPage;
      }}><i class="fa-solid fa-chevron-right" /></button
    >
  </div>
</div>

<div class="mt-8" />

<Grid columns={2} gridClass="md-grid">
  <div class="w-full h-60 md:h-80">
    <AreaChartWithLabels data={chart7.data} labels={months} id={chart7.name} />
  </div>

  <div class="w-full h-60 md:h-80">
    <LineChartWithLabels data={chart8.data} labels={months} id={chart8.name} />
  </div>
</Grid>

<div class="mt-8" />

<ToggleBox title={"Data"}>
  <Grid columns={5}>
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
      <AreaChart data={chart2.data} id={chart2.name} colors={[COLOR.VIOLET]} />
    </TitleAndTextContainer>
    <TitleAndTextContainer
      title="TITLE"
      text={chart3.data[0].data[chart3.data[0].data.length - 1]}
    >
      <AreaChart data={chart3.data} id={chart3.name} colors={[COLOR.FUSCHIA]} />
    </TitleAndTextContainer>
    <TitleAndTextContainer
      title="TITLE"
      text={chart4.data[0].data[chart4.data[0].data.length - 1]}
    >
      <ColumnChart data={chart4.data} id={chart4.name} colors={[COLOR.ROSE]} />
    </TitleAndTextContainer>
    <TitleAndTextContainer
      title="TITLE"
      text={chart5.data[0].data[chart5.data[0].data.length - 1]}
    >
      <ColumnChart
        data={chart5.data}
        id={chart5.name}
        colors={[COLOR.VIOLET]}
        multipleDataSets={true}
      />
    </TitleAndTextContainer>
  </Grid>
</ToggleBox>

<div class="mt-8" />

<ToggleBox title={"Data"}>
  <Grid columns={4}>
    <TitleAndTextContainer title={"TITLE"} text="VALUE" />
    <TitleAndTextContainer title={"TITLE"} text="VALUE" />
    <TitleAndTextContainerGreen title={"TITLE"} text="VALUE" />
    <TitleAndTextContainerGreen title={"TITLE"} text="VALUE" />
  </Grid>
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
