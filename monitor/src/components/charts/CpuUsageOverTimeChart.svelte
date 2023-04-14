<script>
  import { onMount } from "svelte";
  import { COLORS } from "../../helpers/colors";
  import { getCpuUsageOverTime } from "../../api/prometheus";
  import { cpuUsageOverTime } from "../../stores/data";
  import { getISODate, getTwoDecimalsPercentage } from "../../helpers/helpers";

  let hourButton, hoursButton, weekButton, monthButton, activeButton;
  let chart;
  const buttonClass =
    "text-center bg-neutral-300 hover:bg-neutral-400 dark:bg-neutral-700 dark:hover:bg-neutral-500 rounded-md p-1.5 mr-1";

  onMount(() => {
    activeButton = hourButton;
    render();
  });

  $: update($cpuUsageOverTime);

  let options = {
    series: [{ data: $cpuUsageOverTime.values, name: "CPU" }],
    chart: {
      type: "area",
      height: "100%",
      width: "100%",
      sparkline: {
        enabled: true,
      },
      animations: {
        enabled: false,
      },
    },
    stroke: {
      curse: "smooth",
      width: 1,
    },
    colors: COLORS,
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
    labels: $cpuUsageOverTime.times,
    dataLabels: {
      enabled: false,
    },
    legend: {
      show: false,
    },
  };

  async function setCpuUsageOverTime(time) {
    $cpuUsageOverTime = await getCpuUsageOverTime(time);
  }

  const update = () => {
    if (!$cpuUsageOverTime) return;
    if (chart) {
      chart.updateOptions({
        series: [{ data: $cpuUsageOverTime.values, name: "CPU" }],
        labels: $cpuUsageOverTime.times,
      });
    }
  };

  async function render() {
    const ApexCharts = (await import("apexcharts")).default;
    chart = new ApexCharts(
      document.querySelector("#cpuUsageOverTime1"),
      options
    );
    chart.render();
  }
</script>

<div
  class={"bg-neutral-200 dark:bg-neutral-800 w-full  rounded-md relative h-64"}
>
  <div class="h-2/5 pt-1 text-center">
    <p class="pb-1">CPU usage</p>
    <button
      bind:this={hourButton}
      on:click={() => {
        activeButton.classList.remove("active");
        hourButton.classList.add("active");
        activeButton = hourButton;
        setCpuUsageOverTime("1h");
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
        setCpuUsageOverTime("24h");
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
        setCpuUsageOverTime("7d");
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
        setCpuUsageOverTime("30d");
      }}
      aria-label="30 days"
      class={buttonClass}
    >
      30d
    </button>
  </div>
  <div class="h-3/5">
    <div id="cpuUsageOverTime1" />
  </div>
</div>
