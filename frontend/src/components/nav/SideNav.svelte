<script>
  import SideNavButton from "./SideNavButton.svelte";

</script>
<nav class="p-5 side-menu w-20 bg-black flex justify-center" aria-label="side menu" style="min-height: 100vh;"> 
  <div class="text-center">
    <SideNavButton iconClass="fa-solid fa-plus"/>
    <SideNavButton iconClass="fa-solid fa-border-all"/>
    <SideNavButton iconClass="fa-solid fa-bell"/>
  </div>
</nav>