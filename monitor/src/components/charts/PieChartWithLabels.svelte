<script>
  import { COLORS } from "../../helpers/colors";
  import { onMount } from "svelte";
  export let data = [];
  export let labels = [];
  export let id = "";
  export let colors = COLORS;

  var options = {
    series: data,
    chart: {
      type: "pie",
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
      width: 1,
    },
    colors: colors,
    fill: {
      type: "solid",
    },
    grid: {
      show: false,
    },

    labels: labels,
  };

  onMount(async () => {
    const ApexCharts = (await import("apexcharts")).default;
    var chart = new ApexCharts(document.querySelector("#" + id), options);
    chart.render();
  });
</script>

<div {id} />
