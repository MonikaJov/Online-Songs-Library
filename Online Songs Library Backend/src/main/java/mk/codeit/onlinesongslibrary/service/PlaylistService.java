package mk.codeit.onlinesongslibrary.service;

import mk.codeit.onlinesongslibrary.model.Playlist;
import mk.codeit.onlinesongslibrary.model.dto.PlaylistDTO;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {
    //TODO: Add a new playlist.
    Optional<Playlist> addNewPlaylist(PlaylistDTO playlistDTO) throws Exception;
    //TODO: Get all playlists containing songs by a specified artist sorted first by Artist name then by Artist date of birth in ascending order
    List<PlaylistDTO> getPlaylistsByArtistSorted(Long artistId);
    //TODO: Get all the playlists which are public and contain a maximum of 3 songs.
    List<PlaylistDTO> getPublicPlaylistsWithMaxThreeSongs();
    //TODO: Delete specified playlist.
    void deletePlaylist(Long playlistId) throws Exception;
}