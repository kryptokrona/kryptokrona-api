<script>
  import TopNavButton from "./TopNavButton.svelte";
  import { goto } from "$app/navigation";
  import GreenButton from "../buttons/GreenButton.svelte";
  import { user } from "../../stores/user";
  export let repo;
</script>

<nav class="top-nav flex pt-4 pb-4 " aria-label="main navigation">
  <div class="w-1/6">
    <a href="/" aria-label="front page">
      <img src="./logo.png" class="w-36" alt="kryptokrona api logo" />
    </a>
  </div>
  <div class="w-4/5 flex justify-center">
    {#if repo.version != undefined}
      <div class="  flex items-center gap-6">
        <div class="flex items-center">
          <i class="fa-brands fa-github" aria-hidden="true" />
          <p class="pl-1">version: {repo.version}</p>
        </div>
        <div class="flex items-center">
          <i class="fa-solid fa-star" aria-hidden="true" />
          <p class="pl-1">stars: {repo.stars}</p>
        </div>

        <div class="flex items-center">
          <i class="fa-solid fa-code-commit" aria-hidden="true" />
          <p class="pl-1">commits: {repo.commitCount}</p>
        </div>
        <div class="flex items-center">
          <i class="fa-solid fa-code-commit" aria-hidden="true" />
          <p class="pl-1">
            latest: <a
              href={repo.latestCommit.path}
              target="_blank"
              rel="noreferrer"
              class="hover:underline">{repo.latestCommit.date}</a
            >
          </p>
        </div>
        <div class="flex items-center">
          <p class="pl-1">made by:</p>
          {#each repo.contributors as contributor}
            <a
              href="https://github.com/{contributor.login}"
              target="_blank"
              rel="noreferrer"
            >
              <img
                src={contributor.avatar_url}
                alt="contributor"
                class="border-neutral-700 dark:border-neutral-100 hover:scale-125 border w-8 h-8 rounded-full ml-2"
              />
            </a>
          {/each}
        </div>
      </div>
    {/if}
  </div>
  <div class="flex justify-end w-1/6 items-center">
    <TopNavButton
      iconClass="fa-solid fa-network-wired"
      label="nodes"
      goTo="/nodes"
    />
    <TopNavButton iconClass="fa-solid fa-water-ladder" label="pools" />
    {#if $user.email != null}
      <TopNavButton
        iconClass="fa-solid fa-user"
        label="profile"
        goTo="/profile"
      />
    {:else}
      <GreenButton
        text="Login"
        action={() => {
          goto("/login");
        }}
      />
    {/if}
  </div>
</nav>
