<script>
  export let width = "xl:w-60"; // important: Tailwind size with breakpoint xl
</script>

<div
  class={width +
    " w-full bg-neutral-300 rounded-md overflow-hidden dark:bg-neutral-800 h-64"}
>
  <slot />
</div>
