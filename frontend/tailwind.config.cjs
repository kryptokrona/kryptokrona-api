/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js,svelte,ts}"],
  theme: {
    extend: {},
  },
  plugins: [],
  theme: {
    fontFamily: {
      roboto: ["Roboto Mono", "monospace", "sans-serif"],
    },
    extend: { 
      colors: {
      "beige-100": "#FFF8E7",
      "beige-200": "#F5E8CE",
      "beige-300": "#E8D4BA",
      "beige-400":"#CFB298"
    }
  }
  },
};
