const VITE_KRYPTOKRONA_API = "https://xkr.mjovanc.com/api/v1/statistics";
let endpoint = VITE_KRYPTOKRONA_API;

export async function getPosts(timeInterval) {
  let page = 1;
  let array = [];
  let times = [];
  let values = [];
  let data;
  try {
    do {
      let response = await fetch(
        endpoint + "/post-encrypted/" + timeInterval + "?page=" + page
      );
      data = await response.json();
      array = array.concat(data.items);
      page++;
    } while (page * data.size < data.total);

    let dict = {};

    for (let i = 0; i < array.length; i++) {
      let date = new Date(array[i].createdAt).toLocaleString();
      if (dict[date]) dict[date] += 1;
      else dict[date] = 1;
    }

    times = Object.keys(dict);
    values = Object.values(dict);
  } catch (error) {
    console.error(error);
  }
  return { values, times, total: array.length };
}
export async function getGroupPosts(timeInterval) {
  let page = 1;
  let array = [];
  let times = [];
  let values = [];
  let data;
  try {
    do {
      let response = await fetch(
        endpoint + "/post-encrypted-group/" + timeInterval + "?page=" + page
      );
      data = await response.json();
      array = array.concat(data.items);
      page++;
    } while (page * data.size < data.total);

    let dict = {};

    for (let i = 0; i < array.length; i++) {
      let date = new Date(array[i].createdAt).toLocaleString();
      if (dict[date]) dict[date] += 1;
      else dict[date] = 1;
    }

    times = Object.keys(dict);
    values = Object.values(dict);
  } catch (error) {
    console.error(error);
  }
  return { values, times, total: array.length };
}

export async function getTotalPostsThisYear() {
  let count = 0;
  try {
    let response = await fetch(endpoint + "/post-encrypted/1y");
    let data = await response.json();
    count = data.total;
  } catch (error) {
    console.error;
  }
  return count;
}
export async function getTotalGroupPostsThisYear() {
  let count = 0;
  try {
    let response = await fetch(endpoint + "/post-encrypted-group/1y");
    let data = await response.json();
    count = data.total;
  } catch (error) {
    console.error;
  }
  return count;
}
