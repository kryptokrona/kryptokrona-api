
<script>
    export let title = "title";
    export let text = "text";
    export let width = "xl:w-60" // important: Tailwind size
</script>

<div class={width + " w-full bg-green-700 h-64"}>
    <div class="h-2/5 text-center">
        <p>{title}</p>
    </div>
    <div class="h-1/5 text-center">
        <h1>{text}</h1>
    </div>
    <div class="h-2/5"></div>
</div>