<script>
    export let title;
    let show = true;
    let toggleBox;
    function toggleVisibillity() {
        toggleBox.classList.toggle("togglebox-hidden");
        show = !show; 
    };
</script>


<div class="mx-auto w-full">
    <button on:click={toggleVisibillity} class="flex">
        <span class="w-8">
            {#if show}
                <i class="fa-solid fa-caret-down text-2xl"></i>
            {:else}
                <i class="fa-solid fa-caret-right text-2xl"></i>
            {/if}
        </span>
        <h2 class="caret-text text-2xl font-bold"> {title}</h2>
    </button>
    
    <div bind:this={toggleBox} class="box-grid flex mt-4">
        <slot></slot>
    </div>
</div>

<style>
    .box-grid {
        gap: 1rem;
        height: 13rem;
        transition: display .1s ease-out;
    }
    .caret-text:hover {
        cursor: pointer;
    }
</style>