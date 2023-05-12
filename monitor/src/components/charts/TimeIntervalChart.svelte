<script>
  import { onMount } from "svelte";
  import { createEventDispatcher } from "svelte";

  const dispatch = createEventDispatcher();

  export let title;
  export let data;
  export let labels;
  export let id;
  export let color;
  export let includeYear = false;
  export let activeInterval = "1h";
  export let type = "line";
  export let height = "h-64";
  export let yFormatterTooltip = (v) => {
    return v;
  };
  export let xFormatter = (v) => {
    return v;
  };
  export let yAxisFormatter = (v) => {
    return v;
  };

  let hourButton,
    hoursButton,
    weekButton,
    monthButton,
    yearButton,
    activeButton;
  let chart;
  const buttonClass =
    "text-center bg-neutral-300 hover:bg-neutral-400 dark:bg-neutral-700 dark:hover:bg-neutral-500 rounded-md p-1.5 mr-1";

  onMount(() => {
    switch (activeInterval) {
      case "1h":
        activeButton = hourButton;
        break;
      case "24h":
        activeButton = hoursButton;
        break;
      case "1w":
        activeButton = weekButton;
        break;
      case "1m":
        activeButton = monthButton;
        break;
      case "1y":
        activeButton = yearButton;
        break;
    }
    activeButton.classList.add("active");
    render();
  });

  $: update(data);

  let options = {
    series: data,
    labels: labels,
    chart: {
      type: type,
      height: "100%",
      width: "100%",
      toolbar: {
        show: false,
      },
      animations: {
        enabled: true,
        easing: "easeinout",
        speed: 600,
        dynamicAnimation: {
          enabled: true,
          speed: 350,
        },
      },
    },
    stroke: {
      curse: "smooth",
      width: type == "line" ? 3 : 0,
    },
    colors: [color],
    fill: {
      type: "solid",
    },
    tooltip: {
      enabled: true,
      y: {
        formatter: yFormatterTooltip,
      },
    },
    grid: {
      show: false,
    },
    xaxis: {
      labels: {
        maxHeight: 5,
        show: false,
        rotate: 0,
        formatter: xFormatter,
      },
      axisTicks: {
        show: false,
      },
    },
    yaxis: {
      min: 0,
      tickAmount: 4,
      opposite: true,
      forceNiceScale: true,
      labels: {
        formatter: yAxisFormatter,
      },
    },
    dataLabels: {
      enabled: false,
    },
    legend: {
      show: false,
    },
    plotOptions: {
      bar: {
        borderRadius: 2,
        borderRadiusApplication: "end",
      },
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
  class={"bg-neutral-200 dark:bg-neutral-800 w-full  rounded-md relative " +
    height}
>
  <div class="h-2/6 pt-1 text-center">
    <p class="pb-2">{title}</p>
    <button
      bind:this={hourButton}
      on:click={() => {
        activeButton.classList.remove("active");
        hourButton.classList.add("active");
        activeButton = hourButton;
        updateTimeInterval("1h");
      }}
      aria-label="1 hour"
      class={buttonClass}
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
        updateTimeInterval("1w");
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
        updateTimeInterval("1m");
      }}
      aria-label="30 days"
      class={buttonClass}
    >
      30d
    </button>
    {#if includeYear}
      <button
        bind:this={yearButton}
        on:click={() => {
          activeButton.classList.remove("active");
          yearButton.classList.add("active");
          activeButton = yearButton;
          updateTimeInterval("1y");
        }}
        aria-label="1 year"
        class={buttonClass}
      >
        1y
      </button>
    {/if}
  </div>
  <div class="h-4/6">
    <div {id} />
  </div>
</div>
