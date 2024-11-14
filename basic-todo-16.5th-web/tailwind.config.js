/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        customBlue: "#034A9A",
        customYellow: "#FBAE17",
        customSky: "rgba(3, 74, 154, 0.1)",
      }
    },
  },
  plugins: [],
}

