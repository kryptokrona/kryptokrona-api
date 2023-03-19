import { writable } from "svelte/store";
import { browser} from '$app/environment';

export const user = writable({});

if(browser) {
    let stored = localStorage.getItem("user");
    if(stored) user.set(JSON.parse(stored));
   
}