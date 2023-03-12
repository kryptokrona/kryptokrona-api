<script>
  import SideNavButton from "./SideNavButton.svelte";

</script>
<nav class="p-5 w-20 bg-black justify-center hidden sm:flex" aria-label="side menu" style="min-height: 100vh;"> 
  <div class="text-center">
    <SideNavButton iconClass="fa-solid fa-plus"/>
    <SideNavButton iconClass="fa-solid fa-border-all"/>
    <SideNavButton iconClass="fa-solid fa-bell"/>
  </div>
</nav>