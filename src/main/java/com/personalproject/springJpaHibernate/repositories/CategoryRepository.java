package com.personalproject.springJpaHibernate.repositories;

import com.personalproject.springJpaHibernate.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
