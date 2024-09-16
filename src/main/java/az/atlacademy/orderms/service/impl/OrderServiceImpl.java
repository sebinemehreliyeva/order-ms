package az.atlacademy.orderms.service.impl;

import az.atlacademy.orderms.entity.Order;
import az.atlacademy.orderms.repository.OrderRepository;
import az.atlacademy.orderms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Order updatedOrder = existingOrder.get();
            updatedOrder.setCustomerId(order.getCustomerId());
            updatedOrder.setProductId(order.getProductId());
            updatedOrder.setPrice(order.getPrice());
            updatedOrder.setCount(order.getCount());
            return orderRepository.save(updatedOrder);
        }
        return null;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

