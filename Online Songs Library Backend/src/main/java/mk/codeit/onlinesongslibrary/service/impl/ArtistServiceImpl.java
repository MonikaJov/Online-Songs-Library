package mk.codeit.onlinesongslibrary.service.impl;

import lombok.RequiredArgsConstructor;
import mk.codeit.onlinesongslibrary.model.Artist;
import mk.codeit.onlinesongslibrary.model.Song;
import mk.codeit.onlinesongslibrary.model.dto.ArtistDTO;
import mk.codeit.onlinesongslibrary.model.dto.ArtistNameDTO;
import mk.codeit.onlinesongslibrary.model.exceptions.InvalidArtistDataException;
import mk.codeit.onlinesongslibrary.model.mappers.ArtistMapper;
import mk.codeit.onlinesongslibrary.repository.ArtistRepository;
import mk.codeit.onlinesongslibrary.service.ArtistService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {


    private final ArtistRepository artistRepository;

    // Add a new artist.
    @Transactional
    @Override
    public Optional<Artist> addNewArtist(ArtistDTO artistData) {

        if (artistData.getName() == null || artistData.getName().isBlank()) {
            throw new InvalidArtistDataException("Artist name cannot be null or blank");
        }

        Artist artist = ArtistMapper.MapToViewModelDTO(artistData);

        artist = artistRepository.save(artist);

        if (artistData.getSongs() != null && !artistData.getSongs().isEmpty()) {
            List<Song> songs = new ArrayList<>(artistData.getSongs());
            artist.setSongs(songs);
        }

        return Optional.of(artist);
    }

    //Get all the artists with their artistic name and original name which are born before specified year and nationality
    @Override
    public List<ArtistNameDTO> getAllArtistsWithNationalityBornBeforeYear(String nationality, String year) {

        LocalDate date;

        try {
            date = LocalDate.of(Integer.parseInt(year), 1, 1);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid year format: " + year, e);
        }

        List<Artist> artists = artistRepository.findAllByNationalityEqualsAndDateOfBirthIsBefore(nationality, date);

        return ArtistMapper.MapToListViewModelNames(artists);
    }

    //Get specified artist with all their details, including the list of their songs (just with their title and release date)
    // sorted by song name in descending order.
    @Override
    public Optional<ArtistDTO> getArtistDetailsWithSongsSorted(Long artistId) {
        Optional<Artist> artistOptional = artistRepository.findById(artistId);
        if (artistOptional.isEmpty()) {
            return Optional.empty(); // Artist not found
        }

        Artist artist = artistOptional.get();
        //Sort songs
        List<Song> sortedSongs = artist.getSongs().stream()
                .sorted(Comparator.comparing(Song::getTitle).reversed())
                .collect(Collectors.toList());

        ArtistDTO artistDTO = ArtistMapper.MapToViewModel(artist);

        artistDTO.setSongs(new ArrayList<>(sortedSongs));

        return Optional.of(artistDTO);
    }
}
