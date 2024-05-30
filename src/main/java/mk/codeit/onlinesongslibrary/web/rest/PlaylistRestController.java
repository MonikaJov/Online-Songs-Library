package mk.codeit.onlinesongslibrary.web.rest;

import mk.codeit.onlinesongslibrary.model.Playlist;
import mk.codeit.onlinesongslibrary.model.dto.PlaylistDTO;
import mk.codeit.onlinesongslibrary.model.exceptions.ArtistNotFoundException;
import mk.codeit.onlinesongslibrary.model.exceptions.PlaylistException;
import mk.codeit.onlinesongslibrary.repository.PlaylistRepository;
import mk.codeit.onlinesongslibrary.service.PlaylistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistRestController {
    private final PlaylistService playlistService;
    private final PlaylistRepository playlistRepository;

    public PlaylistRestController(PlaylistService playlistService, PlaylistRepository playlistRepository) {
        this.playlistService = playlistService;
        this.playlistRepository = playlistRepository;
    }
    //TODO: Add a new playlist.
    @PostMapping("/add")
    public Optional<Playlist> addPlaylist(@RequestBody PlaylistDTO playlistDTO) throws Exception
    {
        Optional<Playlist> playlist = this.playlistService.addNewPlaylist(playlistDTO);
        if (playlistRepository.findById(playlistDTO.getId()).isEmpty()) throw new PlaylistException("Playlist could not be added");
        return playlist;
    }
    //TODO: Get all playlists containing songs by a specified artist sorted first by Artist name then by Artist date of birth in ascending order
    @GetMapping("/artist/{artistId}")
    public List<PlaylistDTO> GetSpecifiedArtist(@PathVariable Long artistId)
    {
        return this.playlistService.getPlaylistsByArtistSorted(artistId);
    }
    //TODO: Get all the playlists which are public and contain a maximum of 3 songs.
    @GetMapping("/public")
    public List<PlaylistDTO> GetSpecifiedArtist()
    {
        return this.playlistService.getPublicPlaylistsWithMaxThreeSongs();
    }
    //TODO: Delete specified playlist
    @PostMapping("/delete/{playlistId}")
    public String deletePlaylist(@PathVariable Long playlistId) throws Exception {
        this.playlistService.deletePlaylist(playlistId);
        if (playlistRepository.findById(playlistId).isPresent()) throw new PlaylistException("Playlist could not be deleted");
        return "";
    }
}
