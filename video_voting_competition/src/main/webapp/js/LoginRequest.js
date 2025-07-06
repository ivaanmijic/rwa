document.addEventListener("DOMContentLoaded", function () {
  const loginForm = document.getElementById("loginForm");

  console.log("tu sam");

  if (loginForm instanceof HTMLFormElement) {
    loginForm.addEventListener("submit", async function (e) {
      e.preventDefault();

      const formData = new FormData(loginForm);
      const payload = {
        username: formData.get("username"),
        password: formData.get("password"),
        rememberMe: formData.get("rememberMe"),
      };

      try {
        const res = await fetch(`${window.ctxPath}/login`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(payload),
        });

        console.log(`${window.ctxPath}/login`);

        if (!res.ok) {
          throw new Error(`Login error: ${res.status}`);
        }
      } catch (err) {
        console.error("Login error: ", err);
        alert("Login failed. Please check your credentials.");
      }
    });
  } else {
    console.log("tu sam else");
  }
});
