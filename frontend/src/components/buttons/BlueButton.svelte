<script>
  export let text;
  export let action;
</script>

<button
  class="bg-blue-700 py-1.5 px-2.5 rounded-md hover:bg-blue-600"
  on:click={action}>{text}</button
>
