import { Router } from "express";
import { catalogController } from "../../../config/dependencies";

const router = Router();

router.get("/movies/:id", catalogController.getMovie);
router.get("/movies/:id/details", catalogController.getMovieDetails);

export default router;