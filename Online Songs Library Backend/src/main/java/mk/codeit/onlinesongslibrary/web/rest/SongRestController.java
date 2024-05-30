package mk.codeit.onlinesongslibrary.web.rest;

import mk.codeit.onlinesongslibrary.model.Artist;
import mk.codeit.onlinesongslibrary.model.Song;
import mk.codeit.onlinesongslibrary.model.dto.AddSongToArtistDTO;
import mk.codeit.onlinesongslibrary.model.dto.AddSongToPlaylistDTO;
import mk.codeit.onlinesongslibrary.service.SongService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
public class SongRestController {
    private final SongService songService;

    public SongRestController(SongService songService) {
        this.songService = songService;
    }
    //TODO: Add a new song for the specified artist.
    @PostMapping("/add")
    public Artist addSongToArtist(@RequestBody AddSongToArtistDTO addSongToArtistDTO) throws Exception
    {
        return this.songService.addNewSongToArtist(addSongToArtistDTO);
    }
    //TODO: Get the song with the longest duration from a specified artist of a certain genre.
    @GetMapping("/longestduration/{artistId}/{genre}")
    public Optional<Song> getSongWithDuartion(@PathVariable Long artistId, @PathVariable String genre)
    {
        return this.songService.getLongestSongByArtistAndGenre(artistId, genre);
    }
    //TODO: Calculate total duration of all the songs for the specified playlist.
    @GetMapping("/duration/{playlistId}")
    public double getSongWithDuartion(@PathVariable Long playlistId)
    {
        return this.songService.calculateTotalDurationOfPlaylist(playlistId);
    }
    //TODO: Add an existing song to a specified playlist.
    @PostMapping("/add/{playlistId}")
    public Optional<Song> addSongToPlaylist(@RequestBody AddSongToPlaylistDTO addSongToPlaylistDTO) throws Exception
    {
        return this.songService.addSongToPlaylist(addSongToPlaylistDTO);
    }
    //TODO: Get first 3 songs with duration between 5 and 10 minutes and sort by duration time in descending order
    @GetMapping("/sorted")
    public List<Song> getSongSorted()
    {
        return this.songService.getTop3SongsByDurationBetween5And10Min();
    }
}
