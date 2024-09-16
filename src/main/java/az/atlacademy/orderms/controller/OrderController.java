package az.atlacademy.orderms.controller;

import az.atlacademy.orderms.model.request.OrderProductDto;
import az.atlacademy.orderms.model.response.OrderResponseDto;
import az.atlacademy.orderms.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.status(OK).body(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<Void> oderProduct(@Valid @RequestBody OrderProductDto dto) {
        orderService.oderProduct(dto);
        return ResponseEntity.status(OK).build();
    }
}