import express from "express";

import catalogRouter from "./infrastructure/adapters/inbound/routers/catalog.routes";
import healthRouter from "./infrastructure/adapters/inbound/routers/health.routes";

const app = express();

app.use(express.json());

app.use("/health", healthRouter);
app.use("/catalog", catalogRouter);

export { app };