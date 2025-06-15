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
