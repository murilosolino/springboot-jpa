package com.personalproject.springJpaHibernate.repositories;

import com.personalproject.springJpaHibernate.entities.Category;
import com.personalproject.springJpaHibernate.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
