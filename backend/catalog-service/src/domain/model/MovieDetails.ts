import { Genre } from "./Genre";

export interface MovieDetails {
    adult: boolean;
    backdropPath: string | null;
    genres: Genre[];
    homepage: string;
    id: number;
    originalLanguage: string;
    overview: string;
    popularity: number;
    posterPath: string | null;
    releaseDate: string;
    runtime: number;
    title: string;
    voteAverage: number;
    voteCount: number;
}