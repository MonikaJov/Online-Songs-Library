package mk.codeit.onlinesongslibrary.repository;

import mk.codeit.onlinesongslibrary.model.Artist;
import mk.codeit.onlinesongslibrary.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByDurationInMinBetween(float x, float y);
    List<Song> findByArtistsOfSong(Artist artist);
}

