<script>
  export let title;

  let show = true;
  let toggleBox;
  function toggleVisibillity() {
    toggleBox.classList.toggle("togglebox-hidden");
    show = !show;
  }
</script>

<button on:click={toggleVisibillity} class="flex" aria-label={title}>
  <span class="w-8">
    {#if show}
      <i class="fa-solid fa-chevron-down text-xl" aria-hidden="true" />
    {:else}
      <i class="fa-solid fa-chevron-right text-xl" aria-hidden="true" />
    {/if}
  </span>
  <h2 class="caret-text text-2xl font-bold">{title}</h2>
</button>

<div bind:this={toggleBox} class="mt-4">
  <slot />
</div>

<style>
  .caret-text:hover {
    cursor: pointer;
  }
</style>
