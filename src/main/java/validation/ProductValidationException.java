package validation;

public class ProductValidationException extends IllegalArgumentException {
    public ProductValidationException(String message) {
        super(message);
    }
}
