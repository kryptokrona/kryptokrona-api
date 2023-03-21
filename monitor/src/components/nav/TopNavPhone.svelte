<script>
  import { goto } from "$app/navigation";
  import { user } from "../../stores/user";
  import GreenButton from "../buttons/GreenButton.svelte";

  let phoneMenu;
  let show = false;
  export let repo;

  const toggleMenu = () => {
    phoneMenu.classList.toggle("phone-nav-show");
    show = !show;
  };

  const navigate = (path) => {
    toggleMenu();
    goto(path);
  };
</script>

<div class="flex text-neutral-400 justify-center pt-2 pb-2">
  {#if repo.latestVersion != undefined}
    <div
      class="flex items-center gap-6"
      style="font-size: clamp(10px,2.5vw,13px)"
    >
      <div class="flex items-center">
        <i class="fa-brands fa-github" aria-hidden="true" />
        <p class="pl-1">
          version: <a
            href={repo.latestVersion.path}
            target="_blank"
            rel="noreferrer"
            class="underline">{repo.latestVersion.version}</a
          >
        </p>
      </div>
      <div class="flex items-center">
        <i class="fa-solid fa-star" aria-hidden="true" />
        <p class="pl-1">
          stars: <a
            href={repo.stargazers.path}
            target="_blank"
            rel="noreferrer"
            class="underline">{repo.stargazers.stars}</a
          >
        </p>
      </div>

      <div class="flex items-center">
        <i class="fa-solid fa-code-commit" aria-hidden="true" />
        <p class="pl-1">
          latest: <a
            href={repo.latestCommit.path}
            target="_blank"
            rel="noreferrer"
            class="underline">{repo.latestCommit.date}</a
          >
        </p>
      </div>
    </div>
  {/if}
</div>
<nav class="top-nav pt-4 pb-4" aria-label="main navigation">
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
      <button
        on:click={() => {
          navigate("/pools");
        }}><i class="fa-solid fa-water-ladder " /> pools</button
      >
      {#if $user.email != null}
        <button
          on:click={() => {
            navigate("/profile");
          }}><i class="fa-solid fa-user pb-4" /> profile</button
        >
      {:else}
        <div class="text-center pb-4">
          <GreenButton
            text="Login"
            action={() => {
              navigate("/login");
            }}
          />
        </div>
      {/if}
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
