import express from "express";
import path from "path";
import { fileURLToPath } from "url";

const app = express();
const PORT = process.env.PORT || 3000;

// Config para rutas relativas en ES Modules
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// Servir archivos estáticos
app.use(express.static(path.join(__dirname, "public")));

// Endpoint de prueba (opcional)
app.get("/ping", (req, res) => {
  res.json({ message: "Frontend Node.js funcionando correctamente 🚀" });
});

app.listen(PORT, () => {
  console.log(`Frontend corriendo en http://localhost:${PORT}`);
});
