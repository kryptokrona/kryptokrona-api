<script>
    import "../app.css";
    import SideNav from "../components/nav/SideNav.svelte";
    import TopNav from "../components/nav/TopNav.svelte";
</script>
  
<div class="bg-neutral-100 inline-flex w-full text-neutral-900 dark:bg-neutral-900 dark:text-neutral-100 font-roboto" style="min-height: 100vh;">   
    <SideNav />
    <div class="w-full">
    <TopNav />
        <div class="p-5 mt-10">
            <slot />
        </div>
    </div>
</div>

