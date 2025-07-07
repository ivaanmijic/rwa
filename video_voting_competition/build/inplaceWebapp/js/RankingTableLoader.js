async function fetchVideos(page, limit) {
  const params = new URLSearchParams({
    page: `${page}`,
    limit: `${limit}`,
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
  videos.map((video, idx) => {
    console.log(video);
  });

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
            (video, idx) => `
    <div class="row">
      <div class="cell image">
        <img src="${video.CustomImageUrl != null && video.CustomImageUrl.trim() !== "" ? video.CustomImageUrl : video.thumbnailURL}" alt="${video.name}">
      </div>
      <div class="cell headline">${video.name}</div>
      <div class="cell votes">${video.votes}/${video.totalVotes}</div>
      <div class="cell rank">${idx + 1}</div>
    </div>
  `,
          )
          .join("")}
      </div>
    `;
}

async function loadContent(page, limit, container) {
  try {
    const videos = await fetchVideos(page, limit);
    render(videos, container);
  } catch (err) {
    showError(err.message);
  }
}
