package mk.codeit.onlinesongslibrary.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import mk.codeit.onlinesongslibrary.model.enumerations.Status;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Table(name = "playlist")
@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

//    @CreatedDate // Need to configure Spring Data JPA properly to make it work
    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;

    @ManyToMany
    @Column(name = "songs")
    private List<Song> songsInPlaylist = new ArrayList<>();

    public Playlist() {}

    public Playlist(String name, Status status, LocalDate dateOfCreation) {
        this.name = name;
        this.status = status;
        this.dateOfCreation = dateOfCreation;
    }

    public Playlist(String name, LocalDate dateOfCreation) { // If status is not provided, automatically set to PRIVATE
        this.name = name;
        this.status = Status.PRIVATE;
        this.dateOfCreation = dateOfCreation;
    }
}
