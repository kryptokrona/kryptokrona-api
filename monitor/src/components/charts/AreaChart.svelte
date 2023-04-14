<script>
  import { onMount } from "svelte";
  import { COLORS } from "../../helpers/colors";
  export let data = [];
  export let labels = [];
  export let xFormatter = (value) => value;
  export let yFormatter = (value) => value;
  export let id = "";
  export let colors = COLORS;
  export let tooltipEnabled = false;

  var chart;

  $: update(data.data);

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
      enabled: tooltipEnabled,
    },
    grid: {
      show: false,
    },
    yaxis: {
      labels: {
        formatter: yFormatter,
      },
    },
    xaxis: {
      labels: {
        formatter: xFormatter,
      },
    },
    labels: labels,
    dataLabels: {
      enabled: false,
    },
    legend: {
      show: false,
    },
  };

  const update = () => {
    if (chart) {
      chart.updateOptions({
        series: data,
        labels: labels,
      });
    }
  };

  onMount(() => {
    render();
  });

  async function render() {
    const ApexCharts = (await import("apexcharts")).default;
    chart = new ApexCharts(document.querySelector("#" + id), options);
    chart.render();
  }
</script>

<div {id} />
