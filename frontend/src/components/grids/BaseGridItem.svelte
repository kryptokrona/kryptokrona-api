<script>
  export let title = "";
  export let text = "";
  export let color = "bg-neutral-300 dark:bg-neutral-800"; // tailwind color only
  export let height = "h-64";
</script>

<div class={color + " w-full  rounded-md overflow-hidden relative " + height}>
  <div class={(text == "" ? "h-1/5" : "h-2/5") + " pt-1 text-center"}>
    <p>{title}</p>
  </div>
  <div class={text == "" ? "hidden" : "h-1/5 text-center"}>
    <h1>{text}</h1>
  </div>
  <div class={text == "" ? "h-4/5" : "h-2/5"}>
    <slot />
  </div>
</div>
