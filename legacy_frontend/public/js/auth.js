const loginSection = document.getElementById("login-section");
const panelSection = document.getElementById("panel-section");
const loginBtn = document.getElementById("login-btn");
const logoutBtn = document.getElementById("logout-btn");
const usernameSpan = document.getElementById("username");

const keycloak = new Keycloak({
  url: "http://localhost:8082",
  realm: "streaming-app",
  clientId: "streaming-web",
});

async function loginWithCredentials(username, password) {
  try {

    const response = await fetch("/api/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ username: username, password: password })
    });
    

    if (!response.ok) {
      throw new Error("Credenciales inválidas");
    }

    const data = await response.json();

    if (data.success) {
      showPanel();
    }
    
  } catch (error) {
    console.error("Error de login:", error);
    alert("Error en el login. Intenta de nuevo.");
  }
}

async function initAuth() {
  const authenticated = await keycloak.init({
    onLoad: "check-sso",
    checkLoginIframe: false,
    redirectUri: window.location.origin
  });

  if (authenticated) {
    showPanel();
  } else {
    showLogin();
  }
}

function showLogin() {
  loginSection.style.display = "block";
  panelSection.style.display = "none";
  loginBtn.onclick = () => {
    const username = document.getElementById("username-input").value;
    const password = document.getElementById("password-input").value;
    loginWithCredentials(username, password);
  }
}

function showPanel() {
  loginSection.style.display = "none";
  panelSection.style.display = "block";

  if (keycloak.tokenParsed) {
    usernameSpan.textContent = keycloak.tokenParsed.preferred_username || "Usuario";
  }

  logoutBtn.onclick = () => {
    keycloak.logout();
    sessionStorage.removeItem("auth_token");
    localStorage.removeItem("auth_token");
    window.auth = null;
    showLogin();
  };

  window.auth = {
    token: keycloak.token,
    keycloak,
  };
}

initAuth();
