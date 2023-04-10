import adapter from "@sveltejs/adapter-node";
import preprocess from "svelte-preprocess";

const config = {
  preprocess: preprocess({
    postcss: true,
    scss: {
      prependData: `@import 'src/app.scss';`,
    },
  }),
  onwarn: (warning, handler) => {
    const { code } = warning;
    if (
      code === "css-semicolonexpected" ||
      code === "css-ruleorselectorexpected" ||
      code === "css-unused-selector"
    )
      return;
    handler(warning);
  },
  kit: {
    paths: {
      base: '/monitor',
    },
    adapter: adapter(),
  },
};

export default config;
