import { Movie } from "../model/Movie";
import { MovieDetails } from "../model/MovieDetails";

export interface MovieProviderPort {
    getMovie(movieId: number): Promise<Movie>;
    getMovieDetails(movieId: number): Promise<MovieDetails>;
}