<script>
  import { onMount } from "svelte";
  export let data = [];
  export let id = "";
  export let colors = ["#1e3a8a", "#4c1d95", "#701a75", "#881337"];

  var options = {
    series: data,
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
    colors: colors,
    fill: {
      type: "solid",
    },
    tooltip: {
      enabled: false,
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
