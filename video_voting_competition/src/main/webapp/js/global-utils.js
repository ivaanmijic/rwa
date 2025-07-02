function showError(msg) {
  document.body.innerHTML = `<div class="error-message">Error: ${msg}</div>`;
}

async function fetchVideos(page) {
  const params = new URLSearchParams({
    page: `${page}`,
  });

  const resp = await fetch(
    `${window.ctxPath}/ranking-table?${params.toString()}`,
    {
      method: "GET",
    },
  );

  if (!resp.ok) {
    const body = await resp.text();
    throw new Error(`Server ${resp.status}: ${body}`);
  }

  const ct = resp.headers.get("content-type") || "";
  if (!ct.includes("application/json")) {
    throw new Error(`Expected JSON, got ${ct}`);
  }

  const videos = await resp.json();
  return videos;
}

function render(videos, element) {
  element.innerHTML += `
      <div class="table-container">
        <div class="row header">
          <div class="cell image"></div>
          <div class="cell headline">Headline</div>
          <div class="cell votes">Votes</div>
          <div class="cell rank">Rank</div>
        </div>
        ${videos
          .map(
            ({ youtubeId, name, votes, totalVotes }, idx) => `
    <div class="row">
      <div class="cell image">
        <img src="https://img.youtube.com/vi/${youtubeId}/hqdefault.jpg" alt="${name}">
      </div>
      <div class="cell headline">${name}</div>
      <div class="cell votes">${votes}/${totalVotes}</div>
      <div class="cell rank">${idx + 1}</div>
    </div>
  `,
          )
          .join("")}
      </div>
    `;
}

async function loadContent(page, container) {
  try {
    const videos = await fetchVideos(page);
    render(videos, container);
  } catch (err) {
    showError(err.message);
  }
}
