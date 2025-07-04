document.addEventListener("DOMContentLoaded", () => {
  const btn = document.getElementById("share-btn");
  const menu = document.getElementById("share-menu");

  btn.addEventListener("click", (e) => {
    e.stopPropagation();

    const rect = btn.getBoundingClientRect();
    const scrollY = window.scrollY || window.pageYOffset;
    const scrollX = window.scrollX || window.pageXOffset;

    menu.style.top = rect.bottom + scrollY + "px";
    menu.style.left = rect.left + scrollX + "px";

    menu.style.display = menu.style.display === "block" ? "none" : "block";
  });

  document.addEventListener("click", () => {
    menu.style.display = "none";
  });
});
