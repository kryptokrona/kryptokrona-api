<script>
  import { COLOR } from "../helpers/colors";
  export let percentage = 50;
  export let color = COLOR.BLUE;
</script>

<div
  class="w-full h-full rounded-xl bg-neutral-200 dark:bg-neutral-800 relative"
>
  <div
    class="h-full rounded-xl"
    style={"width:" + percentage + "%; " + "background-color:" + color}
  />
</div>
