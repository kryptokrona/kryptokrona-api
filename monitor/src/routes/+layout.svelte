<script>
  import "../app.scss";
  import "../theme/chart.scss";
  import TopNav from "../components/nav/TopNav.svelte";
  import { onMount } from "svelte";
  import TopNavPhone from "../components/nav/TopNavPhone.svelte";
  import { browser } from "$app/environment";
  import Footer from "../components/Footer.svelte";
  import Head from "../components/Head.svelte";
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

<Head />
<div
  class=" bg-neutral-50 text-neutral-700 dark:bg-neutral-900 dark:text-neutral-100 font-roboto"
>
  <div class="w-full flex justify-center" style="min-height: 100vh;">
    <div class="container pl-4 pr-4">
      {#if isPhone}
        <TopNavPhone repo={data.repo} />
      {:else}
        <TopNav repo={data.repo} />
      {/if}
      <main class={isPhone ? "mt-4" : "mt-16"}>
        <slot />
      </main>
    </div>
  </div>

  <Footer />
</div>
