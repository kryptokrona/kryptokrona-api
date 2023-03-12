<script>
  import { onMount } from "svelte";

  export let data = [];
  export let labels = [];
  export let id = "";
  export let colors = ["#1e3a8a", "#4c1d95", "#701a75", "#881337"];

  var options = {
    series: [{ data: data }],
    chart: {
      type: "line",
      height: "100%",
      width: "100%",
      sparkline: {
        enabled: true,
      },
    },
    stroke: {
      curse: "smooth",
      width: 5,
    },
    colors: colors,
    fill: {
      type: "solid",
    },
    tooltip: {
      enabled: true,
    },
    grid: {
      show: false,
    },
    dataLabels: {
      enabled: false,
    },
    labels: labels,
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
