document.addEventListener("DOMContentLoaded", function () {
  const container = document.getElementById("rand-videos-container");

  function loadAndRenderVideos() {
    fetch(`${window.ctxPath}/random-videos`)
      .then((resp) => {
        if (!resp.ok) {
          return resp.text().then((body) => {
            throw new Error(`Server ${resp.status}: ${body}`);
          });
        }
        return resp.json();
      })
      .then((videos) => {
        container.innerHTML = "";
        videos.forEach((video) => {
          const otherId = videos.find((v) => v.id !== video.id).id;
          renderVideo(video, otherId);
        });
      })
      .catch((err) => {
        showError(err.message);
      });
  }

  function renderVideo(video, otherVideoId) {
    const cardWrapper = document.createElement("div");
    cardWrapper.className = "video-wrapper";

    const card = document.createElement("div");
    card.className = "iframe-card";

    const h3 = document.createElement("h3");
    h3.textContent = video.name;
    card.appendChild(h3);

    const iframe = document.createElement("iframe");
    iframe.src = `${video.url}?autoplay=0`;
    iframe.title = video.name;
    iframe.setAttribute("frameborder", "0");
    iframe.setAttribute(
      "allow",
      "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture",
    );
    iframe.allowFullscreen = true;
    card.appendChild(iframe);

    cardWrapper.appendChild(card);

    const wrapper = document.createElement("div");
    wrapper.className = "button-wrapper";

    const btn = document.createElement("button");
    btn.type = "button";
    btn.textContent = "Vote";

    btn.addEventListener("click", () => {
      const params = new URLSearchParams({
        votedId: video.id,
        otherId: otherVideoId,
        vote: "true",
      });

      fetch(`${window.ctxPath}/vote?${params.toString()}`, {
        method: "POST",
      })
        .then((resp) => {
          if (!resp.ok) {
            return resp.text().then((body) => {
              throw new Error(`Server ${resp.status}: ${body}`);
            });
          }
          const ct = resp.headers.get("content-type") || "";
          if (!ct.includes("application/json")) {
            throw new Error(`Expected JSON, got ${ct}`);
          }
          return resp.json();
        })
        .then((videos) => {
          container.innerHTML = "";
          videos.forEach((v) => {
            const other = videos.find((x) => x.id !== v.id).id;
            renderVideo(v, other);
          });
        })
        .catch((err) => {
          showError(err.message);
        });
    });

    wrapper.appendChild(btn);
    cardWrapper.appendChild(wrapper);
    container.appendChild(cardWrapper);
  }

  loadAndRenderVideos();
});
