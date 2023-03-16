<script>
  import { onMount } from "svelte";
  export let data = [];
  export let labels = [];
  export let id = "";
  export let colors = ["#1e3a8a", "#4c1d95", "#701a75", "#881337"];

  var options = {
    series: data,
    chart: {
      type: "line",
      height: "100%",
      width: "100%",
      fontFamily: "Roboto mono, sans-serif",
      toolbar: {
        show: false,
      },
      animations: {
        enabled: false,
      },
    },
    stroke: {
      curse: "smooth",
      width: 4,
    },
    colors: colors,
    fill: {
      type: "solid",
    },
    grid: {
      show: false,
    },
    dataLabels: {
      enabled: false,
    },
    labels: labels,
    yaxis: {
      opposite: true,
    },
  };

  onMount(async () => {
    const ApexCharts = (await import("apexcharts")).default;
    var chart = new ApexCharts(document.querySelector("#" + id), options);
    chart.render();
  });
</script>

<div {id} />
