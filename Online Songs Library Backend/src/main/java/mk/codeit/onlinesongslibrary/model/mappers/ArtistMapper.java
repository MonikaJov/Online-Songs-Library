package mk.codeit.onlinesongslibrary.model.mappers;

import mk.codeit.onlinesongslibrary.model.Artist;
import mk.codeit.onlinesongslibrary.model.dto.ArtistDTO;
import mk.codeit.onlinesongslibrary.model.dto.ArtistNameDTO;

import java.util.ArrayList;
import java.util.List;

public class ArtistMapper {
    public static ArtistDTO MapToViewModel(Artist artist)
    {
        return new ArtistDTO(
                artist.getId(),
                artist.getName(),
                artist.getArtisticName(),
                artist.getDateOfBirth(),
                artist.getNationality(),
                artist.getSongs());
    }
    public static Artist MapToViewModelDTO(ArtistDTO artistDTO)
    {
        return new Artist(
                artistDTO.getName(),
                artistDTO.getArtisticName(),
                artistDTO.getDateOfBirth(),
                artistDTO.getNationality()
        );
    }
    public static List<ArtistDTO> MapToListViewModel(List<Artist> artists){
        List<ArtistDTO> ArtistDTOS = new ArrayList<>();
        for (var artist : artists){
            ArtistDTOS.add(MapToViewModel(artist));
        }
        return ArtistDTOS;
    }

    public static ArtistNameDTO MapToViewModelName(Artist artist)
    {
        return new ArtistNameDTO(
                artist.getName(),
                artist.getArtisticName());

    }


    public static List<ArtistNameDTO> MapToListViewModelNames(List<Artist> artists){
        List<ArtistNameDTO> ArtistDTOS = new ArrayList<>();
        for (var artist : artists){
            ArtistDTOS.add(MapToViewModelName(artist));
        }
        return ArtistDTOS;
    }

    public static KeyValue MapToKeyValue(ArtistDTO dto){
        return new KeyValue(dto.getId(), dto.getName());
    }

    public static List<KeyValue> MapToKeyValueList(List<ArtistDTO> DTOs){
        List<KeyValue> categoryKeyValues = new ArrayList<>();
        for (var cat : DTOs){
            categoryKeyValues.add(MapToKeyValue(cat));
        }
        return categoryKeyValues;
    }

    public static KeyValue MapToKeyValueDomain(Artist domainModel){
        return new KeyValue(domainModel.getId(), domainModel.getName());
    }

    public static List<KeyValue> MapToKeyValueDomainList(List<Artist> domainList){
        List<KeyValue> artistKeyValues = new ArrayList<>();
        for (var a : domainList){
            artistKeyValues.add(MapToKeyValueDomain(a));
        }
        return artistKeyValues;
    }
}
