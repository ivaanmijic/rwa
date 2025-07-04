document.addEventListener("DOMContentLoaded", () => {
  const videosContainer = document.getElementById("top-five-container");
  let page = 1;
  let limit = 5;
  console.log("tu sam");
  videosContainer.innerHTML = `<h2>Top 5 videos</h2>`;
  loadContent(page, limit, videosContainer);
});
