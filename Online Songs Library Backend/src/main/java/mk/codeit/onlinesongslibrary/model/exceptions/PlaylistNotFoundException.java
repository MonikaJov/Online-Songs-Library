package mk.codeit.onlinesongslibrary.model.exceptions;

public class PlaylistNotFoundException extends RuntimeException{
    public PlaylistNotFoundException(String message) {
        super(message);
    }
}
