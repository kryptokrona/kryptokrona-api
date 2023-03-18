import { formatDate } from "../helpers/helpers";

const endpoint = "https://api.github.com/repos/kryptokrona/kryptokrona-api";
const repoPath = "https://github.com/kryptokrona/kryptokrona-api";
export async function getRepoStats() {
    try {
        const response = await fetch(endpoint);
        const data = await response.json();
        let stars = data.stargazers_count;
        let commits = await getCommits();
        let contributors = await getContributors();
        let latestCommit = { path : repoPath + "/commit/" + commits[commits.length - 1].sha, date: formatDate(new Date(commits[commits.length - 1].commit.author.date))};
        return {stars, version: 0.1, commitCount: commits.length, latestCommit, contributors };
    } catch (error) {
        console.error;
    }   
    return {};
}

async function getLatestVersion(){
    try {
        const response = await fetch(endpoint + "/releases")
        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
    return {};
}

async function getCommits() {
    try {
        const response = await fetch(endpoint + "/commits");
        const data = await response.json();
        return data;
    } catch(error) {
        console.error(error);
    }
  
    return [];  
}

async function getContributors() {
    try {
        const response = await fetch(endpoint + "/contributors");
        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
    return [];
}