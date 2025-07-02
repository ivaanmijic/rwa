document.addEventListener("DOMContentLoaded", () => {
  const videosContainer = document.getElementById("top-five-container");
  let page = 0;
  videosContainer.innerHTML = `<h2>Top 5 videos</h2>`;
  loadContent(page, videosContainer);
});
