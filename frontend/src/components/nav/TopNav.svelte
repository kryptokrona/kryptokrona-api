<script>
  import TopNavButton from "./TopNavButton.svelte";

</script>
<nav class="top-nav flex" aria-label="top navigation">
    <div class="flex justify-end w-full items-center">
       <TopNavButton iconClass="fa-solid fa-magnifying-glass" />
       <TopNavButton iconClass="fa-solid fa-gear"/>
       <TopNavButton iconClass="fa-solid fa-arrows-rotate" />
       <TopNavButton iconClass="fa-regular fa-star"/>
       <TopNavButton iconClass="fa-solid fa-display" />
       <TopNavButton iconClass="fa-regular fa-clock" />
    </div>  
</nav>