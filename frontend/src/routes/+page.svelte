<script>
  import { onMount } from "svelte";
  import AreaChart from "../components/charts/AreaChart.svelte";
  import ColumnChart from "../components/charts/ColumnChart.svelte";
  import LineChart from "../components/charts/LineChart.svelte";
  import InfoBox from "../components/toggle-box/InfoBox.svelte";
  import InfoBoxGreen from "../components/toggle-box/InfoBoxGreen.svelte";
  import ToggleBox from "../components/toggle-box/ToggleBox.svelte";
  import {
    chart1,
    chart2,
    chart3,
    chart4,
    chart5,
    chart6,
  } from "../mock-data/data";
</script>

<div>
  <ToggleBox title={"Overview 1"}>
    <InfoBox
      title="CPU"
      text={chart1.data[0].data[chart1.data[0].data.length - 1]}
      width={"xl:w-60"}
    >
      <AreaChart data={chart1.data} id={chart1.name} />
    </InfoBox>
    <InfoBox
      title="RAM"
      text={chart2.data[0].data[chart2.data[0].data.length - 1]}
      width={"xl:w-60"}
    >
      <AreaChart data={chart2.data} id={chart2.name} colors={["#4c1d95"]} />
    </InfoBox>
    <InfoBox
      title="DISK"
      text={chart3.data[0].data[chart3.data[0].data.length - 1]}
      width={"xl:w-60"}
    >
      <AreaChart data={chart3.data} id={chart3.name} colors={["#701a75"]} />
    </InfoBox>
    <InfoBox
      title="TITLE"
      text={chart4.data[0].data[chart4.data[0].data.length - 1]}
      width={"xl:w-60"}
    >
      <ColumnChart data={chart4.data} id={chart4.name} colors={["#881337"]} />
    </InfoBox>
    <InfoBox
      title="TITLE"
      text={chart5.data[0].data[chart5.data[0].data.length - 1]}
      width={"xl:w-96"}
    >
      <ColumnChart
        data={chart5.data}
        id={chart5.name}
        colors={["#4c1d95", "#701a75"]}
        multipleDataSets={true}
      />
    </InfoBox>
  </ToggleBox>

  <div class="mt-8" />

  <ToggleBox title={"Overview 2"}>
    <InfoBox
      title="TITLE"
      text={chart6.data[0].data[chart6.data[0].data.length - 1]}
      width={"xl:w-60"}
    >
      <AreaChart data={chart1.data} id={"id1"} />
    </InfoBox>
    <InfoBoxGreen title="TITLE" text={"0"} />
    <InfoBoxGreen title="TITLE" text={"0"} />
    <InfoBox
      title="TITLE"
      text={chart6.data[0].data[chart6.data[0].data.length - 1]}
      width={"xl:w-96"}
    >
      <AreaChart data={chart6.data} id={chart6.name} />
    </InfoBox>
  </ToggleBox>
</div>
