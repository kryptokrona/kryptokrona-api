<script>
    import {COLOR} from "../../helpers/colors";
    import {onMount} from "svelte";

    export let id;
  export let data = 0;
  export let color = COLOR.green;
  export let warningPoint = 60;
  export let criticalPoint = 90;

  onMount(async () => {
    if (data[0] <= warningPoint) color = "#15803d";
    else if (data[0] > warningPoint && data < criticalPoint)
      color = COLOR.ORANGE;
    else color = COLOR.RED;

    var options = {
      series: [data],
      chart: {
        height: "100%",
        width: "100%",
        type: "radialBar",
      },
      fill: { opacity: 1 },
      colors: [color],
      plotOptions: {
        radialBar: {
          hollow: {
            opacity: 0,
            size: "40%",
          },
          track: { color: "#ffff", strokeWidth: "90%" },
          dataLabels: {
            name: { show: false },
            value: { show: false },
          },
        },
      },
    };

    const ApexCharts = (await import("apexcharts")).default;
    var chart = new ApexCharts(document.querySelector("#" + id), options);
    chart.render();
  });
</script>

<div {id} />
