<script>
  export let columns = 6;
</script>

<div class="multi-items-grid" style={"--columns:" + columns}>
  <slot />
</div>

<style>
  .multi-items-grid {
    display: grid;
    grid-template-columns: repeat(var(--columns), 1fr);
    gap: 1em;
    justify-content: flex-start;
    transition: display 0.1s ease-out;
  }

  @media (max-width: 1280px) {
    .multi-items-grid {
      grid-template-columns: repeat(3, 31.8%);
    }
  }

  @media (max-width: 768px) {
    .multi-items-grid {
      grid-template-columns: repeat(2, 49%);
    }
  }

  @media (max-width: 640px) {
    .multi-items-grid {
      gap: 0.5em;
    }
  }
</style>
