import { GenreDto } from "./GenreDto";

export interface MovieDetailsDto {
    adult: boolean;
    backdropPath: string | null;
    belongsToCollection: unknown | null;
    budget: number;
    genres: GenreDto[];
    homepage: string;
    id: number;
    imdbId: string | null;
    originCountry: string[];
    originalLanguage: string;
    originalTitle: string;
    overview: string;
    popularity: number;
    posterPath: string | null;
    productionCompanies: unknown[];
    productionCountries: unknown[];
    releaseDate: string;
    revenue: number;
    runtime: number;
    spokenLanguages: unknown[];
    status: string;
    tagline: string;
    title: string;
    video: boolean;
    voteAverage: number;
    voteCount: number;
}