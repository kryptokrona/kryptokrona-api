<script>
  import { page } from "$app/stores";
</script>

<svelte:head>
  <meta property="og:type" content="website" />
  <meta property="og:title" content="Kryptokrona API" />
  <meta property="og:url" content={$page.url} />
  <meta
    property="og:description"
    content="Kryptokrona API for caching and processing data from the blockchain to provide faster access for services."
  />
  <meta
    property="og:image"
    content={$page.url.origin + "/monitor/logo_meta.png"}
  />
</svelte:head>
