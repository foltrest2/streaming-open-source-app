import { MovieDetailsDto } from "./dto/movie/MovieDetailsDto";
import { MovieDto } from "./dto/movie/MovieDto";
import { MovieDetails } from "../../../../domain/model/MovieDetails";
import { Movie } from "../../../../domain/model/Movie";

export class TmdbMapper {

    public static toMovieDetails(dto: MovieDetailsDto): MovieDetails {
        return {
            id: dto.id,
            title: dto.title,
            overview: dto.overview,
            posterPath: dto.posterPath,
            backdropPath: dto.backdropPath,
            genres: dto.genres.map(genre => ({
                id: genre.id,
                name: genre.name
            })),
            releaseDate: dto.releaseDate,
            runtime: dto.runtime,
            originalLanguage: dto.originalLanguage,
            popularity: dto.popularity,
            voteAverage: dto.voteAverage,
            voteCount: dto.voteCount,
            adult: dto.adult,
            homepage: dto.homepage
        };
    }

    public static toMovie(dto: MovieDto): Movie {
        return {
            id: dto.id,
            videos: dto.results.map(video => ({
                name: video.name,
                key: video.key,
                site: video.site,
                size: video.size,
                type: video.type,
                official: video.official,
                publishedAt: video.publishedAt,
                id: video.id,
            }))
        };
    }

}