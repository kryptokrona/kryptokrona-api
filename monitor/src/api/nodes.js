export async function getNodes() {
  try {
    const response = await fetch("https://blocksum.org/api/v1/nodes", {});
    let data = await response.json();
    return data.nodes;
  } catch (error) {
    console.error("failed fetching nodes " + error);
  }
  return [];
}

export async function getNode(name, port) {
  /*
  console.log(name, port);
  try {
    const response = await fetch(
      "https://stage.xkr.mjovanc.com/api/v1/info/node",
      {
        method: "POST",
        body: JSON.stringify({
          hostName: name,
          port: port,
          ssl: false,
        }),
      }
    );
    console.log(response);
    let data = await response.json();
    console.log(data);
  } catch (error) {
    console.error("failed fetching nodes " + error);
  }*/
  return {};
}
