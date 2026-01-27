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

// 1. VALIDAR INPUTS
function validateCredentials(username, password) {
  // Verificar que no estén vacíos
  if (!username || !password) {
    throw new Error("Usuario y contraseña requeridos");
  }

  // Verificar longitudes razonables
  if (username.length > 255 || password.length > 1000) {
    throw new Error("Credenciales inválidas");
  }

  // No permitir caracteres especiales peligrosos en username
  if (!/^[a-zA-Z0-9._@-]+$/.test(username)) {
    throw new Error("Usuario contiene caracteres inválidos");
  }

  return true;
}

// 2. SANITIZAR (prevenir inyección)
function sanitizeInput(input) {
  return input
    .trim()
    .replace(/[<>\"']/g, "") // Remover caracteres HTML peligrosos
    .substring(0, 255); // Limitar longitud
}

// 3. LOGIN SEGURO
async function loginWithCredentials(username, password) {
  try {
    validateCredentials(username, password);

    const sanitizedUsername = sanitizeInput(username);

    // ! Hay que cambiar el protocolo HTTP a HTTPS en producción !
    const response = await fetch("http://localhost:8082/realms/streaming-app/protocol/openid-connect/token", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      body: new URLSearchParams({
        client_id: "streaming-web",
        grant_type: "password",
        username: sanitizedUsername,
        password: password // Keycloak lo valida internamente
      })
    });

    if (!response.ok) {
      throw new Error("Credenciales inválidas");
    }

    const data = await response.json();

    if (data.access_token) {
      // IMPORTANTE: Guardar en sessionStorage (no localStorage)
      // sessionStorage se limpia al cerrar navegador
      sessionStorage.setItem("auth_token", data.access_token);

      window.auth = {
        token: data.access_token,
        refreshToken: data.refresh_token
      };

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
