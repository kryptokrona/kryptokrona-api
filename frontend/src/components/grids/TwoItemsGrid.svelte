<div class="two-items-grid w-full">
  <slot />
</div>

<style>
  .two-items-grid {
    display: grid;
    grid-template-columns: repeat(2, 48%);
    justify-content: space-between;
    gap: 1rem;
  }
  @media (max-width: 768px) {
    .two-items-grid {
      display: flex;
      gap: 2rem;
      flex-direction: column;
    }
  }
</style>
