<script>
  import TopNavButton from "./TopNavButton.svelte";
  import { base } from "$app/paths";
  export let repo;
</script>

<nav class="top-nav flex pt-4 pb-4" aria-label="main navigation">
  <div class="w-1/6">
    <a href={`${base}/`} aria-label="front page">
      <img src={`${base}/logo.png`} class="w-36" alt="Kryptokrona API Logo" />
    </a>
  </div>
  <div class="w-4/5 flex justify-center">
    {#if repo.latestVersion !== undefined}
      <div class="  flex items-center gap-6">
        <div class="flex items-center">
          <i class="fa-brands fa-github" aria-hidden="true" />
          <p class="pl-1">
            version: <a
              href={repo.latestVersion.path}
              target="_blank"
              rel="noreferrer"
              class="hover:underline">{repo.latestVersion.version}</a
            >
          </p>
        </div>
        <div class="flex items-center">
          <i class="fa-solid fa-star" aria-hidden="true" />
          <p class="pl-1">
            stars:
            <a
              href={repo.stargazers.path}
              target="_blank"
              rel="noreferrer"
              class="hover:underline">{repo.stargazers.stars}</a
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
      goTo={`${base}/nodes`}
    />
    <TopNavButton
      iconClass="fa-solid fa-water-ladder"
      label="pools"
      goTo={`${base}/pools`}
    />
    <!-- 
    {#if $user.username != null}
      <TopNavButton
        iconClass="fa-solid fa-user"
        label="profile"
        goTo="{`${base}/profile`}"
      />
    {:else}
      <GreenButton text="Login" action={login} />
    {/if}-->
  </div>
</nav>
