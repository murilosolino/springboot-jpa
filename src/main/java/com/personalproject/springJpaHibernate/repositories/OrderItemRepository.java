package com.personalproject.springJpaHibernate.repositories;

import com.personalproject.springJpaHibernate.entities.OrderItem;
import com.personalproject.springJpaHibernate.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
