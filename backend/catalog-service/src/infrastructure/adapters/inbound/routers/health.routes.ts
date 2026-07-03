import { Router } from "express";
import { healthController } from "../../../config/dependencies";

const router = Router();

router.get("/", healthController.health.bind(healthController));

export default router;