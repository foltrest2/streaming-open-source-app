import { TmdbAdapter } from "../adapters/outbound/tmdb/TmdbAdapter";
import { GetMovieUseCase } from "../../application/usecases/GetMovieUseCase";
import { GetMovieDetailsUseCase } from "../../application/usecases/GetMovieDetailsUseCase";
import { CatalogController } from "../adapters/inbound/controller/CatalogController";
import { HealthController } from "../adapters/inbound/controller/HealthController";

export const healthController = new HealthController();

const movieProvider = new TmdbAdapter();

const getMovieUseCase = new GetMovieUseCase(movieProvider);
const getMovieDetailsUseCase = new GetMovieDetailsUseCase(movieProvider);

export const catalogController = new CatalogController(
    getMovieUseCase,
    getMovieDetailsUseCase
);