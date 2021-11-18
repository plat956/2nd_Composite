package by.latushko.training.exception;

public class TextAnalyzingException extends Exception{
    public TextAnalyzingException() {
        super();
    }

    public TextAnalyzingException(String message) {
        super(message);
    }

    public TextAnalyzingException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextAnalyzingException(Throwable cause) {
        super(cause);
    }
}
