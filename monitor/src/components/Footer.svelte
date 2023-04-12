<script>
    import {base} from "$app/paths";
</script>

<footer
  class="border-t border-neutral-700 dark:border-neutral-50 flex justify-center mt-16"
>
  <div class="flex flex-row p-4" style="font-size: clamp(10px,2.5vw,16px)">
    <p class="mr-4">Powered by:</p>
    <img
      src={`${base}/svelte_logo.png`}
      alt="svelte logo"
      class="h-4 md:h-6 w-auto object-contain"
    />
    <p class="ml-1 mr-4">svelte</p>
    <img
      src={`${base}/kotlin_logo.png`}
      alt="kotlin logo"
      class="h-4 md:h-6 w-auto object-contain"
    />
    <p class="ml-1 mr-4">kotlin</p>
    <img
      src={`${base}/ktor_logo.png`}
      alt="ktor logo"
      class="h-4 md:h-6 w-auto object-contain"
    />
    <p class="ml-1">ktor</p>
  </div>
</footer>
