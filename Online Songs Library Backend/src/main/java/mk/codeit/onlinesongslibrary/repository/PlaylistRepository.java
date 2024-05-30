package mk.codeit.onlinesongslibrary.repository;

import mk.codeit.onlinesongslibrary.model.Playlist;
import mk.codeit.onlinesongslibrary.model.Song;
import mk.codeit.onlinesongslibrary.model.enumerations.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findBySongsInPlaylist(List<Song> songs);
    List<Playlist> findByStatusEquals(Status status);
    Playlist findBySongsInPlaylistContaining (Song song);
}
