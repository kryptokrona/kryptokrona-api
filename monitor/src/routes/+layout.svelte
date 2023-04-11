<script>
  import "../app.scss";
  import "../theme/chart.scss";
  import TopNav from "../components/nav/TopNav.svelte";
  import { onMount } from "svelte";
  import TopNavPhone from "../components/nav/TopNavPhone.svelte";
  import { browser } from "$app/environment";
  export let data;
  let isPhone = false;
  export const trailingSlash = "always";

  onMount(async () => {
    if (!browser) return;
    isPhone = window.innerWidth < 1024;
    addEventListener("resize", () => {
      isPhone = window.innerWidth < 1024;
    });
  });
</script>

<div
  class="w-full flex justify-center bg-neutral-50 text-neutral-700 dark:bg-neutral-900 dark:text-neutral-100 font-roboto"
  style="min-height: 100vh;"
>
  <div class="container pl-4 pr-4">
    {#if isPhone}
      <TopNavPhone repo={data.repo} />
    {:else}
      <TopNav repo={data.repo} />
    {/if}
    <div class={isPhone ? "mt-4" : "mt-10"}>
      <slot />
    </div>
  </div>
</div>
