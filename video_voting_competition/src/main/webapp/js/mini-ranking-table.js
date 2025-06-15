// document.addEventListener("DOMContentLoaded", function () {
//   const videosContainer = document.getElementById("top-five-container");
//
//   function render(videos) {
//     const titleH2 = document.createElement("h2");
//     titleH2.textContent = "Top 5 videos";
//
//     const tableContainer = document.createElement("div");
//     tableContainer.className = "table-container";
//
//     const rowHeader = document.createElement("div");
//     rowHeader.className = "row header";
//
//     const cellImage = document.createElement("div");
//     cellImage.className = "cell image";
//     rowHeader.appendChild(cellImage);
//
//     const cellHeadline = document.createElement("div");
//     cellHeadline.className = "cell headline";
//     cellHeadline.textContent = "Headline";
//     rowHeader.appendChild(cellHeadline);
//
//     const cellVotes = document.createElement("div");
//     cellVotes.className = "cell votes";
//     cellVotes.textContent = "Votes";
//     rowHeader.appendChild(cellVotes);
//
//     const cellRank = document.createElement("div");
//     cellRank.className = "cell rank";
//     cellRank.textContent = "Rank";
//     rowHeader.appendChild(cellRank);
//
//     tableContainer.appendChild(rowHeader);
//
//     videos.forEach((video) => {
//       const row = document.createElement("div");
//       row.className = "row header";
//
//       const cellImage = document.createElement("div");
//       cellImage.className = "cell image";
//       const img = document.createElement("img");
//       img.src = video.thumbnailURL;
//       img.alt = video.name;
//       cellImage.appendChild(img);
//       row.appendChild(cellImage);
//
//       const cellHeadline = document.createElement("div");
//       cellHeadline.className = "cell headline";
//       cellHeadline.textContent = video.name;
//       row.appendChild(cellHeadline);
//
//       const cellVotes = document.createElement("div");
//       cellVotes.className = "cell votes";
//       cellVotes.textContent = `${video.votes}/${video.totalVotes}`;
//       row.appendChild(cellVotes);
//
//       const cellRank = document.createElement("div");
//       cellRank.className = "cell rank";
//       cellRank.textContent = "Rank";
//       row.appendChild(cellRank);
//
//       tableContainer.appendChild(row);
//     });
//
//     videosContainer.appendChild(titleH2);
//     videosContainer.appendChild(tableContainer);
//   }
//
//   let page = 1;
//
//   async function loadContent() {
//     try {
//       const videos = await fetchVideos(page);
//       render(videos);
//     } catch (err) {
//       showError(err.message);
//     }
//   }
//
//   loadContent();
// });

document.addEventListener("DOMContentLoaded", () => {
  const videosContainer = document.getElementById("top-five-container");
  let page = 1;

  async function loadContent() {
    try {
      const videos = await fetchVideos(page);
      render(videos);
    } catch (err) {
      showError(err.message);
    }
  }

  function render(videos) {
    videosContainer.innerHTML = `
      <h2>Top 5 videos</h2>
      <div class="table-container">
        <div class="row header">
          <div class="cell image"></div>
          <div class="cell headline">Headline</div>
          <div class="cell votes">Votes</div>
          <div class="cell rank">Rank</div>
        </div>
        ${videos
          .map(
            ({ thumbnailURL, name, votes, totalVotes }, idx) => `
          <div class="row">
            <div class="cell image">
              <img src="${thumbnailURL}" alt="${name}">
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

  loadContent();
});
