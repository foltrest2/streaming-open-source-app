import express from "express";
import cors from "cors";
/*
import path from "path";
import fs from "fs";
import { fileURLToPath } from "url";
*/
const app = express();
const PORT = process.env.PORT || 3000;
/*
app.use((req, res, next) => {
  console.log(`[REQ] ${req.method} ${req.url}`);
  next();
});
*/
// Config para rutas relativas en ES Modules
//const __filename = fileURLToPath(import.meta.url);
//const __dirname = path.dirname(__filename);
/*
app.get("/", function(req, res) {
  res.sendFile(path.join(__dirname, "public", "index.html"));
});*/

app.get("/api/health/all", async (req, res) => {
  try {
    const response = await fetch("http://gateway:8080/health/all");
    const data = await response.json();
    res.json(data);
  } catch (err) {
    res.status(500).json({ error: "Error al conectar con gateway", detail: err.message });
  }
});

// Servir archivos estáticos
app.use(express.static("/app/public"));

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
