package mk.codeit.onlinesongslibrary.model.exceptions;

public class ArtistNotFoundException extends RuntimeException{
    public ArtistNotFoundException(String message) {
        super(message);
    }
}
