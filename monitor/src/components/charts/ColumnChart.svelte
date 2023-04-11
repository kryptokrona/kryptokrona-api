<script>
  import { COLORS } from "../../helpers/colors";
  import { onMount } from "svelte";
  export let data = [];
  export let id = "";
  export let colors = COLORS;
  export let tooltipEnabled = false;

  var options = {
    series: data,
    chart: {
      type: "bar",
      height: "100%",
      width: "100%",
      sparkline: {
        enabled: true,
      },
    },
    stroke: {
      curse: "smooth",
      width: 1,
    },
    colors: colors,
    fill: {
      type: "solid",
    },
    tooltip: {
      enabled: tooltipEnabled,
    },
    grid: {
      show: false,
    },
    dataLabels: {
      enabled: false,
    },
    legend: {
      show: false,
    },
  };

  onMount(async () => {
    const ApexCharts = (await import("apexcharts")).default;
    var chart = new ApexCharts(document.querySelector("#" + id), options);
    chart.render();
  });
</script>

<div {id} />
