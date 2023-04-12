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
  try {
    const response = await fetch("http://localhost:8080/api/v1/info/node", {
      method: "POST",
      body: JSON.stringify({
        hostName: name,
        port: port,
        ssl: false,
      }),
    });
  } catch (error) {
    console.error("failed fetching nodes " + error);
  }
  return {};
}
