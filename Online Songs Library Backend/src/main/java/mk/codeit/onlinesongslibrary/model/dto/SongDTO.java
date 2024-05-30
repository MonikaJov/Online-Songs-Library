package mk.codeit.onlinesongslibrary.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import mk.codeit.onlinesongslibrary.model.enumerations.Genre;

import java.time.LocalDate;

@Getter
@Setter
public class SongDTO {
    private Long id;
    private String title;
    private float durationInMin;
    private LocalDate releaseDate;
    private Genre genre;

    public SongDTO(Long id, @NonNull String title, float durationInMin, LocalDate releaseDate, Genre genre) {
        this.id = id;
        this.title = title;
        this.durationInMin = durationInMin;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }


}
