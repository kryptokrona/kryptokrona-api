<script>
  import GrayButton from "../buttons/GrayButton.svelte";

  export let title = "";
  export let color = "bg-neutral-200 dark:bg-neutral-800"; // tailwind color only
  export let height = "h-64";
</script>

<div class={color + " w-full  rounded-md overflow-hidden relative " + height}>
  <div class="h-1/5 pt-1 text-center">
    <p class="pb-1">{title}</p>
    <GrayButton text="1h" />
    <GrayButton text="24h" />
    <GrayButton text="Weeks" />
    <GrayButton text="Months" />
  </div>
  <div class="h-4/5">
    <slot />
  </div>
</div>
