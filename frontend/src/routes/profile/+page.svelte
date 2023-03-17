<script>
  import BlueButton from "../../components/buttons/BlueButton.svelte";
  import { user } from "../../stores/user";
  import { logout } from "../../api/login";
  import { goto } from "$app/navigation";

  function onLogOut() {
    logout();
    $user = {};
    goto("/login");
  }
</script>

<div class="text-center">
  <h2 class="mb-4">{$user.email}</h2>
  <BlueButton text="Log out" action={onLogOut} />
</div>
