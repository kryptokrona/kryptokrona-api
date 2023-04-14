<script>
  import { onMount } from "svelte";
  import { getISODate, getTwoDecimalsPercentage } from "../../helpers/helpers";
  import { createEventDispatcher } from "svelte";

  const dispatch = createEventDispatcher();

  export let title;
  export let data;
  export let labels;
  export let id;
  export let color;

  let hourButton, hoursButton, weekButton, monthButton, activeButton;
  let chart;
  const buttonClass =
    "text-center bg-neutral-300 hover:bg-neutral-400 dark:bg-neutral-700 dark:hover:bg-neutral-500 rounded-md p-1.5 mr-1";

  onMount(() => {
    activeButton = hourButton;
    render();
  });

  $: update(data);

  let options = {
    series: data,
    labels: labels,
    chart: {
      type: "area",
      height: "100%",
      width: "100%",
      sparkline: {
        enabled: true,
      },
      animations: {
        enabled: true,
        easing: "easeinout",
        speed: 800,
        dynamicAnimation: {
          enabled: true,
          speed: 350,
        },
      },
    },
    stroke: {
      curse: "smooth",
      width: 0,
    },
    colors: [color],
    fill: {
      type: "solid",
    },
    tooltip: {
      enabled: true,
    },
    grid: {
      show: false,
    },
    xaxis: {
      labels: {
        formatter: getISODate,
      },
    },
    yaxis: {
      labels: {
        formatter: getTwoDecimalsPercentage,
      },
    },
    dataLabels: {
      enabled: false,
    },
    legend: {
      show: false,
    },
  };

  function updateTimeInterval(time) {
    dispatch("updateTimeInterval", {
      time: time,
    });
  }

  function update() {
    if (!data) return;
    if (chart) {
      chart.updateOptions({
        series: data,
        labels: labels,
      });
    }
  }

  async function render() {
    const ApexCharts = (await import("apexcharts")).default;
    chart = new ApexCharts(document.querySelector("#" + id), options);
    chart.render();
  }
</script>

<div
  class={"bg-neutral-200 dark:bg-neutral-800 w-full  rounded-md relative h-64"}
>
  <div class="h-2/5 pt-1 text-center">
    <p class="pb-1">{title}</p>
    <button
      bind:this={hourButton}
      on:click={() => {
        activeButton.classList.remove("active");
        hourButton.classList.add("active");
        activeButton = hourButton;
        updateTimeInterval("1h");
      }}
      aria-label="1 hour"
      class={buttonClass + " active"}
    >
      1h
    </button>
    <button
      bind:this={hoursButton}
      on:click={() => {
        activeButton.classList.remove("active");
        hoursButton.classList.add("active");
        activeButton = hoursButton;
        updateTimeInterval("24h");
      }}
      aria-label="24 hours"
      class={buttonClass}
    >
      24h
    </button>
    <button
      bind:this={weekButton}
      on:click={() => {
        activeButton.classList.remove("active");
        weekButton.classList.add("active");
        activeButton = weekButton;
        updateTimeInterval("7d");
      }}
      aria-label="7 days"
      class={buttonClass}
    >
      7d
    </button>

    <button
      bind:this={monthButton}
      on:click={() => {
        activeButton.classList.remove("active");
        monthButton.classList.add("active");
        activeButton = monthButton;
        updateTimeInterval("30d");
      }}
      aria-label="30 days"
      class={buttonClass}
    >
      30d
    </button>
  </div>
  <div class="h-3/5">
    <div {id} />
  </div>
</div>
