package az.atlacademy.orderms.repository;

import az.atlacademy.orderms.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

