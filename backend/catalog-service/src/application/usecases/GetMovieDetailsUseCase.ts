import { MovieDetails } from "../../domain/model/MovieDetails";
import { MovieProviderPort } from "../../domain/ports/MovieProviderPort";

export class GetMovieDetailsUseCase {

    constructor(private readonly movieProvider: MovieProviderPort) {}

    async execute(movieId: number): Promise<MovieDetails> {
        return this.movieProvider.getMovieDetails(movieId);
    }

}