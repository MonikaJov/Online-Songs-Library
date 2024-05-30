package mk.codeit.onlinesongslibrary.model.dto;

import lombok.Getter;

@Getter
public class ArtistNameDTO {
    private String name;
    private String artisticName;

    public ArtistNameDTO(String name, String artisticName) {
        this.name = name;
        this.artisticName = artisticName;
    }
}

