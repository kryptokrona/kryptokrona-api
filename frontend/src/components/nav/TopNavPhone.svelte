<script>
  import { goto } from "$app/navigation";

  let phoneMenu;
  let show = false;

  const toggleMenu = () => {
    phoneMenu.classList.toggle("phone-nav-show");
    show = !show;
  };

  const navigate = (path) => {
    toggleMenu();
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
        class="
                    text-center
                    relative 
                    bg-neutral-400
                    dark:bg-neutral-700
                    rounded-md 
                    py-1.5 px-2
                    mr-1"
        aria-label="toggle menu"
      >
        {#if show}
          <i class="fa-solid fa-xmark w-5 h-5" aria-hidden="true" />
        {:else}
          <i class="fa-solid fa-bars w-5 h-5" aria-hidden="true" />
        {/if}
      </button>
    </div>
  </div>

  <div
    bind:this={phoneMenu}
    class="phone-nav mt-4 rounded-md bg-neutral-300 dark:bg-neutral-800"
  >
    <div class="flex flex-col gap-4">
      <button
        on:click={() => {
          navigate("/nodes");
        }}
        class="pt-4"><i class="fa-solid fa-network-wired" /> nodes</button
      >
      <button><i class="fa-solid fa-water-ladder pb-4" /> pools</button>
    </div>
  </div>
</nav>

<style>
  .phone-nav {
    -webkit-transition: max-height 0.2s ease-in-out;
    -moz-transition: max-height 0.2s ease-in-out;
    -ms-transition: max-height 0.2s ease-in-out;
    -o-transition: max-height 0.2s ease-in-out;
    transition: max-height 0.2s ease-in-out;
    overflow: hidden;
    max-height: 0;
  }
</style>
