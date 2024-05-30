package mk.codeit.onlinesongslibrary.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Table(name = "artist")
@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @Column(name = "artistic_name")
    private String artisticName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String nationality;

    @ManyToMany
    private List<Song> songs = new ArrayList<>();

    public Artist() { }

    public Artist(String name, String artisticName, LocalDate dateOfBirth, String nationality) {
        this.name = name;
        this.artisticName = artisticName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }

}
