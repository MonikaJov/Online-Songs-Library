package mk.codeit.onlinesongslibrary.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import mk.codeit.onlinesongslibrary.model.Song;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ArtistDTO {

    private Long id;
    private String name;
    private String artisticName;
    private LocalDate dateOfBirth;
    private String nationality;
    private List<Song> songs;

    public ArtistDTO(Long id, @NonNull String name, String artisticName, LocalDate dateOfBirth, String nationality, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.artisticName = artisticName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.songs = songs;
    }
}
