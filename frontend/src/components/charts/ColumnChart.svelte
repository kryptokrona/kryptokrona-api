<script>
    import ApexCharts from "apexcharts";
    import { onMount } from "svelte";
  
    export let data = [];
    export let labels = [];
    export let id = "";
    export let colors = ['#1e3a8a', '#4c1d95', '#701a75', '#881337'];
    export let multipleDataSets = false;

      var options = {
          series: multipleDataSets ? data : [{ data: data}],
          chart: {
            type: 'bar',
            height: '100%',
            sparkline: {
                    enabled: true
                },
          },
          stroke: {
            curse: 'smooth',
            width: 1
          },
          colors: colors,
          fill: {
            type: 'solid'
          },
          tooltip: {
            enabled: false,
          },
          grid: {
            show: false,
          },
          dataLabels: {
                enabled: false
            },
          labels: labels,
          legend: {
            show: false,
          }
        };
  
          onMount(() => {
            var chart = new ApexCharts(document.querySelector("#" + id), options);
            chart.render();
          })
          
  </script>
  
  <div id={id}></div>