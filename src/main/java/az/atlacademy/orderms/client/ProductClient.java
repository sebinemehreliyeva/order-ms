package az.atlacademy.orderms.client;

import az.atlacademy.orderms.model.response.ProductResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductClient {
    @GetMapping("/{id}")
    ProductResponseDto getProductById(@PathVariable Long id);

    @PostMapping("reduce/{id}")
    void reduceProductCount(@PathVariable Long id, @RequestParam Integer count);
}
