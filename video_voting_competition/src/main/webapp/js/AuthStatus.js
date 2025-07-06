document.addEventListener("DOMContentLoaded", function () {
  async function fetchAuthStatus() {
    try {
      const response = await fetch(`${window.ctxPath}/auth-status`);
      if (!response.ok) {
        console.error(`HTTP error! status: ${response.status}`);
        return false;
      }

      const data = await response.json();
      return data.isLoggedIn;
    } catch (err) {
      console.error("Error fetchin login status: ", err);
      return false;
    }
  }

  function updateHeaderForUser(isLoggedIn) {
    let container = document.getElementById("status-container");

    if (!container) {
      console.warn(
        "Element with ID 'status-container' not found. Cannot display login/logout button",
      );
      return;
    }

    container.innerHTML = "";

    let a = document.createElement("a");
    a.classList.add("ranking-btn");

    if (!isLoggedIn) {
      a.textContent = "Login";
      a.href = `${window.ctxPath}/login`;
    } else {
      a.textContent = "Logout";
      a.href = "{pageContext.request.contextPath}/logout";
    }

    container.appendChild(a);
  }

  fetchAuthStatus().then((isLoggedIn) => {
    updateHeaderForUser(isLoggedIn);
  });
});
