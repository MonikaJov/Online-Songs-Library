package mk.codeit.onlinesongslibrary.model.mappers;

import mk.codeit.onlinesongslibrary.model.Song;
import mk.codeit.onlinesongslibrary.model.dto.SongDTO;

import java.util.ArrayList;
import java.util.List;

public class SongMapper {
    public static SongDTO MapToViewModel(Song song)
    {
        return new SongDTO(
                song.getId(),
                song.getTitle(),
                song.getDurationInMin(),
                song.getReleaseDate(),
                song.getGenre());
    }

    public static Song MapToViewModelDTO(SongDTO songDTO)
    {
        return new Song(
                songDTO.getTitle(),
                songDTO.getDurationInMin(),
                songDTO.getReleaseDate(),
                songDTO.getGenre());
    }

    public static List<SongDTO> MapToListViewModel(List<Song> songs){
        List<SongDTO> SongDTOS = new ArrayList<>();
        for (var song : songs){
            SongDTOS.add(MapToViewModel(song));
        }
        return SongDTOS;
    }
    public static KeyValue MapToKeyValue(SongDTO dto){
        return new KeyValue(dto.getId(), dto.getTitle());
    }

    public static List<KeyValue> MapToKeyValueList(List<SongDTO> DTOs){
        List<KeyValue> categoryKeyValues = new ArrayList<>();
        for (var cat : DTOs){
            categoryKeyValues.add(MapToKeyValue(cat));
        }
        return categoryKeyValues;
    }

    public static KeyValue MapToKeyValueDomain(Song domainModel){
        return new KeyValue(domainModel.getId(), domainModel.getTitle());
    }

    public static List<KeyValue> MapToKeyValueDomainList(List<Song> domainList){
        List<KeyValue> playlistKeyValues = new ArrayList<>();
        for (var pl : domainList){
            playlistKeyValues.add(MapToKeyValueDomain(pl));
        }
        return playlistKeyValues;
    }
}
