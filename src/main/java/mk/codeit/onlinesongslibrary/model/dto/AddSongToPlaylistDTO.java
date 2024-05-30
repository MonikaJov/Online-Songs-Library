package mk.codeit.onlinesongslibrary.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddSongToPlaylistDTO {
    private SongDTO songDTO;
    private Long playlistId;

    public AddSongToPlaylistDTO(SongDTO songDTO, Long playlistId) {
        this.songDTO =songDTO;
        this.playlistId = playlistId;
    }
}