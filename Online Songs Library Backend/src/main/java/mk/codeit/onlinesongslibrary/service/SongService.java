package mk.codeit.onlinesongslibrary.service;

import mk.codeit.onlinesongslibrary.model.Artist;
import mk.codeit.onlinesongslibrary.model.Song;
import mk.codeit.onlinesongslibrary.model.dto.AddSongToArtistDTO;
import mk.codeit.onlinesongslibrary.model.dto.AddSongToPlaylistDTO;

import java.util.List;
import java.util.Optional;

public interface SongService {
    //TODO: Add a new song for the specified artist.
    Artist addNewSongToArtist(AddSongToArtistDTO addSongToArtistDTO) throws Exception;
    //TODO: Get the song with the longest duration from a specified artist of a certain genre.
    Optional<Song> getLongestSongByArtistAndGenre(Long artistId, String genre);
    //TODO: Calculate total duration of all the songs for the specified playlist.
    double calculateTotalDurationOfPlaylist(Long playlistId);
    //TODO: Add an existing song to a specified playlist.
    Optional<Song> addSongToPlaylist(AddSongToPlaylistDTO addSongToPlaylistDTO) throws Exception;
    //TODO: Get first 3 songs with duration between 5 and 10 minutes and sort by duration time in descending order
    List<Song> getTop3SongsByDurationBetween5And10Min();
}