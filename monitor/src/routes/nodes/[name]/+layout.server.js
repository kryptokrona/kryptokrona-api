export const ssr = true;
import { getNode } from "../../../api/nodes";

export const load = async ({ url }) => {
  const searchparams = new URLSearchParams(url.searchParams);
  let node = await getNode(searchparams.get("name"), searchparams.get("port"));
  return { node };
};
