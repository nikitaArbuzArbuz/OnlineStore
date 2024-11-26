package ru.moysklad.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.moysklad.onlinestore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
