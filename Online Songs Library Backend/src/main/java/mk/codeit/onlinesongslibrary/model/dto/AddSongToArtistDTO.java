package mk.codeit.onlinesongslibrary.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddSongToArtistDTO {

    private SongDTO songDTO;
    private Long artistId;

    public AddSongToArtistDTO(SongDTO songDTO, Long artistId) {
        this.songDTO =songDTO;
        this.artistId = artistId;
    }
}