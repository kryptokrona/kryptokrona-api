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
