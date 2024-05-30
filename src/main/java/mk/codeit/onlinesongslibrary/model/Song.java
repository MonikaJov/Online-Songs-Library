package mk.codeit.onlinesongslibrary.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import mk.codeit.onlinesongslibrary.model.enumerations.Genre;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Table(name = "song")
@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

//    @PositiveOrZero // Duration can't be a negative value
    @Column(name = "duration")
    private float durationInMin;

//    @PastOrPresent // What if the song is going to be released in the future?
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToMany
    @Column(name = "playlists")
    private List<Playlist> playlistsWithSong = new ArrayList<>();

    @ManyToMany
    @Column(name = "artists")
    private List<Artist> artistsOfSong = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Song() {}

    public Song(String title, float durationInMin, LocalDate releaseDate, Genre genre) {
        this.title = title;
        this.durationInMin = durationInMin;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    public Song(String title, LocalDate releaseDate, Genre genre) {
        this.title = title;
        this.durationInMin = 0;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

}

