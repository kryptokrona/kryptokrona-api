import { writable } from "svelte/store";

export const nodes = writable([]);
export const cpuUsage = writable(0);
export const ramUsage = writable(0);
export const diskUsage = writable(0);
export const cpuUsageOverTime = writable({});
export const ramUsageOverTime = writable({});
export const diskUsageOverTime = writable({});
export const threads = writable(0);
export const posts = writable([]);
export const groupPosts = writable([]);
export const totalPosts = writable(0);
export const totalGroupPosts = writable(0);
