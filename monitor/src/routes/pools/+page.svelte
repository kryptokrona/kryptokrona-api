<script>
  import { COLOR } from "../../helpers/colors";
  import AreaChart from "../../components/charts/AreaChart.svelte";
  import { pools, poolChart, months } from "../../mock-data/data";
  import PercentageBar from "../../components/PercentageBar.svelte";
  import PieChartWithLabels from "../../components/charts/PieChartWithLabels.svelte";
  import LineChartWithLabels from "../../components/charts/LineChartWithLabels.svelte";
  import Grid from "../../components/grids/Grid.svelte";

  //TODO add sort on height
  let page = 0;
  let rowsPerPage = 5;
  let sliceIndex = 5;

  $: sliceIndex = calculateSliceIndex(page);

  function calculateSliceIndex(page) {
    if (page == 0) return rowsPerPage;
    if (page + rowsPerPage > pools.length) return pools.length;
    return page + rowsPerPage;
  }
</script>

<div class="w-full">
  <div
    class="rounded-md border-t border-l border-r mt-4 dark:border-neutral-100 border-neutral-700"
  >
    <div
      class="table-grid border-b p-2 dark:border-neutral-100 border-neutral-700"
    >
      <div class="flex items-center">
        <h2 class="text-left w-full">Name</h2>
      </div>

      <div class="hidden sm:flex items-center">
        <h2 class="text-center w-full">Fee</h2>
      </div>

      <div class="hidden sm:flex items-center">
        <h2 class="text-center w-full">Miners</h2>
      </div>

      <div class="hidden sm:flex items-center">
        <h2 class="text-center w-full">History</h2>
      </div>

      <div class="hidden sm:flex items-center">
        <h2 class="text-center w-full">Hashrate</h2>
      </div>

      <div class="hidden lg:flex items-center">
        <h2 class="text-center w-full">Network hashrate</h2>
      </div>

      <div class="hidden lg:flex items-center">
        <h2 class=" text-center w-full">Blocks</h2>
      </div>

      <div class="flex items-center">
        <h2 class="text-right md:text-center w-full">Block height</h2>
      </div>

      <div class="hidden sm:flex items-center">
        <h2 class="text-center w-full">Last found</h2>
      </div>
    </div>

    {#each pools.slice(page, sliceIndex) as pool, i}
      <div
        class={(i == pools.length - 1 ? "rounded-md " : "") +
          "table-grid w-full border-b p-2 dark:border-neutral-100 border-neutral-700 "}
      >
        <div class="flex items-center">
          <p class="text-left underline w-full">
            <a href={pool.url} target="_blank" rel="noreferrer">{pool.name}</a>
          </p>
        </div>

        <div class="hidden sm:flex items-center">
          <p class="text-center w-full">15</p>
        </div>

        <div class="hidden sm:flex items-center">
          <p class="text-center w-full">13</p>
        </div>

        <div class="hidden text-center sm:inline-block w-full h-10">
          <AreaChart
            data={[pool.graph]}
            id={pool.name}
            colors={[COLOR.VIOLET]}
          />
        </div>
        <div class="hidden sm:flex items-center">
          <p class="text-center w-full">8.26 MH/s</p>
        </div>

        <div class="hidden lg:flex items-center">
          <div class="h-2/3 text-center w-full">
            <PercentageBar color={COLOR.FUSCHIA} />
          </div>
        </div>

        <div class="hidden lg:flex items-center">
          <p class="text-center w-full">49</p>
        </div>

        <div class="flex items-center">
          <p class="text-right sm:text-center w-full">41347822</p>
        </div>

        <div class="hidden sm:flex items-center">
          <p class="text-center w-full">1347817</p>
        </div>
      </div>
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
      disabled={sliceIndex == pools.length}
      class="rounded-md border dark:border-neutral-100 border-neutral-700 lg:enabled:hover:cursor-pointer lg:enabled:hover:bg-neutral-200 dark:lg:enabled:hover:bg-neutral-800 px-3 py-1 disabled:opacity-40"
      on:click={() => {
        page += rowsPerPage;
      }}><i class="fa-solid fa-chevron-right" /></button
    >
  </div>
</div>

<div class="mt-8" />

<Grid columns={2} gridClass="md-grid">
  <div>
    <h2>Hashrate history</h2>
    <div class="w-full h-60 md:h-80">
      <LineChartWithLabels data={poolChart.data} labels={months} id={"line1"} />
    </div>
  </div>
  <div>
    <h2>Block distribution</h2>
    <div class="w-full h-60 md:h-80">
      <PieChartWithLabels
        data={[35, 40, 10, 5, 15, 10]}
        labels={pools.map((p) => p.name)}
        id="pie1"
      />
    </div>
  </div>
</Grid>

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

  .table-grid {
    display: grid;
    grid-template-columns: 14% 9% 9% 14% 14% 11% 11% 9% 9%;
  }

  @media (max-width: 1280px) {
    .table-grid {
      font-size: 14px !important;
    }
  }
  @media (max-width: 1024px) {
    .table-grid {
      grid-template-columns: 17% 12% 12% 17% 16% 13% 13%;
      font-size: 12px !important;
    }
  }

  @media (max-width: 640px) {
    .table-grid {
      grid-template-columns: 50% 50%;
      font-size: initial !important;
    }
  }
</style>
