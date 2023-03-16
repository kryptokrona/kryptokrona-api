<script>
  import BaseGridItem from "../../../components/grids/BaseGridItem.svelte";
  import ToggleBox from "../../../components/toggle-box/ToggleBox.svelte";
</script>

<div>
  <h1>Göta Pool</h1>
  <div class="mt-20" />
  <ToggleBox title="Data">
    <BaseGridItem />
    <BaseGridItem />
    <BaseGridItem />
  </ToggleBox>

  <div class="mt-4" />
  <ToggleBox title="Data">
    <BaseGridItem />
  </ToggleBox>
</div>
