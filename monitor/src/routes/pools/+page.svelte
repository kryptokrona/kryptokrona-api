<script>
  import { COLOR } from "../../helpers/colors";
  import AreaChart from "../../components/charts/AreaChart.svelte";
  import { pools, chart8, months } from "../../mock-data/data";
  import PercentageBar from "../../components/PercentageBar.svelte";
  import TwoItemsGrid from "../../components/grids/TwoItemsGrid.svelte";
  import PieChartWithLabels from "../../components/charts/PieChartWithLabels.svelte";
  import LineChartWithLabels from "../../components/charts/LineChartWithLabels.svelte";

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

      {#each pools.filter((n) => n.name
          .toLowerCase()
          .includes(searchInput.toLowerCase())) as pool, i}
        <div
          class={(i == pools.length - 1 ? "rounded-md " : "") +
            "table-grid w-full border-b p-2 dark:border-neutral-100 border-neutral-700 "}
        >
          <div class="flex items-center">
            <p class="text-left underline w-full">
              <a href={pool.url} target="_blank" rel="noreferrer">{pool.name}</a
              >
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
</div>

<div class="mt-16" />

<TwoItemsGrid>
  <div class="w-full h-80">
    <h2>Block distribution</h2>
    <LineChartWithLabels data={chart8.data} labels={months} id={"line1"} />
  </div>
  <div class="w-full h-80">
    <h2>Hashrate history</h2>
    <PieChartWithLabels
      data={[35, 40, 10, 5, 15, 10]}
      labels={pools.map((p) => p.name)}
      id="pie1"
    />
  </div>
</TwoItemsGrid>

<div class="mt-16" />

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
