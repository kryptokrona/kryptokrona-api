<script>
  import BlueButton from "../../components/buttons/BlueButton.svelte";
  import { user } from "../../stores/user";
</script>

<div class="text-center">
  <h2 class="mb-4">{$user.username}</h2>
  <BlueButton text="Log out" />
</div>
