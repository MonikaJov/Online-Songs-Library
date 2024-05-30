package mk.codeit.onlinesongslibrary.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import mk.codeit.onlinesongslibrary.model.enumerations.Status;

import java.time.LocalDate;

@Getter
@Setter
public class PlaylistDTO {
    private Long id;
    private String name;
    private Status status;
    private LocalDate dateOfCreation;

    public PlaylistDTO(Long id, @NonNull String name, Status status, LocalDate dateOfCreation) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.dateOfCreation = dateOfCreation;
    }
}

