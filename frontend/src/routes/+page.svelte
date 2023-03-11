
<script>
  import AreaChart from "../components/charts/AreaChart.svelte";
  import ColumnChart from "../components/charts/ColumnChart.svelte";
  import InfoBox from "../components/toggle-box/InfoBox.svelte";
  import ToggleBox from "../components/toggle-box/ToggleBox.svelte";
  import {chart1, chart2, chart3, chart4, chart5 } from "../mock-data/data";

</script>

<div class="mx-auto w-full">
   <ToggleBox title={"Overview 1"}>
    <InfoBox title="CPU" text={chart1.data[chart1.data.length - 1]}>
      <AreaChart data={chart1.data} id={chart1.name} />
    </InfoBox>
    <InfoBox title="RAM" text={chart2.data[chart2.data.length - 1]} width={"w-40"}>
      <AreaChart data={chart2.data} id={chart2.name} colors={['#4c1d95']}/>
    </InfoBox>
    <InfoBox title="DISK" text={chart3.data[chart3.data.length - 1]} width={"w-40"}>
      <AreaChart data={chart3.data} id={chart3.name} colors={['#701a75']}/>
    </InfoBox>
    <InfoBox title="TITLE" text={chart4.data[chart4.data.length - 1]} width={"w-80"}>
      <ColumnChart data={chart4.data} id={chart4.name} colors={['#881337']}/>
    </InfoBox>
    <InfoBox title="TITLE" text={94}>
      <ColumnChart data={chart5.data} id={chart5.name} colors={['#4c1d95','#701a75']} multipleDataSets={true}/>
    </InfoBox>
   </ToggleBox>

   <div class="mt-8"></div>

   <ToggleBox title={"Overview 2"}> 
    <InfoBox title="CPU" text={chart1.data[chart1.data.length - 1]}  width={"w-80"}>
      <AreaChart data={chart1.data} id={"id1"} />
    </InfoBox>
    <InfoBox title="RAM" text={chart2.data[chart2.data.length - 1]}  width={"w-80"}>
      <AreaChart data={chart2.data} id={"id2"} colors={['#4c1d95']}/>
    </InfoBox>
    <InfoBox title="DISK" text={chart3.data[chart3.data.length - 1]} >
      <AreaChart data={chart3.data} id={"id3"} colors={['#701a75']}/>
    </InfoBox>
    <InfoBox title="TITLE" text={chart4.data[chart4.data.length - 1]} >
      <ColumnChart data={chart4.data} id={"id4"} colors={['#881337']}/>
    </InfoBox>
    <InfoBox title="TITLE" text={94}>
      <ColumnChart data={chart5.data} id={"id5"} colors={['#4c1d95','#701a75']} multipleDataSets={true}/>
    </InfoBox>
   </ToggleBox>
</div>


