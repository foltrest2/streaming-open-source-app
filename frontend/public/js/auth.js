const loginSection = document.getElementById("login-section");
const panelSection = document.getElementById("panel-section");
const loginBtn = document.getElementById("loginBtn");
const logoutBtn = document.getElementById("logoutBtn");
const usernameSpan = document.getElementById("username");

// auth.js (NO module)
const keycloak = new Keycloak({
  url: "http://localhost:8082",
  realm: "streaming-app",
  clientId: "streaming-web",
});

async function initAuth() {
  const authenticated = await keycloak.init({
    onLoad: "login-required",
    checkLoginIframe: false,
    redirectUri: window.location.origin
  });

  if (authenticated) {
    // Solo acceder al token después de autenticar
    output.textContent = keycloak.token || "No token";
    showPanel();
  } else {
    showLogin();
  }
}

function showLogin() {
  loginSection.style.display = "block";
  panelSection.style.display = "none";
  loginBtn.onclick = () => keycloak.login();
}

function showPanel() {
  loginSection.style.display = "none";
  panelSection.style.display = "block";

  // Acceder al tokenParsed de forma segura
  if (keycloak.tokenParsed) {
    usernameSpan.textContent = keycloak.tokenParsed.preferred_username || "Usuario";
  }

  logoutBtn.onclick = () => {
    // No hagas console.log del objeto keycloak directamente
    // En su lugar, accede a propiedades específicas
    if (keycloak.tokenParsed) {
      console.log("Usuario logueado:", keycloak);
    }
    //keycloak.logout();
  };

  window.auth = {
    token: keycloak.token,
    keycloak,
  };
}

initAuth();
