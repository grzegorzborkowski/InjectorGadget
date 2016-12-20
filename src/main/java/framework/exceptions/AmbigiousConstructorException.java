package framework.exceptions;

public class AmbigiousConstructorException extends RuntimeException {
    public AmbigiousConstructorException(String message) {
        super(message);
    }
}
