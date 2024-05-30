package mk.codeit.onlinesongslibrary.service.impl;


import lombok.RequiredArgsConstructor;
import mk.codeit.onlinesongslibrary.model.Artist;
import mk.codeit.onlinesongslibrary.model.Playlist;
import mk.codeit.onlinesongslibrary.model.Song;
import mk.codeit.onlinesongslibrary.model.dto.PlaylistDTO;
import mk.codeit.onlinesongslibrary.model.enumerations.Status;
import mk.codeit.onlinesongslibrary.model.exceptions.ArtistNotFoundException;
import mk.codeit.onlinesongslibrary.model.exceptions.PlaylistNotFoundException;
import mk.codeit.onlinesongslibrary.model.mappers.PlaylistMapper;
import mk.codeit.onlinesongslibrary.repository.ArtistRepository;
import mk.codeit.onlinesongslibrary.repository.PlaylistRepository;
import mk.codeit.onlinesongslibrary.repository.SongRepository;
import mk.codeit.onlinesongslibrary.service.PlaylistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;

    private final ArtistRepository artistRepository;

    private final SongRepository songRepository;

    //Add a new playlist.
    @Transactional
    @Override
    public Optional<Playlist> addNewPlaylist(PlaylistDTO playlistDTO) {
        if (playlistDTO == null || playlistDTO.getName() == null || playlistDTO.getName().isBlank()) {
            throw new IllegalArgumentException("Invalid playlist data provided.");
        }

        Playlist playlist = PlaylistMapper.MapToViewModelDTO(playlistDTO);

        playlist = playlistRepository.save(playlist);

        return Optional.of(playlist);
    }

    //Get all playlists containing songs by a specified artist sorted first by Artist name then by Artist date of birth in ascending order
    @Override
    public List<PlaylistDTO> getPlaylistsByArtistSorted(Long artistId) {
        Optional<Artist> artistOptional = artistRepository.findById(artistId);
        if (artistOptional.isEmpty()) {
            throw new ArtistNotFoundException("Artist with id " + artistId + " not found.");
        }

        Artist artist = artistOptional.get();
        List<Song> songs = songRepository.findByArtistsOfSong(artist);
        List<Playlist> playlists = playlistRepository.findBySongsInPlaylist(songs);

        Comparator<Playlist> comparator = Comparator.comparing(playlist -> playlist.getSongsInPlaylist().get(0).getArtistsOfSong().get(0).getName()); //sort by name
        comparator = comparator.thenComparing(playlist -> playlist.getSongsInPlaylist().get(0).getArtistsOfSong().get(0).getDateOfBirth());//sort by date of birth
        playlists.sort(comparator);

        List<PlaylistDTO> playlistDTOs = PlaylistMapper.MapToListViewModel((List<Playlist>) comparator);

        return playlistDTOs;
    }

    //Get all the playlists which are public and contain a maximum of 3 songs.
    @Override
    public List<PlaylistDTO> getPublicPlaylistsWithMaxThreeSongs() {
        List<Playlist> publicPlaylists = playlistRepository.findByStatusEquals(Status.PUBLIC);

        List<Playlist> playlistsWithMaxThreeSongs = publicPlaylists.stream()
                .filter(playlist -> playlist.getSongsInPlaylist().size() <= 3)
                .collect(Collectors.toList());

        List<PlaylistDTO> playlistDTOs = PlaylistMapper.MapToListViewModel(playlistsWithMaxThreeSongs);

        return playlistDTOs;
    }

    //Delete specified playlist.
    @Override
    @Transactional
    public void deletePlaylist(Long playlistId){
        Optional<Playlist> playlistOptional = playlistRepository.findById(playlistId);

        if (playlistOptional.isEmpty()) {
            throw new PlaylistNotFoundException("Playlist with ID " + playlistId + " not found.");
        }

        Playlist playlist = playlistOptional.get();

        try {
            playlistRepository.delete(playlist);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting playlist with ID " + playlistId, e);
        }
    }
}
