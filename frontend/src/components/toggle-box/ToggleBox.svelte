<script>
    export let title;
    let show = true;
    let toggleBox;
    function toggleVisibillity() {
        toggleBox.classList.toggle("togglebox-hidden");
        show = !show; 
    };
</script>

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

<div bind:this={toggleBox} class="box-grid mt-4">
    <slot></slot>
</div>

<style>
    .box-grid {
        display: grid;
        grid-template-columns: repeat(5, auto);
        gap: 1rem;
        justify-content: flex-start;
        transition: display .1s ease-out;
    }
    .caret-text:hover {
        cursor: pointer;
    }
 
    @media (max-width: 1280px) { 
        .box-grid { 
            grid-template-columns: repeat(3, 32%);
        }
     }

     @media (max-width: 768px) { 
        .box-grid { 
            grid-template-columns: repeat(2, 49%);
        }
      }

     @media (max-width: 640px) { 
        .box-grid { 
            display: flex;
            flex-direction: column;
        }
      } 
</style>