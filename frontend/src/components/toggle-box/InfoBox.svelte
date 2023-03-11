
<script>
    export let title = "title";
    export let text = "text";
    export let width = "w-60";
</script>

<div class={width + " dark:bg-neutral-800 h-full relative"}>
    <div class="h-2/5 text-center">
        <p>{title}</p>
    </div>
    <div class="h-1/5 text-center">
        <h1>{text}</h1>
    </div>
    <div class="h-2/5">
        <slot></slot>
    </div>
</div>