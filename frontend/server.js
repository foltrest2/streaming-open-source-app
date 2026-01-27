import express from "express";
import cors from "cors";
import fetch from "node-fetch";

const app = express();
const PORT = process.env.PORT || 3000;

app.use(express.json());
app.use(express.static("/app/public"));

app.post("/api/auth/login", async (req, res) => {
  const { username, password } = req.body;

  try {
    // AQUÍ es donde haces la petición pesada y secreta a Keycloak
    const keycloakResponse = await fetch("http://localhost:8082/realms/streaming-app/protocol/openid-connect/token", {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: new URLSearchParams({
        client_id: "streaming-web",
        // client_secret: "TU_SECRETO_AQUÍ", <-- ¡Aquí puedes poner el secreto sin que nadie lo vea!
        grant_type: "password",
        username: username,
        password: password
      })
    });

    const tokens = await keycloakResponse.json();

    if (keycloakResponse.ok) {
      // OPCIÓN SEGURA: Guardar el token en una Cookie HttpOnly
      // res.cookie('token', tokens.access_token, { httpOnly: true, secure: true });

      res.status(200).json({ success: true, user: { name: username } });
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
