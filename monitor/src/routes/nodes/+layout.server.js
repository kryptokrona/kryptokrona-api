export const ssr = true;

export const load = async () => {
  return { repo: fetchRepo() /* huginStats: fetchPosts() */ };
};
