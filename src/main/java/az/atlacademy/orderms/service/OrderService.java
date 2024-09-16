package az.atlacademy.orderms.service;

import az.atlacademy.orderms.model.request.OrderProductDto;
import az.atlacademy.orderms.model.response.OrderResponseDto;


public interface OrderService {
    OrderResponseDto getOrderById (Long id);

    void oderProduct(OrderProductDto dto);
}

