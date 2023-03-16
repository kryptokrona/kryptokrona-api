<script>
  export let title = "";
  export let color = "bg-neutral-300 dark:bg-neutral-800"; // tailwind color only
  export let height = "h-64";
</script>

<div class={color + " w-full  rounded-md overflow-hidden relative " + height}>
  <div class="h-1/5 pt-1 text-center">
    <p class="pb-1">{title}</p>
    <button
      aria-label="latest hour"
      class="rounded-md bg-neutral-400 p-1
      text-neutral-900
      hover:bg-neutral-500
      dark:bg-neutral-700
      dark:text-neutral-100 
      dark:hover:bg-neutral-500">1h</button
    >
    <button
      aria-label="latest 24 hours"
      class="rounded-md bg-neutral-400 p-1
      text-neutral-900
      hover:bg-neutral-500
      dark:bg-neutral-700
      dark:text-neutral-100 
      dark:hover:bg-neutral-500">24h</button
    >
    <button
      aria-label="latest week"
      class="rounded-md bg-neutral-400 p-1
      text-neutral-900
      hover:bg-neutral-500
      dark:bg-neutral-700
      dark:text-neutral-100 
      dark:hover:bg-neutral-500">Weeks</button
    >
    <button
      aria-label="latest month"
      class="rounded-md bg-neutral-400 p-1
      text-neutral-900
      hover:bg-neutral-500
      dark:bg-neutral-700
      dark:text-neutral-100 
      dark:hover:bg-neutral-500">Months</button
    >
  </div>
  <div class="h-4/5">
    <slot />
  </div>
</div>
