package com.personalproject.springJpaHibernate.repositories;

import com.personalproject.springJpaHibernate.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
