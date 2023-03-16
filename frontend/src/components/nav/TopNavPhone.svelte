<script>
  import { goto } from "$app/navigation";

  let phoneMenu;

  const toggleMenu = () => {
    phoneMenu.classList.toggle("hidden");
  };

  const navigate = (path) => {
    phoneMenu.classList.toggle("hidden");
    goto(path);
  };
</script>

<nav class="top-nav  pt-4 pb-4" aria-label="main navigation">
  <div class="flex">
    <a href="/">
      <img src="./logo.png" class="w-36" alt="kryptokrona api" />
    </a>
    <div class="flex justify-end w-full items-center">
      <button
        on:click={toggleMenu}
        class="tooltip-container
                    text-center
                    relative 
                    bg-neutral-400
                    text-neutral-900
                    dark:bg-neutral-700
                    dark:text-neutral-100 
                    rounded-md 
                    py-1.5 px-2
                    mr-1"
      >
        <i class="fa-solid fa-bars w-5 h-5" />
      </button>
    </div>
  </div>
  <div
    bind:this={phoneMenu}
    class="phone-nav flex justify-center pt-4 pb-4 mt-4 hidden rounded-md bg-neutral-100 text-neutral-900 dark:text-neutral-100 dark:bg-neutral-700"
  >
    <div class="flex flex-col gap-4">
      <button
        on:click={() => {
          navigate("/nodes");
        }}><i class="fa-solid fa-network-wired" /> nodes</button
      >
      <button><i class="fa-solid fa-water-ladder" /> pools</button>
      <button><i class="fa-solid fa-gear" /> settings</button>
    </div>
  </div>
</nav>

<style>
  .phone-nav {
    animation: slide-in 1s ease-in;
  }
</style>
