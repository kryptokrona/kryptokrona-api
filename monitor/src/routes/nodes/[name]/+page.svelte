<script>
    import TitleAndTextContainer from "../../../components/containers/TitleAndTextContainer.svelte";
    import TitleAndTextContainerGreen from "../../../components/containers/TitleAndTextContainerGreen.svelte";
    import Grid from "../../../components/grids/Grid.svelte";

    import ToggleBox from "../../../components/toggle-box/ToggleBox.svelte";
</script>

<h1>Göta Pool</h1>
<div class="mt-8" />
<ToggleBox title="Data">
  <Grid>
    <TitleAndTextContainer />
    <TitleAndTextContainer />
    <TitleAndTextContainer />
  </Grid>
</ToggleBox>

<div class="mt-8" />

<ToggleBox title={"Status"}>
  <Grid columns={6} gridClass="sm-grid">
    <TitleAndTextContainerGreen title="Something" text="OK" />
    <TitleAndTextContainerGreen title="Something" text="OK" />
    <TitleAndTextContainerGreen title="Something" text="OK" />
    <TitleAndTextContainer title="Something" text="OFFLINE" />
    <TitleAndTextContainer title="Something" text="OFFLINE" />
    <TitleAndTextContainerGreen title="Something" text="OK" />
  </Grid>
</ToggleBox>

<div class="mt-8" />
