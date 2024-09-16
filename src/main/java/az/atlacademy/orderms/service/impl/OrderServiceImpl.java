package az.atlacademy.orderms.service.impl;

import az.atlacademy.orderms.client.CustomerClient;
import az.atlacademy.orderms.client.ProductClient;
import az.atlacademy.orderms.dao.entity.OrderEntity;
import az.atlacademy.orderms.dao.OrderRepository;
import az.atlacademy.orderms.exception.OrderNotFoundException;
import az.atlacademy.orderms.model.request.OrderProductDto;
import az.atlacademy.orderms.model.response.OrderResponseDto;
import az.atlacademy.orderms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static az.atlacademy.orderms.mapper.OrderMapper.ORDER_MAPPER;
import static az.atlacademy.orderms.model.enums.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final CustomerClient customerClient;

    @Override
    public OrderResponseDto getOrderById(Long id) {
        var entity = fetchOrderIfExist(id);
        return ORDER_MAPPER.buildOrderResponseDto(entity);
    }

    @Override
    @Transactional
    public void oderProduct(OrderProductDto dto) {
        var product = productClient.getProductById(dto.getProductId());
        var totalAmount = product.getPrice().multiply(new BigDecimal(dto.getCount()));
        var customer = customerClient.getCustomerById(dto.getCustomerId());
        var orderEntity = ORDER_MAPPER.buildOrderEntity(dto, totalAmount);

        if (product.getCount() < dto.getCount())
            throw new RuntimeException(INSUFFICIENT_QUANTITY.getMessage());

        if (customer.getBalance().compareTo(totalAmount) < 0)
            throw new RuntimeException(INSUFFICIENT_BALANCE.getMessage());

        productClient.reduceProductCount(dto.getProductId(), dto.getCount());
        customerClient.reduceBalance(dto.getCustomerId(), totalAmount);
        orderRepository.save(orderEntity);
    }


    private OrderEntity fetchOrderIfExist(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(ORDER_NOT_FOUND.format(id))
        );
    }
}
