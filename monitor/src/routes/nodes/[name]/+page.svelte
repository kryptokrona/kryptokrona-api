<script>
  import TitleAndTextContainer from "../../../components/containers/TitleAndTextContainer.svelte";
  import TitleAndTextContainerGreen from "../../../components/containers/TitleAndTextContainerGreen.svelte";
  import MultiItemsGrid from "../../../components/grids/MultiItemsGrid.svelte";
  import ToggleBox from "../../../components/toggle-box/ToggleBox.svelte";
</script>

<h1>Göta Pool</h1>
<div class="mt-8" />
<ToggleBox title="Data">
  <MultiItemsGrid>
    <TitleAndTextContainer />
    <TitleAndTextContainer />
    <TitleAndTextContainer />
  </MultiItemsGrid>
</ToggleBox>

<div class="mt-8" />

<ToggleBox title={"Status"}>
  <MultiItemsGrid columns={6} fullWidthOnPhone={true}>
    <TitleAndTextContainerGreen title="Something" text="OK" />
    <TitleAndTextContainerGreen title="Something" text="OK" />
    <TitleAndTextContainerGreen title="Something" text="OK" />
    <TitleAndTextContainer title="Something" text="OFFLINE" />
    <TitleAndTextContainer title="Something" text="OFFLINE" />
    <TitleAndTextContainerGreen title="Something" text="OK" />
  </MultiItemsGrid>
</ToggleBox>

<div class="mt-8" />
