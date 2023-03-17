import { formatDate } from "../helpers/helpers";

const endpoint = "https://api.github.com/repos/kryptokrona/kryptokrona-api";

export async function getRepoStats() {
    return {};
    try {
        const response = await fetch(endpoint);
        const data = await response.json();
        console.log(data);
        let stars = data.stargazers_count;
        let commits = await getCommits();
        let contributors = await getContributors();
        let latestCommit = { path : "c551a23c58b1ad8af87f886e5206ca8ff60a16b8", date: formatDate(new Date(commits[commits.length - 1].commit.author.date))};
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
        console.log()
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