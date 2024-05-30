package mk.codeit.onlinesongslibrary.model.mappers;

import mk.codeit.onlinesongslibrary.model.Playlist;
import mk.codeit.onlinesongslibrary.model.dto.PlaylistDTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistMapper {
    public static PlaylistDTO MapToViewModel(Playlist playlist)
    {
        return new PlaylistDTO(
                playlist.getId(),
                playlist.getName(),
                playlist.getStatus(),
                playlist.getDateOfCreation());
    }
    public static Playlist MapToViewModelDTO (PlaylistDTO playlistDTO)
    {
        return new Playlist(
                playlistDTO.getName(),
                playlistDTO.getStatus(),
                playlistDTO.getDateOfCreation());
    }
    public static List<PlaylistDTO> MapToListViewModel(List<Playlist> playlists){
        List<PlaylistDTO> playlistsDTOS = new ArrayList<>();
        for (var p : playlists){
            playlistsDTOS.add(MapToViewModel(p));
        }
        return playlistsDTOS;
    }

    public static KeyValue MapToKeyValue(PlaylistDTO dto){
        return new KeyValue(dto.getId(), dto.getName());
    }

    public static List<KeyValue> MapToKeyValueList(List<PlaylistDTO> DTOs){
        List<KeyValue>playlistsKeyValues = new ArrayList<>();
        for (var p : DTOs){
            playlistsKeyValues.add(MapToKeyValue(p));
        }
        return playlistsKeyValues;
    }

    public static KeyValue MapToKeyValueDomain(Playlist domainModel){
        return new KeyValue(domainModel.getId(), domainModel.getName());
    }

    public static List<KeyValue> MapToKeyValueDomainList(List<Playlist> domainList){
        List<KeyValue> playlistKeyValues = new ArrayList<>();
        for (var pl : domainList){
            playlistKeyValues.add(MapToKeyValueDomain(pl));
        }
        return playlistKeyValues;
    }
}
