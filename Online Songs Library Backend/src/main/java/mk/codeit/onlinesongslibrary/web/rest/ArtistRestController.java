package mk.codeit.onlinesongslibrary.web.rest;

import mk.codeit.onlinesongslibrary.model.Artist;
import mk.codeit.onlinesongslibrary.model.dto.ArtistDTO;
import mk.codeit.onlinesongslibrary.model.dto.ArtistNameDTO;
import mk.codeit.onlinesongslibrary.model.exceptions.ArtistException;
import mk.codeit.onlinesongslibrary.repository.ArtistRepository;
import mk.codeit.onlinesongslibrary.service.ArtistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/artists")
public class ArtistRestController {

    private final ArtistService artistService;

    private final ArtistRepository artistRepository;

    public ArtistRestController(ArtistService artistService, ArtistRepository artistRepository) {
        this.artistService = artistService;
        this.artistRepository = artistRepository;
    }

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

    //TODO: Add a new artist
    @PostMapping("/add")
    public Optional<Artist> addArtist(@RequestBody ArtistDTO artistDTO) throws Exception
    {
        Optional<Artist> artist =  this.artistService.addNewArtist(artistDTO);
        if (artistRepository.findById(artistDTO.getId()).isEmpty()) throw new ArtistException("Artist could not be added");
        return artist;
    }
    //TODO: Get all the artists with their artistic name and original name which are born before 1999 and nationality is Macedonian
    @GetMapping("/mkdartists")
    public List<ArtistNameDTO> GetMacedonianArtists() throws Exception
    {
        return this.artistService.getAllArtistsWithNationalityBornBeforeYear("Macedonian", "1999");
    }
    //TODO: Get specified artist with all their details, including the list of their songs (just with their title and release date) sorted by song name in descending order
    @GetMapping("/artist/{artistId}")
    public Optional<ArtistDTO> GetSpecifiedArtist(@PathVariable Long artistId)
    {
        return this.artistService.getArtistDetailsWithSongsSorted(artistId);
    }
}
