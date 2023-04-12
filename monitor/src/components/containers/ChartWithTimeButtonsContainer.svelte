<script>
  import GrayButton from "../buttons/GrayButton.svelte";

  export let title = "";
  export let color = "bg-neutral-200 dark:bg-neutral-800"; // tailwind color only
  export let height = "h-64";
</script>

<div class={color + " w-full  rounded-md relative " + height}>
  <div class="h-2/5 pt-1 text-center">
    <p class="pb-1">{title}</p>
    <GrayButton text="1h" />
    <GrayButton text="24h" />
    <GrayButton text="1 Week" />
    <GrayButton text="1 month" />
  </div>
  <div class="h-3/5">
    <slot />
  </div>
</div>
