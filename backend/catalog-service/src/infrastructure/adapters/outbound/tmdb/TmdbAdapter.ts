import axios from "axios";

import { Movie } from "../../../../domain/model/Movie";
import { MovieDetails } from "../../../../domain/model/MovieDetails";
import { MovieProviderPort } from "../../../../domain/ports/MovieProviderPort";

import { MovieDto } from "./dto/movie/MovieDto";
import { MovieDetailsDto } from "./dto/movie/MovieDetailsDto";
import { TmdbMapper } from "./TmdbMapper";

import { client } from "../../../config/AxiosConfig";

export class TmdbAdapter implements MovieProviderPort {

    private readonly baseUrl = process.env.TMDB_BASE_URL!;
    private readonly apiKey = process.env.TMDB_API_KEY!;

    async getMovie(movieId: number): Promise<Movie> {
        const response = await client.get<MovieDto>(
            `${this.baseUrl}/movie/${movieId}/videos`,
            {
                params: {
                    api_key: this.apiKey
                }
            }
        );

        return TmdbMapper.toMovie(response.data);
    }

    async getMovieDetails(movieId: number): Promise<MovieDetails> {
        const response = await client.get<MovieDetailsDto>(
            `${this.baseUrl}/movie/${movieId}`,
            {
                params: {
                    api_key: this.apiKey
                }
            }
        );

        return TmdbMapper.toMovieDetails(response.data);
    }

}