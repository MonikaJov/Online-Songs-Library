package mk.codeit.onlinesongslibrary.service.impl;

import lombok.RequiredArgsConstructor;
import mk.codeit.onlinesongslibrary.model.Artist;
import mk.codeit.onlinesongslibrary.model.Playlist;
import mk.codeit.onlinesongslibrary.model.Song;
import mk.codeit.onlinesongslibrary.model.dto.AddSongToArtistDTO;
import mk.codeit.onlinesongslibrary.model.dto.AddSongToPlaylistDTO;
import mk.codeit.onlinesongslibrary.model.dto.SongDTO;
import mk.codeit.onlinesongslibrary.model.exceptions.ArtistNotFoundException;
import mk.codeit.onlinesongslibrary.model.exceptions.PlaylistNotFoundException;
import mk.codeit.onlinesongslibrary.model.mappers.SongMapper;
import mk.codeit.onlinesongslibrary.repository.ArtistRepository;
import mk.codeit.onlinesongslibrary.repository.PlaylistRepository;
import mk.codeit.onlinesongslibrary.repository.SongRepository;
import mk.codeit.onlinesongslibrary.service.SongService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final ArtistRepository artistRepository;

    private final SongRepository songRepository;

    private final PlaylistRepository playlistRepository;

    // Add a new song for the specified artist.
    @Transactional
    @Override
    public Artist addNewSongToArtist(AddSongToArtistDTO addSongToArtistDTO){
        Optional<Artist> artistOpt = artistRepository.findById(addSongToArtistDTO.getArtistId());

        if (artistOpt.isEmpty()) {
            throw new ArtistNotFoundException("Artist with id " + addSongToArtistDTO.getArtistId() + " not found.");
        }

        Artist artist = artistOpt.get();
        SongDTO songDTO = addSongToArtistDTO.getSongDTO();

        Song newSong = SongMapper.MapToViewModelDTO(songDTO);
        newSong = songRepository.save(newSong);
        artist.getSongs().add(newSong);
        newSong.getArtistsOfSong().add(artist);

        artistRepository.save(artist);
        songRepository.save(newSong);

        return artist;
    }

    // Get the song with the longest duration from a specified artist of a certain genre.
    @Override
    public Optional<Song> getLongestSongByArtistAndGenre(Long artistId, String genre) {
        Optional<Artist> artistOpt = artistRepository.findById(artistId);
        if (artistOpt.isEmpty()) {
            throw new ArtistNotFoundException("Artist with id " + artistId + " not found.");
        }

        Artist artist = artistOpt.get();
        List<Song> artistSongs = artist.getSongs();

        List<Song> songsOfGenre = artistSongs.stream()
                .filter(song -> song.getGenre().toString().equalsIgnoreCase(genre)) //filter by genre
                .collect(Collectors.toList());

        if (songsOfGenre.isEmpty()) {
            return Optional.empty(); //no songs of the specified genre
        }

        Optional<Song> longestSong = songsOfGenre.stream()
                .max(Comparator.comparing(Song::getDurationInMin)); //longest duration

        return longestSong;
    }
    //Calculate total duration of all the songs for the specified playlist
    @Override
    public double calculateTotalDurationOfPlaylist(Long playlistId) {
        Optional<Playlist> playlistOpt = playlistRepository.findById(playlistId);
        if (playlistOpt.isEmpty()) {
            throw new PlaylistNotFoundException("Playlist with id " + playlistId + " not found.");
        }

        Playlist playlist = playlistOpt.get();

        List<Song> playlistSongs = playlist.getSongsInPlaylist();

        double totalDuration = playlistSongs.stream()
                .mapToDouble(Song::getDurationInMin)
                .sum();

        return totalDuration;
    }
    //Add an existing song to a specified playlist
    @Override
    public Optional<Song> addSongToPlaylist(AddSongToPlaylistDTO addSongToPlaylistDTO){
        Optional<Playlist> playlistOpt = playlistRepository.findById(addSongToPlaylistDTO.getPlaylistId());
        SongDTO songDTO = addSongToPlaylistDTO.getSongDTO();

        if (playlistOpt.isEmpty()) {
            throw new PlaylistNotFoundException("Playlist with id " + addSongToPlaylistDTO.getPlaylistId() + " not found.");
        }

        if (!isValidSongDTO(songDTO)) { //check if the songDTO is valid
            throw new IllegalArgumentException("Invalid song data provided.");
        }

        Playlist playlist = playlistOpt.get();

        Song existingSong = SongMapper.MapToViewModelDTO(songDTO);

        playlist.getSongsInPlaylist().add(existingSong);

        playlistRepository.save(playlist);

        return Optional.of(existingSong);
    }
    @Override
    public List<Song> getTop3SongsByDurationBetween5And10Min() {

        List<Song> songs = songRepository.findByDurationInMinBetween(5.0f, 10.0f);

        List<Song> sortedSongs = songs.stream()
                .sorted(Comparator.comparingInt((Song song) -> song.getPlaylistsWithSong().size()).reversed())//sort the songs by the number of playlists they are in
                .collect(Collectors.toList());

        return sortedSongs.stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    private boolean isValidSongDTO(SongDTO songDTO) {
        return songDTO != null && songDTO.getTitle() != null && !songDTO.getTitle().isBlank();
    }
}
