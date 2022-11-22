/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,ts}"],
  theme: {
    fontFamily: {
      // Add your custom fonts and enjoy.
      MedievalSharp: ["MedievalSharp", "cursive"],
    },
    extend: {},
  },
  plugins: [require("@tailwindcss/typography")],
};
