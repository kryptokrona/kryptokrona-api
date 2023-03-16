<script>
  export let iconClass = "fa-solid fa-magnifying-glass";
  export let label = "label";
  export let goTo = "";
</script>

<a
  href={goTo}
  aria-label={label}
  class="tooltip-container
        text-center
        relative 
        bg-neutral-400
        text-neutral-900
        hover:bg-neutral-500
        dark:bg-neutral-700
        dark:text-neutral-100 
        dark:hover:bg-neutral-500
        rounded-md 
        p-1.5
        mr-1"
>
  <i class={iconClass + " w-5 h-5"} aria-hidden="true" />
  <span
    class="tooltip 
        bg-neutral-100
        text-neutral-900
        border-neutral-900
        dark:border-white
        border
        dark:bg-neutral-900 
        dark:text-neutral-100
          rounded-md 
          p-1 
          flex 
          justify-center 
          items-center"
    >{label}
  </span>
</a>

<style>
  .tooltip-container:hover .tooltip {
    visibility: visible;
  }
  .tooltip {
    width: 5rem;
    text-align: center;
    visibility: hidden;
    position: absolute;
    top: 3rem;
    left: -1.5rem;
    right: 0;
    margin-left: auto;
    margin-right: auto;
  }

  /*
.tooltip .tooltiptext::after {
  content: " ";
  position: absolute;
  bottom: 100%;  
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: transparent transparent black transparent;
  }
  */
</style>
