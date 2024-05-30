package mk.codeit.onlinesongslibrary.service;

import mk.codeit.onlinesongslibrary.model.Artist;
import mk.codeit.onlinesongslibrary.model.dto.ArtistDTO;
import mk.codeit.onlinesongslibrary.model.dto.ArtistNameDTO;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    //TODO: Add a new artist
    Optional<Artist> addNewArtist(ArtistDTO artistData) throws Exception;
    //TODO: Get all the artists with their artistic name and original name which are born before 1999 and nationality is Macedonian
    List<ArtistNameDTO> getAllArtistsWithNationalityBornBeforeYear(String nationality, String year);
    //TODO: Get specified artist with all their details, including the list of their songs (just with their title and release date) sorted by song name in descending order
    Optional<ArtistDTO> getArtistDetailsWithSongsSorted(Long artistId);
}