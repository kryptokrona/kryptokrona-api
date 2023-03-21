<div class="two-items-grid w-full">
  <slot />
</div>

<style>
  .two-items-grid {
    display: grid;
    grid-template-columns: repeat(2, 49%);
    gap: 1em;
  }
  @media (max-width: 768px) {
    .two-items-grid {
      display: flex;
      flex-direction: column;
    }
  }
  @media (max-width: 640px) {
    .two-items-grid {
      gap: 4em;
    }
  }
</style>
