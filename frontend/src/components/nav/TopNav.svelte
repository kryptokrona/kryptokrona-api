<script>
  import TopNavButton from "./TopNavButton.svelte";
</script>

<nav class="top-nav flex pt-4 pb-4" aria-label="main navigation">
  <a href="/#main" aria-label="front page">
    <img src="./logo.png" class="w-36" alt="kryptokrona api logo" />
  </a>
  <div class="flex justify-end w-full items-center">
    <TopNavButton
      iconClass="fa-solid fa-network-wired"
      label="nodes"
      goTo="/nodes"
    />
    <TopNavButton iconClass="fa-solid fa-gear" label="settings" />
    <TopNavButton iconClass="fa-solid fa-arrows-rotate" label="refresh" />
    <TopNavButton iconClass="fa-regular fa-star" label="favorite" />
    <TopNavButton iconClass="fa-solid fa-display" label="monitor" />
    <TopNavButton iconClass="fa-regular fa-clock" label="time" />
  </div>
</nav>
