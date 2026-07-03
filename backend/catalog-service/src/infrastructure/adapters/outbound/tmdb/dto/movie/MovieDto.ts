import { VideoDto } from "./VideoDto";

export interface MovieDto {
    id: string;
    results: VideoDto[];
}