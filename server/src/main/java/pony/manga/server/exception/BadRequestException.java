package pony.manga.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Bad request exception.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends ApiClientException {
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Bad request exception.
     *
     * @param message the message
     */
    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "BadRequestException{}";
    }
}
