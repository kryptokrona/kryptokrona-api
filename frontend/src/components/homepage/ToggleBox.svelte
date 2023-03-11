<script>
    let show = true;
    let toggleBox;

    function toggleVisibillity() {
        toggleBox.classList.toggle("togglebox-hidden");
        show = !show; 
    };
</script>


<div class="mx-auto w-full">
    <button on:click={toggleVisibillity} id="caret">
        <span class="w-8 block">
            {#if show}
                <i class="fa-solid fa-caret-down text-2xl" id="caret"></i>
            {:else}
                <i class="fa-solid fa-caret-right text-2xl"></i>
            {/if}
        </span>
    </button>
    <label for="caret" class="caret-text text-2xl">Overview</label>
    <div bind:this={toggleBox} class="box-grid flex mt-4">
        <div class="dark:bg-neutral-800 w-60 h-full rounded-md"></div>
        <div class="dark:bg-neutral-800 w-60 h-full rounded-md"></div>
        <div class="dark:bg-neutral-800 w-60 h-full rounded-md"></div>
        <div class="dark:bg-neutral-800 w-60 h-full rounded-md"></div>
        <div class="dark:bg-neutral-800 w-60 h-full rounded-md"></div>
        <div class="dark:bg-neutral-800 w-60 h-full rounded-md"></div>
    </div>
</div>

<style>
    .box-grid {
        gap: 1rem;
        height: 13rem;
        transition: all .3s ease-out;
    }
    .caret-text:hover {
        cursor: pointer;
    }
</style>