package az.atlacademy.orderms.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Sifariş tapılmadı: " + id);
    }
}
