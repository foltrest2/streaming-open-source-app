import { Request, Response } from "express";

import { GetMovieDetailsUseCase } from "../../../../application/usecases/GetMovieDetailsUseCase";
import { GetMovieUseCase } from "../../../../application/usecases/GetMovieUseCase";

export class CatalogController {

    constructor(
        private readonly getMovieUseCase: GetMovieUseCase,
        private readonly getMovieDetailsUseCase: GetMovieDetailsUseCase
    ) {}

    async getMovie(req: Request, res: Response) {
        const movie = await this.getMovieUseCase.execute(Number(req.params.id));
        res.json(movie);
    }

    async getMovieDetails(req: Request, res: Response) {
        const movie = await this.getMovieDetailsUseCase.execute(Number(req.params.id));
        res.json(movie);
    }

}