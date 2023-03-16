<script>
  import "../app.scss";
  import "../theme/chart.scss";
  import TopNav from "../components/nav/TopNav.svelte";
</script>

<div
  class="w-full flex justify-center bg-neutral-100 text-neutral-900 dark:bg-neutral-900 dark:text-neutral-100 font-roboto"
  style="min-height: 100vh;"
>
  <div class="container pl-4 pr-4">
    <TopNav />
    <div class="pt-5 mt-10">
      <slot />
    </div>
  </div>
</div>
