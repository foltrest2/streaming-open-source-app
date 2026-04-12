import express from "express";
import cors from "cors";
import fetch from "node-fetch";
import cookieParser from "cookie-parser";

const app = express();
const PORT = process.env.PORT || 3000;

app.use(express.json());
app.use(express.static("/app/public"));
app.use(cookieParser());

app.post("/api/auth/login", async (req, res) => {
  const { username, password } = req.body;

  try {

    validateCredentials(username, password);

    const sanitizedUsername = sanitizeInput(username);

    // !client_secret: "TU_SECRETO_AQUÍ", <-- ¡Aquí puedes poner el secreto sin que nadie lo vea!
    const keycloakResponse = await fetch("http://keycloak:8080/realms/streaming-app/protocol/openid-connect/token", {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: new URLSearchParams({
        client_id: "streaming-web",
        grant_type: "password",
        username: sanitizedUsername,
        password: password
      })
    });

    const tokens = await keycloakResponse.json();
    // ! Revisar el funcionamiento de las cookies HttpOnly
    if (keycloakResponse.ok) {
      res.cookie('token', tokens.access_token, { 
        httpOnly: true, 
        secure: true,
        sameSite: 'Lax', 
        maxAge: 60 * 60 * 1000 // 1 hora
      });

      res.cookie('refresh_token', tokens.refresh_token, { 
        httpOnly: true, 
        secure: true,
        sameSite: 'Lax', 
        maxAge: tokens.refresh_expires_in * 1000
      });

      res.status(200).json({ 
        success: true, 
        user: { name: username }
      });
    } else {
      res.status(401).json({ success: false, message: "Credenciales inválidas" });
    }
  } catch (error) {
    res.status(500).json({ success: false, message: "Error interno del servidor" });
  }
});

app.get("/api/health/all", async (req, res) => {
  try {
    const response = await fetch("http://gateway:8080/health/all");
    const data = await response.json();
    res.json(data);
  } catch (err) {
    res.status(500).json({ error: "Error al conectar con gateway", detail: err.message });
  }
});

const allowedOrigins = [
  "http://localhost:3000",    // para desarrollo
  "https://tu-dominio.com"    // para producción
];

app.use(cors({
  origin: function (origin, callback) {
    if (!origin || allowedOrigins.includes(origin)) {
      callback(null, true);
    } else {
      callback(new Error("CORS no permitido desde este origen"));
    }
  },
}));

// Endpoint de prueba (opcional)
app.get("/health", (req, res) => {
  res.json({ message: "Frontend Node.js funcionando correctamente 🚀" });
});

app.listen(PORT, () => {
  console.log(`Frontend corriendo en http://localhost:${PORT}`);
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