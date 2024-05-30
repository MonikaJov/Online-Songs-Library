package mk.codeit.onlinesongslibrary.model.exceptions;

public class InvalidArtistDataException extends RuntimeException {
    public InvalidArtistDataException(String message) {
        super(message);
    }
}