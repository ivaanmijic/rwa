document.addEventListener("DOMContentLoaded", () => {
  let usersContainer = document.getElementById("users-container");
  if (usersContainer) {
    loadUsers(usersContainer);
  }
});

async function loadUsers(container) {
  try {
    const users = await fetchUsers();
    render(users, container);
  } catch (err) {
    showError(err.message);
  }
}

async function fetchUsers() {
  const resp = await fetch(`${window.ctxPath}/admin/users`);

  if (!resp.ok) {
    const body = await resp.text();
    throw new Error(`Server ${resp.status}: ${body}`);
  }

  const ct = resp.headers.get("content-type") || "";
  if (!ct.includes("application/json")) {
    throw new Error(`Expected JSON, got ${ct}`);
  }

  const users = await resp.json();
  return users;
}

function render(users, element) {
  element.innerHTML = `
<div class="table-container">
        <div class="row header">
          <div class="cell headline">First Name</div>
          <div class="cell votes">Last Name</div>
          <div class="cell rank">Roles</div>
          <div class="cell rank"></div>
        </div>
${users
  .map(
    (user, idx) => `
    <div class="row">
      <div class="cell headline">${user.firstname}</div>
      <div class="cell votes">${user.lastname}</div>
      <div class="cell rank">${user.roles}</div>
      <div class="cell rank">
            <button class="delete-btn" id="deleteBtn" data-id="${user.id}">Delete</button>
          </div>
    </div>
  `,
  )
  .join("")}
      </div>

`;

  document.querySelectorAll("#deleteBtn").forEach((btn) => {
    btn.addEventListener("click", async (e) => {
      if (btn instanceof HTMLElement) {
        const userId = e.target.dataset.id;
        if (confirm("Are you sure you want to delete this user?")) {
          try {
            await deleteUser(userId);
            loadUsers(element);
          } catch (err) {
            showError(err.message);
          }
        }
      }
    });
  });
}

async function deleteUser(userId) {
  await fetch(`${window.ctxPath}/admin/users?id=${userId}`, {
    method: "DELETE",
  });
}
