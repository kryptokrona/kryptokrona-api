<script>
  import "../app.scss";
  import "../theme/chart.scss";
  import TopNav from "../components/nav/TopNav.svelte";
  import { onMount } from "svelte";
  import TopNavPhone from "../components/nav/TopNavPhone.svelte";

  let isPhone = false;

  onMount(() => {
    if (typeof window === undefined) return;

    isPhone = window.innerWidth < 1024;

    addEventListener("resize", () => {
      isPhone = window.innerWidth < 1024;
    });
  });
</script>

<div
  class="w-full flex justify-center bg-neutral-100 text-neutral-900 dark:bg-neutral-900 dark:text-neutral-100 font-roboto"
  style="min-height: 100vh;"
>
  <div class="container pl-4 pr-4">
    {#if isPhone}
      <TopNavPhone />
    {:else}
      <TopNav />
    {/if}
    <div class={isPhone ? "mt-4" : "mt-10"} id="main">
      <slot />
    </div>
  </div>
</div>
