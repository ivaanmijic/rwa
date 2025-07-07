(function () {
  const ctx = window.ctxPath;

  window.goToPage = function (page) {
    console.log("Navigating to page", page);
    loadContent(page);
    // Update active button highlight
    document.querySelectorAll(".pagination button").forEach((btn) => {
      btn.classList.toggle("active", parseInt(btn.textContent) === page);
    });
  };

  async function fetchVideos(page) {
    console.log("Fetching videos for page", page);
    const resp = await fetch(`${ctx}/ranking-table?page=${page}&limit=20`, {
      cache: "no-cache",
    });
    if (!resp.ok) throw new Error(`Error ${resp.status}`);
    const ct = resp.headers.get("content-type") || "";
    if (!ct.includes("application/json")) {
      throw new Error(`Expected JSON, got ${ct}`);
    }
    return resp.json();
  }

  function render(videos, page) {
    let offset = (page - 1) * 20;

    const rows = videos
      .map(
        (v, idx) => `
      <div class="row">
        <div class="cell image">
          <img src="${v.CustomImageUrl != null && v.CustomImageUrl.trim() !== "" ? v.CustomImageUrl : v.thumbnailURL}" alt="${v.name}"/>
        </div>
        <div class="cell headline">${v.name}</div>
        <div class="cell votes">${v.votes}/${v.totalVotes}</div>
        <div class="cell rank">${offset + idx + 1}</div>
      </div>
    `,
      )
      .join("");

    document.getElementById("ranking-container").innerHTML = `
      <div class="table-container">
        <div class="row header">
          <div class="cell image"></div>
          <div class="cell headline">Headline</div>
          <div class="cell votes">Votes</div>
          <div class="cell rank">Rank</div>
        </div>
        ${rows}
      </div>
    `;
  }

  async function loadContent(page) {
    try {
      const data = await fetchVideos(page);
      render(data, page);
    } catch (e) {
      console.error(e);
      alert(`Failed to load page ${page}: ${e.message}`);
    }
  }

  document.addEventListener("DOMContentLoaded", () => {
    const initialPage =
      parseInt(new URLSearchParams(window.location.search).get("page")) || 1;
    console.log("Initial page loaded:", initialPage);
    loadContent(initialPage);
  });
})();
