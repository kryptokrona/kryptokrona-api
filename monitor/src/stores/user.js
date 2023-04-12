import {writable} from "svelte/store";
import {browser} from '$app/environment';

export const user = writable({});

if(browser){   
    const content = localStorage.getItem('user');
    if(content){
        user.set(JSON.parse(content));
    }
}

export function setUser(username, email) {
    user.set({username, email});
    localStorage.setItem('user', JSON.stringify(user));
}

export function clearUser() {
    user.set({});
}
