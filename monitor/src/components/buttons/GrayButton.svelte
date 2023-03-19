<script>
  export let text;
  export let action;
</script>

<button
  on:click={action}
  aria-label={text}
  class="tooltip-container
        text-center
        relative 
        bg-neutral-300
        hover:bg-neutral-400
        dark:bg-neutral-700
        dark:hover:bg-neutral-500
        rounded-md 
        p-1.5
        mr-1"
>
  {text}
</button>
