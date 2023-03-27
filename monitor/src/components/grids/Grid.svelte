<script>
  export let columns = 6;
  export let gridClass = "lg-grid"; // sm-grid, md-grid or lg-grid
</script>

<div class={"grid " + gridClass} style={"--columns:" + columns}>
  <slot />
</div>

<style>
  .grid {
    display: grid;
    grid-template-columns: repeat(var(--columns), 1fr);
    gap: 1em;
    justify-content: flex-start;
  }

  @media (max-width: 1280px) {
    .lg-grid {
      grid-template-columns: repeat(3, 1fr) !important;
    }
    .sm-grid {
      grid-template-columns: repeat(2, 1fr) !important;
    }
  }

  @media (max-width: 1280px) {
    .md-grid {
      display: flex !important;
      flex-direction: column !important;
    }
  }

  @media (max-width: 640px) {
    .lg-grid {
      grid-template-columns: repeat(2, 1fr) !important;
    }
    .sm-grid {
      display: flex !important;
      flex-direction: column !important;
    }
  }
</style>
