import axios from 'axios';
import express from 'express';
import cors from 'cors';

const app = express();

app.get('/health', (req, res) => {
  res.send('Gateway OK');
});

const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});

const services = {
  users: 'http://users:8080/health',
  billing: 'http://billing:8080/health',
  catalog: 'http://catalog:4000/health',
  streaming: 'http://streaming:8080/health',
  recommendation: 'http://recommendation:5005/health'
};

app.get('/health/all', async (req, res) => {
  const results = {};

  await Promise.all(
    Object.entries(services).map(async ([name, url]) => {
      try {
        const response = await axios.get(url);
        results[name] = response.data.status || 'UNKNOWN';
      } catch (error) {
        results[name] = 'DOWN';
      }
    })
  );

  res.json(results);
});

// ✅ Solo permite el frontend que tú controles (por ejemplo el de React en el puerto 3000)
const allowedOrigins = ['http://localhost:3000', 'http://frontend:3000'];

app.use(cors({
  origin: function (origin, callback) {
    // Permitir sin restricción herramientas locales (como Postman o curl)
    if (!origin) return callback(null, true);

    if (allowedOrigins.includes(origin)) {
      return callback(null, true);
    } else {
      return callback(new Error('Not allowed by CORS'));
    }
  },
  methods: ['GET', 'POST', 'PUT', 'DELETE'],
  credentials: true // solo si manejas cookies o auth
}));

// --- Middleware de errores (debe ir al final) ---
app.use((err, req, res, next) => {
  console.error("Error capturado:", err.message);

  if (err.message === "Not allowed by CORS") {
    return res.status(403).json({ error: "CORS policy: Access denied" });
  }
  else {
    res.status(500).json({ error: "Internal Server Error" });
  }
});