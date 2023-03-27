<script>
  import BlueButton from "../../components/buttons/BlueButton.svelte";
  import GreenButton from "../../components/buttons/GreenButton.svelte";
  import { login } from "../../api/login";
  import { goto } from "$app/navigation";
  import { user } from "../../stores/user";
  let email;

  async function onLogin() {
    $user = await login(email);
    console.log("fsfdsf" + $user);
    //goto("/");
  }
</script>

<div class="flex justify-center">
  <div class="w-full max-w-sm">
    <h2 class="mb-8">Login</h2>
    <div class="flex flex-col">
      <label class=" mb-1" for="email">email</label>
      <input
        class="rounded-md p-1 border border-neutral-700 dark:border-neutral-100 dark:border-none dark:text-neutral-900"
        type="email"
        name="email"
        bind:value={email}
      />
    </div>

    <div class="flex flex-col mt-4 mb-4">
      <label class=" mb-1" for="pw">password</label>
      <input
        class="rounded-md p-1 border border-neutral-700 dark:border-neutral-100 dark:border-none dark:text-neutral-900"
        type="password"
        name="pw"
      />
    </div>

    <GreenButton text="Login" action={onLogin} />

    <h2 class="mt-20 mb-4">Don't have an account? Create one for free</h2>
    <BlueButton text="Register" />
  </div>
</div>
