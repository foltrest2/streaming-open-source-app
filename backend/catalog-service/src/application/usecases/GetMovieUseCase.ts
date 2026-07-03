import { Movie } from "../../domain/model/Movie";
import { MovieProviderPort } from "../../domain/ports/MovieProviderPort";

export class GetMovieUseCase {

    constructor(private readonly movieProvider: MovieProviderPort) {}

    async execute(movieId: number): Promise<Movie> {
        return this.movieProvider.getMovie(movieId);
    }

}