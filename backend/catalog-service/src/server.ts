import "dotenv/config";
import { app } from "./app";

app.listen(process.env.port, () => {
    console.log(`Catalog Service running on port ${process.env.port}`);
});

/*
import "dotenv/config";
import express from "express";

const app = express();

app.get("/health", (_req, res) => {
    res.json({ status: "UP" });
});

const PORT = Number(process.env.PORT) || 4000;

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});
*/