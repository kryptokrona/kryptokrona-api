const endpoint = "http://localhost:8080/v1/statistics";

export async function getPosts(timeInterval) {
    let response = await fetch(endpoint + "/post-encrypted/" + timeInterval);
    let json = await response.json();
    console.log(json);
}