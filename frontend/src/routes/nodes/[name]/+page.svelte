<script>
  import InfoBox from "../../../components/toggle-box/InfoBox.svelte";
  import InfoBoxGreen from "../../../components/toggle-box/InfoBoxGreen.svelte";
  import ToggleBox from "../../../components/toggle-box/ToggleBox.svelte";
</script>

<div>
  <h1>Göta Pool</h1>
  <div class="mt-20" />
  <ToggleBox title="Data">
    <InfoBox />
    <InfoBox width={"xl:w-96"} />
    <InfoBox />
    <InfoBoxGreen />
    <InfoBox />
  </ToggleBox>

  <div class="mt-4" />
  <ToggleBox title="Data">
    <InfoBox />
    <InfoBox />
    <InfoBox />
    <InfoBoxGreen />
    <InfoBox />
  </ToggleBox>
</div>
